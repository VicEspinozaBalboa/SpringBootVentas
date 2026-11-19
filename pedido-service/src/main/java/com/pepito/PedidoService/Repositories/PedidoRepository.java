package com.pepito.PedidoService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepito.PedidoService.Models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Long>{
    List<Pedido> findByTotal(Double total);
    
}
