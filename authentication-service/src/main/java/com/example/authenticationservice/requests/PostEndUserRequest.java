package com.example.authenticationservice.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEndUserRequest {

    private static final int MINIMAL_USER_NAME_LENGTH = 6;
    private static final int MINIMAL_PASSWORD_LENGTH = 9;
    private static final int MAXIMUM_PASSWORD_LENGTH = 15;

    @NotNull(message = "User name cannot be null")
    @Size(min = MINIMAL_USER_NAME_LENGTH, message = "User Name cannot be less than "
        + MINIMAL_USER_NAME_LENGTH + " characters")
    private String userName;

    @NotNull(message = "Password cannot be null")
    @Size(min = MINIMAL_PASSWORD_LENGTH, max = MAXIMUM_PASSWORD_LENGTH, message = "Password cannot be less than "
        + MINIMAL_PASSWORD_LENGTH + " characters and more than "
        + MAXIMUM_PASSWORD_LENGTH + " characters")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
}
