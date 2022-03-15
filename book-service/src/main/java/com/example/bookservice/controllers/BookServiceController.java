package com.example.bookservice.controllers;

import com.example.bookservice.requests.PostBookRequest;
import com.example.bookservice.responses.GetBookResponse;
import com.example.bookservice.responses.GetBooksResponse;
import com.example.bookservice.responses.PostBookResponse;
import com.example.bookservice.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("books")// http://localhost:8080/books-service/books
public class BookServiceController {

    private static final String BOOK_ID_PATH = "/{bookId}";
    private final BookService bookService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PostBookResponse> createBook(@Valid @RequestBody PostBookRequest request) {
        PostBookResponse postBookResponse = bookService.createBook(request);
        return new ResponseEntity<>(postBookResponse, HttpStatus.CREATED);
    }

    @GetMapping(
            path = BOOK_ID_PATH,
            consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GetBookResponse> getBook(@PathVariable String bookId) {
        GetBookResponse getBookResponse = bookService.getBook(bookId);
        return new ResponseEntity<>(getBookResponse, HttpStatus.OK);
    }

    @GetMapping(
            consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GetBooksResponse> getBooks() {
        GetBooksResponse getBooksResponse = bookService.getBooks();
        return new ResponseEntity<>(getBooksResponse, HttpStatus.OK);
    }
}
