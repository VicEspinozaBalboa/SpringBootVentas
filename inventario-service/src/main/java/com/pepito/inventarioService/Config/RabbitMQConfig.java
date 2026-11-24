package com.pepito.InventarioService.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String QUEUE = "inventario.pedido.queue";
	public static final String EXCHANGE = "pedido.exchange";
	public static final String ROUTING_KEY = "pedido.created";

	@Bean
	public Queue inventarioPedidoQueue() {
		return new Queue(QUEUE, true);
	}

	@Bean
	public TopicExchange pedidoExchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Binding inventarioPedidoBinding(Queue inventarioPedidoQueue, TopicExchange pedidoExchange) {
		return BindingBuilder.bind(inventarioPedidoQueue).to(pedidoExchange).with(ROUTING_KEY);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
