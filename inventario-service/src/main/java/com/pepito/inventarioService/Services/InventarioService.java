package com.pepito.InventarioService.Services;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.pepito.InventarioService.Models.Inventario;
import com.pepito.InventarioService.Models.PedidoProcesado;
import com.pepito.InventarioService.Repositories.InventarioRepository;
import com.pepito.InventarioService.Repositories.PedidoProcesadoRepository;
import com.pepito.commons.PedidoEvent;

@Service
public class InventarioService {

	private static final Logger logger = LoggerFactory.getLogger(InventarioService.class);

	private final InventarioRepository inventarioRepository;
	private final PedidoProcesadoRepository pedidoProcesadoRepository;

	public InventarioService(
			InventarioRepository inventarioRepository,
			PedidoProcesadoRepository pedidoProcesadoRepository) {
		this.inventarioRepository = inventarioRepository;
		this.pedidoProcesadoRepository = pedidoProcesadoRepository;
	}

	public List<Inventario> obtenerTodos() {
		return inventarioRepository.findAll();
	}

	public Inventario obtenerPorProductId(Long productId) {
		return inventarioRepository.findByProductId(productId)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "No existe inventario para el producto " + productId));
	}

	@Transactional
	public Inventario actualizarStock(Long productId, Integer cantidadDisponible) {
		Objects.requireNonNull(productId, "productId es obligatorio");
		Objects.requireNonNull(cantidadDisponible, "cantidadDisponible es obligatoria");

		Inventario inventario = inventarioRepository.findByProductId(productId)
				.orElseGet(() -> new Inventario(productId, cantidadDisponible));
		inventario.setCantidadDisponible(cantidadDisponible);
		Inventario inventarioActualizado = inventarioRepository.save(inventario);
		logger.info(
				"Stock ajustado: productId={}, cantidadDisponible={}",
				productId,
				cantidadDisponible);
		return inventarioActualizado;
	}

	@Transactional
	public void procesarPedido(PedidoEvent pedidoEvent) {
		Objects.requireNonNull(pedidoEvent, "El evento de pedido es obligatorio");
		Objects.requireNonNull(pedidoEvent.getId(), "El id del pedido es obligatorio");
		Objects.requireNonNull(pedidoEvent.getProductId(), "El productId es obligatorio");
		Objects.requireNonNull(pedidoEvent.getCantidad(), "La cantidad es obligatoria");

		if (pedidoEvent.getCantidad() <= 0) {
			throw new IllegalArgumentException("La cantidad del pedido debe ser mayor que cero");
		}

		if (pedidoProcesadoRepository.existsById(pedidoEvent.getId())) {
			logger.info("Pedido ya procesado; se ignora la redelivery: pedidoId={}", pedidoEvent.getId());
			return;
		}

		Inventario inventario = inventarioRepository.findByProductId(pedidoEvent.getProductId())
				.orElseGet(() -> new Inventario(pedidoEvent.getProductId(), 0));
		int cantidadActualizada = inventario.getCantidadDisponible() - pedidoEvent.getCantidad();
		inventario.setCantidadDisponible(cantidadActualizada);
		inventarioRepository.save(inventario);
		pedidoProcesadoRepository.save(new PedidoProcesado(pedidoEvent.getId()));

		logger.info(
				"Stock actualizado por pedido: pedidoId={}, productId={}, cantidadSolicitada={}, cantidadDisponible={}",
				pedidoEvent.getId(),
				pedidoEvent.getProductId(),
				pedidoEvent.getCantidad(),
				cantidadActualizada);
	}
}
