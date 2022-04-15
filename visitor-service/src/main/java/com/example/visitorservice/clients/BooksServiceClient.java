package com.example.visitorservice.clients;

import bookservice.responses.GetBooksResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.ArrayList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "books-service", fallbackFactory = BookServiceFallbackFactory.class)
@FeignClient(name = "books-service")
public interface BooksServiceClient {

    @GetMapping(
        path = "/books?visitorId={visitorId}",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Retry(name = "books-service")
    @CircuitBreaker(name = "books-service", fallbackMethod = "getBooksFallback")
    GetBooksResponse getBooks(@PathVariable String visitorId);

    default GetBooksResponse getBooksFallback(String visitorId, Throwable exception) {
        System.out.println("Fallback method for getting visitors' books has been triggered. Exception: " + exception.getLocalizedMessage());
        GetBooksResponse response = new GetBooksResponse();
        response.setListOfBooks(new ArrayList<>());
        return response;
    }
}
