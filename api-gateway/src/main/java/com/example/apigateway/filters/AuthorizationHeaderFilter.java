package com.example.apigateway.filters;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@AllArgsConstructor
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Value("${token.secret}")
    private String tokenSecret;

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(AUTHORIZATION)) {
                return onError(exchange);
            }

            String authorizationHeader = request.getHeaders().get(AUTHORIZATION).get(0);
            String jwtToken = authorizationHeader.replace("Bearer ", "");

            boolean isTokenValid = isJwtTokenValid(jwtToken);

            if(!isTokenValid) {
                return onError(exchange);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private boolean isJwtTokenValid(String jwtToken) {
        String subject = null;
        try {
            subject = Jwts
                .parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
        } catch (SignatureException ex) {
            log.debug("JWT token is invalid", ex);
        }

        return !(subject == null || subject.isEmpty());
    }

    public static class Config {
        // Put configuration properties here
    }
}
