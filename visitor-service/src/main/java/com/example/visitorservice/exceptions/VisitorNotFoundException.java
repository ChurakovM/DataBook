package com.example.visitorservice.exceptions;

public class VisitorNotFoundException extends VisitorServiceException {

    private static final String VISITOR_NOT_FOUND_ERROR_MESSAGE = "VisitorId '%s' not found";

    public VisitorNotFoundException(String visitorId) {
        super(String.format(VISITOR_NOT_FOUND_ERROR_MESSAGE, visitorId));
    }
}
