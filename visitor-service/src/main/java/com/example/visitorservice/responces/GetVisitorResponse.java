package com.example.visitorservice.responces;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVisitorResponse {

    private Long id;
    private String firstName, lastName, email, phoneNumber;
}
