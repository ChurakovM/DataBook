package com.example.apigateway.filters;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomPreFilter implements GlobalFilter, Ordered {

    // @Order(1) will not work as expected, why??
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global Pre Filter has been executed");

        String requestPath = exchange.getRequest().getPath().toString();
        log.info("Request path = " + requestPath);

        HttpHeaders headers = exchange.getRequest().getHeaders();
        Set<String> headerNames = headers.keySet();
        headerNames
            .forEach(headerName -> log.info("Header '" + headerName + "' has this value: " + headers.get(headerName)));

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
