package com.example.bookservice.exceptions;

public class BookNotFoundException extends RuntimeException {

    private static final String BOOK_NOT_FOUND_ERROR_MESSAGE = "BookId '%s' not found";

    public BookNotFoundException(String bookId) {
        super(String.format(BOOK_NOT_FOUND_ERROR_MESSAGE, bookId));
    }
}
