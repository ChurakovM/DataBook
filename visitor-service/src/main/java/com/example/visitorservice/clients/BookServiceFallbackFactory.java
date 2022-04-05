package com.example.visitorservice.clients;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class BookServiceFallbackFactory implements FallbackFactory<BooksServiceClient> {

    @Override
    public BooksServiceClient create(Throwable cause) {
        return new BookServiceFallback(cause);
    }
}
