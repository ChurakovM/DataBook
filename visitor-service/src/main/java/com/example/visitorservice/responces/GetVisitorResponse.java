package com.example.visitorservice.responces;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVisitorResponse {

    private String id, firstName, lastName, email, phoneNumber;
}
