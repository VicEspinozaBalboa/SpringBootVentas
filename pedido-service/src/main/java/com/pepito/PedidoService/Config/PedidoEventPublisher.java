package com.pepito.PedidoService.Config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.pepito.commons.PedidoEvent;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class PedidoEventPublisher {
    //logging
    private final RabbitTemplate rabbitTemplate;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PedidoEventPublisher.class);

    public void publishPedidoCreatedEvent(PedidoEvent pedidoEvent) {
    
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE, 
            RabbitMQConfig.ROUTING_KEY,
            pedidoEvent);

        logger.info("PedidoEvent publicado: {}", pedidoEvent);

    }

    
}
