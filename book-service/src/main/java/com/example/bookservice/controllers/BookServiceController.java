package com.example.bookservice.controllers;

import bookservice.queries.BookQueryParameters;
import bookservice.requests.PostAssignBookRequest;
import bookservice.requests.PostBookRequest;
import bookservice.responses.BookDetailsResponse;
import bookservice.responses.GetBooksResponse;
import bookservice.responses.PostBookResponse;
import bookservice.responses.PostAssignBookResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<BookDetailsResponse> getBook(@PathVariable String bookId) {
        BookDetailsResponse bookDetailsResponse = bookService.getBook(bookId);
        return new ResponseEntity<>(bookDetailsResponse, HttpStatus.OK);
    }

    @GetMapping(
            consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GetBooksResponse> getBooks(
            @RequestParam(value = "authorName", defaultValue = "", required = false) String authorName,
            @RequestParam(value = "bookName", defaultValue = "", required = false) String bookName,
            @RequestParam(value = "visitorId", defaultValue = "", required = false) String visitorId) {
        BookQueryParameters queryParameters = new BookQueryParameters(authorName, bookName, visitorId);
        GetBooksResponse getBooksResponse = bookService.getBooks(queryParameters);
        return new ResponseEntity<>(getBooksResponse, HttpStatus.OK);
    }

    @PostMapping(
            path = "/assign-book",
            consumes = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<PostAssignBookResponse> assignBookToVisitor(@Valid @RequestBody PostAssignBookRequest request) {
        PostAssignBookResponse postAssignBookResponse = bookService.updateBook(request);
        return new ResponseEntity<>(postAssignBookResponse, HttpStatus.OK); // TODO is it ok to give 200 in this case?
    }
}
