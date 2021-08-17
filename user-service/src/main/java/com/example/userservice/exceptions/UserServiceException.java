package com.example.userservice.exceptions;

// TODO why do we implement this exception in this way??
public class UserServiceException extends RuntimeException {

    public UserServiceException(String message) {
        super(message);
    }
}
