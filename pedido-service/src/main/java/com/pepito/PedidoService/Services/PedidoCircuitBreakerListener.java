package com.pepito.PedidoService.Services;

import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker.StateTransition;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import jakarta.annotation.PostConstruct;

@Component 
public class PedidoCircuitBreakerListener {
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final PedidoRecoveryService pedidoRecoveryService;
   
    public PedidoCircuitBreakerListener(CircuitBreakerRegistry circuitBreakerRegistry,
            PedidoRecoveryService pedidoRecoveryService) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.pedidoRecoveryService = pedidoRecoveryService;
    }

    @PostConstruct 
    public void registerCircuitBreakerListener(){
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("productoServiceCB");
        circuitBreaker.getEventPublisher().onStateTransition(this::manejarTransicion);
    }

    private void manejarTransicion (CircuitBreakerOnStateTransitionEvent event){
        if (event.getStateTransition() == StateTransition.HALF_OPEN_TO_CLOSED) {
            pedidoRecoveryService.recoverPedidosPendientes();
        }
    }

}
