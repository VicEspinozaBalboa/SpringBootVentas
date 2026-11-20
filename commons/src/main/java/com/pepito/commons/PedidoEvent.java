package com.pepito.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class PedidoEvent {
    private Long id;
    private Long productId;
    private Integer cantidad;
    
}
