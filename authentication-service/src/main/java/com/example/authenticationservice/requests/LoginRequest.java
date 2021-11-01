package com.example.authenticationservice.requests;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "User Name cannot be null")
    private String userName;

    @NotNull(message = "Password cannot be null")
    private String password;
}
