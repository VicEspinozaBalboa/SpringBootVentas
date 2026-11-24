package com.pepito.InventarioService.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepito.InventarioService.Models.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

	Optional<Inventario> findByProductId(Long productId);
}
