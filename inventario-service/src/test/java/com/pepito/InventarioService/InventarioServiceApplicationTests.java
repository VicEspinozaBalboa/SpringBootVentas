package com.pepito.InventarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pepito.InventarioService.Repositories.InventarioRepository;
import com.pepito.InventarioService.Repositories.PedidoProcesadoRepository;
import com.pepito.InventarioService.Services.InventarioService;
import com.pepito.commons.PedidoEvent;

@SpringBootTest(properties = {
		"spring.rabbitmq.listener.simple.auto-startup=false",
		"spring.cloud.config.enabled=false",
		"eureka.client.enabled=false"
})
class InventarioServiceApplicationTests {

	@Autowired
	private InventarioService inventarioService;

	@Autowired
	private InventarioRepository inventarioRepository;

	@Autowired
	private PedidoProcesadoRepository pedidoProcesadoRepository;

	@BeforeEach
	void limpiarDatos() {
		pedidoProcesadoRepository.deleteAll();
		inventarioRepository.deleteAll();
	}

	@Test
	void procesaPedidoYPermiteStockNegativo() {
		inventarioService.actualizarStock(42L, 2);

		inventarioService.procesarPedido(new PedidoEvent(100L, 42L, 5));

		assertEquals(-3, inventarioService.obtenerPorProductId(42L).getCantidadDisponible());
	}

	@Test
	void noDescuentaDosVecesElMismoPedido() {
		inventarioService.actualizarStock(42L, 10);
		PedidoEvent evento = new PedidoEvent(100L, 42L, 3);

		inventarioService.procesarPedido(evento);
		inventarioService.procesarPedido(evento);

		assertEquals(7, inventarioService.obtenerPorProductId(42L).getCantidadDisponible());
	}
}
