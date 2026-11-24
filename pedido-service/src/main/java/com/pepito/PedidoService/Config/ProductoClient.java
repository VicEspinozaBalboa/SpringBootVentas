package com.pepito.PedidoService.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pepito.PedidoService.DTOs.Producto;

@FeignClient (name = "producto-service")
public interface ProductoClient {
    @GetMapping ("/api/v1/productos/{id}")
    Producto obteneProductoPorId(@PathVariable Long id);
    
}

