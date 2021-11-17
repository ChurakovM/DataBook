package com.example.visitorservice.exceptions;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),
            ex.getLocalizedMessage() == null ? ex.toString() : ex.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException() {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),
            "Null Pointer Exception was thrown in your request");
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {VisitorServiceException.class})
    public ResponseEntity<Object> handleVisitorServiceException(VisitorServiceException ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {VisitorNotFoundException.class})
    public ResponseEntity<Object> handleVisitorNotFoundException(VisitorNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), NOT_FOUND);
    }
}
