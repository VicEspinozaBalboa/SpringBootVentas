package com.pepito.PedidoService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table (name = "Orden")
@Entity 
@Data 
public class Pedido {
    @Id 
    @GeneratedValue 
    private Long id;
    private Long idProducto;
    private Integer cantidad;
    private Double total;
}
