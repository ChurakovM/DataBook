package com.example.authenticationservice.controllers;

import com.example.authenticationservice.models.EndUserModel;
import com.example.authenticationservice.requests.PostEndUserRequest;
import com.example.authenticationservice.services.EndUserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final EndUserService endUserService;

    @GetMapping("/login")
    public String testApi() {
        return "Test user login method is working";
    }

    @PostMapping
    public ResponseEntity<EndUserModel> createEndUser(@RequestBody @Valid PostEndUserRequest postEndUserRequest) {
        EndUserModel createdEndUser = endUserService.createEndUser(postEndUserRequest);
        return new ResponseEntity<>(createdEndUser, HttpStatus.CREATED);
    }
}
