package com.aibiigae1221.study;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
			.addEndpoint("/ws")
			.setAllowedOrigins("ws://localhost:3000", "ws://127.0.0.1:3000", 
								"http://localhost:3000", "http://127.0.0.1:3000")
			.setAllowedOriginPatterns("ws://localhost:3000", "ws://127.0.0.1:3000", 
										"http://localhost:3000", "http://127.0.0.1:3000")
			.withSockJS();
	}
	
}
