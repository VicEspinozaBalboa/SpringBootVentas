package com.pepito.InventarioService.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventario")
@Getter
@Setter
public class Inventario {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private Long productId;

	@Column(nullable = false)
	private Integer cantidadDisponible;

	protected Inventario() {
	}

	public Inventario(Long productId, Integer cantidadDisponible) {
		this.productId = productId;
		this.cantidadDisponible = cantidadDisponible;
	}
}
