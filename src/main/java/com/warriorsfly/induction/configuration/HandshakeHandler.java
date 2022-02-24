package com.winning.ds.veda.configuration;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.net.URI;
import java.security.Principal;
import java.util.Map;

public class HandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        URI uri = request.getURI();
        String[] queries = uri.getQuery().split("&");
        Principal principal = null;
        String username = null;
        String role = null;
        for (String query:
                queries) {
            String[] items = query.split("=");
            if(items!=null && items.length == 2){
                if("username".equals(items[0])){
                    username=items[1];
                    continue;
                }

                if("role".equals(items[0])){
                    role=items[1];
                    continue;
                }

            }
        }
        if(username!=null && role !=null) {
            principal = new StompPrincipal(username,role);
        }
        return principal;
    }
}
