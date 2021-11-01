package com.example.authenticationservice.controllers;

import com.example.authenticationservice.requests.PostUserRequest;
import com.example.authenticationservice.requests.PostUserResponse;
import com.example.authenticationservice.services.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private final UserService userService;

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostUserResponse> createEndUser(@RequestBody @Valid PostUserRequest postUserRequest) {
        PostUserResponse createdEndUser = userService.createUser(postUserRequest);
        return new ResponseEntity<>(createdEndUser, HttpStatus.CREATED);
    }
}
