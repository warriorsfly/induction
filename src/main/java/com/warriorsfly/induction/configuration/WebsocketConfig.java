package com.warriorsfly.induction.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/chat").withSockJS();
        registry.addEndpoint("/broadcast").withSockJS();//.setHeartbeatTime(25_000);
        registry.addEndpoint("/group").withSockJS();//.setHeartbeatTime(25_000);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // for group ack,I think redis stream is a good choise,so stop using rabbitmq for now
        // when tests passed, delete rabbitmq dependences
        registry.enableSimpleBroker("/chat","/boardcast","/group");
//        registry.setApplicationDestinationPrefixes("/app");
//        registry.enableStompBrokerRelay("/topic", "/queue")
//                .setAutoStartup(true)
//                .setRelayHost("127.0.0.1")
//                .setRelayPort(61613)
//                .setClientLogin("guest")
//                .setClientPasscode("guest");

    }
}
