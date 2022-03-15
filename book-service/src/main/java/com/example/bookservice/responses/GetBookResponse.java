package com.example.bookservice.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBookResponse {

    private String id, authorName, bookName;
}
