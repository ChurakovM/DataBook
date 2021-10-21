package com.example.userservice.models;

import lombok.Getter;

@Getter
public class VisitorContactQueryParameters {

    private final String firstName, lastName, email, phoneNumber;

    public VisitorContactQueryParameters(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
