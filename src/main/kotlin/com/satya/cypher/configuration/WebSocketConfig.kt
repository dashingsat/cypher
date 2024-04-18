package com.satya.cypher.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // Registering the endpoint and enabling SockJS fallback options
        registry.addEndpoint("/websocket")
            //.setAllowedOrigins("http://localhost:8080")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        // Setting "/app" as the prefix for messages bound for methods annotated with @MessageMapping
        // Using "/topic" as the prefix for outgoing messages to the message broker
        registry.setApplicationDestinationPrefixes("/app")
        registry.enableSimpleBroker("/topic")
    }



}