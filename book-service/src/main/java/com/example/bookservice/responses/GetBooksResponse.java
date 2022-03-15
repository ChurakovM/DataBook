package com.example.bookservice.responses;

import com.example.bookservice.models.BookModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetBooksResponse {

    private List<BookModel> listOfBooks;
}
