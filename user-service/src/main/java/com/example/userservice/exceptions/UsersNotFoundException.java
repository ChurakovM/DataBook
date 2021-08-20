package com.example.userservice.exceptions;

public class UsersNotFoundException extends UserServiceException{

    private static final String USERS_NOT_FOUND_ERROR_MESSAGE = "UserId '%s' not found";

    public UsersNotFoundException() {
        super(USERS_NOT_FOUND_ERROR_MESSAGE);
    }
}
