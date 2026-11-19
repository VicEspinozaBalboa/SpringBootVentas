package com.pepito.PedidoService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepito.PedidoService.Config.ProductoClient;
import com.pepito.PedidoService.DTOs.Producto;
import com.pepito.PedidoService.Models.Pedido;
import com.pepito.PedidoService.Repositories.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PedidoRecoveryService { 
    @Autowired 
    private static final Double TOTAL_PENDIENTE = -1.0;
    private final PedidoRepository pedidoRepository;
    private final ProductoClient productoClient;
    
    public void recoverPedidosPendientes(){
        System.out.println("RECUPERANDO PEDIDOS PENDIENTES");
        List<Pedido> pedidosPendientes = pedidoRepository.findByTotal(TOTAL_PENDIENTE);
        for (Pedido pedido : pedidosPendientes)
            {
                try {
                    Producto producto = productoClient.obteneProductoPorId(pedido.getIdProducto());
                    pedido.setTotal(producto.getPrecio() * pedido.getCantidad());
                    pedidoRepository.save(pedido);
                } catch (Exception e) {
                    pedidoRepository.delete(pedido);
                }
            } 
    }
}
