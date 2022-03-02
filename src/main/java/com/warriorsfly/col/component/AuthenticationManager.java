package com.warriorsfly.col.component;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JWTUtil jwt;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        String username;
        try {
            username = jwt.getSubject(token);
        } catch (Exception e) {
            username = null;
        }
        if (username != null && jwt.validate(token)) {
            Claims claims = jwt.getClaims(token);
            List<String> roles = claims.get("roles", List.class);
         
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    roles.stream().map(role -> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toList()));
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }   
}
