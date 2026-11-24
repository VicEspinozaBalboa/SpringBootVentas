package com.pepito.ProductoService.Configs;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.pepito.commons.PedidoEvent;

@Component 
public class PedidoEventListener {
    // RabbitMQ listener
    @RabbitListener (queues = RabbitMQConfig.QUEUE)
    public void handlePedidoCreated(PedidoEvent pedidoEvent) {
        System.out.println("Received message from RabbitMQ: " + pedidoEvent);
        // Aquí puedes agregar la lógica para procesar el mensaje recibido
    }

}
