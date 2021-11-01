package com.example.visitorservice.responces;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostVisitorResponse {

    private Long id;
    private String firstName, lastName, email, phoneNumber;
}
