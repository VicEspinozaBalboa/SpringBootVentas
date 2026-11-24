package com.pepito.InventarioService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_procesado")
public class PedidoProcesado {

	@Id
	private Long id;

	protected PedidoProcesado() {
	}

	public PedidoProcesado(Long id) {
		this.id = id;
	}
}
