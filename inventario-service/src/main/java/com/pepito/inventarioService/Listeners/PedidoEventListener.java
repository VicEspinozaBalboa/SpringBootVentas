package com.pepito.InventarioService.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.pepito.InventarioService.Config.RabbitMQConfig;
import com.pepito.InventarioService.Services.InventarioService;
import com.pepito.commons.PedidoEvent;

@Component
public class PedidoEventListener {

	private static final Logger logger = LoggerFactory.getLogger(PedidoEventListener.class);

	private final InventarioService inventarioService;

	public PedidoEventListener(InventarioService inventarioService) {
		this.inventarioService = inventarioService;
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE)
	public void handlePedidoCreated(PedidoEvent pedidoEvent) {
		try {
			inventarioService.procesarPedido(pedidoEvent);
		} catch (IllegalArgumentException | NullPointerException exception) {
			logger.error("Se descarta un PedidoEvent inválido", exception);
			throw new AmqpRejectAndDontRequeueException("Evento de pedido inválido", exception);
		}
	}
}
