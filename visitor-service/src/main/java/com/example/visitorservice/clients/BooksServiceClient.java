package com.example.visitorservice.clients;

import bookservice.responses.GetBooksResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "books-service", fallbackFactory = BookServiceFallbackFactory.class)
public interface BooksServiceClient {

    @GetMapping(
        path = "/books?visitorId={visitorId}",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    GetBooksResponse getBooks(@PathVariable String visitorId);
}
