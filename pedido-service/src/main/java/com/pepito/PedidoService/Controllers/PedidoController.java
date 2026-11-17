package com.pepito.PedidoService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepito.PedidoService.Models.Pedido;
import com.pepito.PedidoService.Services.PedidoService;

@RestController 
@RequestMapping ("/api/v1/pedidos")
public class PedidoController {
   @Autowired 
    private PedidoService pedidoService;

    @PostMapping 
    public Pedido crearPedido(@RequestBody Pedido pedido){
        return pedidoService.registrarPedido(pedido);
    }

    @GetMapping 
    public List<Pedido> obtenerTodosLosPedidos(){
        return pedidoService.obtenerTodosLosPedidos();
    }
}
