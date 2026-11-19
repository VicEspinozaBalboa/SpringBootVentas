package com.pepito.PedidoService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table (name = "Orden")
@Entity 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Pedido {
    @Id 
    @GeneratedValue 
    private Long id;
    private Long idProducto;
    private Integer cantidad;
    private Double total;
}
