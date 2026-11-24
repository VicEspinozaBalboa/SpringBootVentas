package com.pepito.InventarioService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepito.InventarioService.Models.PedidoProcesado;

public interface PedidoProcesadoRepository extends JpaRepository<PedidoProcesado, Long> {
}
