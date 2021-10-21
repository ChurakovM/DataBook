package com.example.userservice.exceptions;

public class VisitorsNotFoundException extends VisitorServiceException {

    private static final String VISITORS_NOT_FOUND_ERROR_MESSAGE = "No visitors were found in the DB";

    public VisitorsNotFoundException() {
        super(VISITORS_NOT_FOUND_ERROR_MESSAGE);
    }
}
