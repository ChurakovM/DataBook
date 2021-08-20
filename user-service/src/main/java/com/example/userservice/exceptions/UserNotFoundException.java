package com.example.userservice.exceptions;

public class UserNotFoundException extends UserServiceException {

    private static final String USER_NOT_FOUND_ERROR_MESSAGE = "UserId '%s' not found";

    public UserNotFoundException(String userId) {
        super(String.format(USER_NOT_FOUND_ERROR_MESSAGE, userId));
    }
}
