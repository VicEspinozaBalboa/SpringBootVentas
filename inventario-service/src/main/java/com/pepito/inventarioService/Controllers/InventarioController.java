package com.pepito.InventarioService.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepito.InventarioService.DTOs.ActualizarStockRequest;
import com.pepito.InventarioService.Models.Inventario;
import com.pepito.InventarioService.Services.InventarioService;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {

	private final InventarioService inventarioService;

	public InventarioController(InventarioService inventarioService) {
		this.inventarioService = inventarioService;
	}

	@GetMapping
	public List<Inventario> obtenerTodos() {
		return inventarioService.obtenerTodos();
	}

	@GetMapping("/{productId}")
	public Inventario obtenerPorProductId(@PathVariable Long productId) {
		return inventarioService.obtenerPorProductId(productId);
	}

	@PutMapping("/{productId}")
	public Inventario actualizarStock(
			@PathVariable Long productId,
			@RequestBody ActualizarStockRequest request) {
		return inventarioService.actualizarStock(productId, request.cantidadDisponible());
	}
}
