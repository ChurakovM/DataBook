package com.example.userservice.exceptions;

public class UsersNotFoundException extends UserServiceException{

    private static final String USERS_NOT_FOUND_ERROR_MESSAGE = "No users were found in the DB";

    public UsersNotFoundException() {
        super(USERS_NOT_FOUND_ERROR_MESSAGE);
    }
}
