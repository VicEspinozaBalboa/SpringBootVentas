package com.pepito.PedidoService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import com.pepito.PedidoService.Config.ProductoClient;
import com.pepito.PedidoService.DTOs.Producto;
import com.pepito.PedidoService.Models.Pedido;
import com.pepito.PedidoService.Repositories.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PedidoService {
    @Autowired 
    private PedidoRepository pedidoRepository;
    @Autowired 
    //private productoRepository productoRepository;
    private RestTemplate restTemplate;
    //private final ProductoClient productoClient;
    public Pedido registrarPedido(Pedido pedido){
        //Llamada al microservicio de productos
        String productoURL = "http://localhost:8080/api/v1/productos/" + pedido.getIdProducto();
        Producto producto = restTemplate.getForObject(productoURL, Producto.class);
        
        //Producto producto  = productoClient.obteneProductoPorId(pedido.getIdProducto());
        if (producto == null) throw new RuntimeException("No se encontro el producto (conexion REST a microservicio producto-service)");
        pedido.setTotal(producto.getPrecio()*pedido.getCantidad());
        pedidoRepository.save(pedido);
        return pedido;
    }

    public List<Pedido> obtenerTodosLosPedidos(){
        return pedidoRepository.findAll();
    }
}
