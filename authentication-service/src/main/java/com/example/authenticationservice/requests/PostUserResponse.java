package com.example.authenticationservice.requests;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUserResponse {

    @NotNull
    private String userName, email;
}
