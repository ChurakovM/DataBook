package com.example.userservice.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutUserRequest {

    @NotNull(message = "First Name cannot be null")
    @Size(min = 1, message = "First Name length must be at least 1 character")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @Size(min = 1, message = "Last Name length must be at least 1 character")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "Phone Number cannot be null")
    @Size(min = 5, max = 30, message = "Phone Number length must be at least 5 characters and not longer than 30 characters")
    private String phoneNumber;
}
