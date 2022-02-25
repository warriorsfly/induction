package com.warriorsfly.col.configuration.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    public static final String NOTIFICATIONS="notifications";
    public static final String GROUPS="groups";
    public static final String BROADCASTS="broadcasts";
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint(NOTIFICATIONS).withSockJS();
        registry.addEndpoint(GROUPS).withSockJS().setHeartbeatTime(25_000);
        registry.addEndpoint(BROADCASTS).withSockJS().setHeartbeatTime(25_000);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setPathMatcher(new AntPathMatcher("."));
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableStompBrokerRelay( "/amq/queue", "/queue", "/topic", "/temp-queue/")
                .setAutoStartup(true)
                .setRelayHost("127.0.0.1")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
    }
}
