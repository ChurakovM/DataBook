package com.example.userservice.exceptions;

public class UserNotFoundException extends UserServiceException {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "User not found";

    public UserNotFoundException() {
        super(USER_NOT_FOUND_ERROR_MESSAGE);
    }
}
