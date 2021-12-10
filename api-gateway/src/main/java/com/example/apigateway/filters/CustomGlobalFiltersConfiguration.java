package com.example.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class CustomGlobalFiltersConfiguration {

    @Order(2)
    @Bean
    public GlobalFilter secondPreFilter() {
        return ((exchange, chain) -> {
            log.info("Second Global Pre Filter has been executed...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Second Global Post Filter has been executed...");
            }));
        });
    }

    @Order(3)
    @Bean
    public GlobalFilter thirdPreFilter() {
        return ((exchange, chain) -> {
            log.info("Third Global Pre Filter has been executed...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Third Global Post Filter has been executed...");
            }));
        });
    }
}
