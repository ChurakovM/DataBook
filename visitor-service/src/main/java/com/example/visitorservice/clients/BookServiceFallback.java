package com.example.visitorservice.clients;

import bookservice.responses.GetBooksResponse;
import feign.FeignException;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookServiceFallback implements BooksServiceClient {

    private final Throwable cause;

    BookServiceFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public GetBooksResponse getBooks(String visitorId) {
        if (cause instanceof FeignException) {
            log.error("Something went wrong. Error message:" + cause.getLocalizedMessage());
        } else {
            log.error("There is a failure from Book Service. Error message: " + cause.getLocalizedMessage());
        }

        GetBooksResponse response = new GetBooksResponse();
        response.setListOfBooks(new ArrayList<>());
        return response;
    }
}
