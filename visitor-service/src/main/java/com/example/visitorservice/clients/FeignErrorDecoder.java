package com.example.visitorservice.clients;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
            case 404:
            case 500:
            case 503:
                if (methodKey.contains("getBooks")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Books Service is unavailable");
                }
            default:
                return new Exception(response.reason());
        }
    }
}
