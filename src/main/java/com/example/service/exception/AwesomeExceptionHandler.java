package com.example.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchShopException.class)
    protected ResponseEntity<AwesomeException> handleThereIsNoSuchShopException() {
        return new ResponseEntity<>(new AwesomeException("There is no such shop"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ThereIsNoSuchProductException.class)
    protected ResponseEntity<AwesomeException> handleThereIsNoSuchProductException() {
        return new ResponseEntity<>(new AwesomeException("There is no such product"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ThereIsNoSuchPersonException.class)
    protected ResponseEntity<AwesomeException> handleThereIsNoSuchPersonException() {
        return new ResponseEntity<>(new AwesomeException("There is no such person"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueShopNameException.class)
    protected ResponseEntity<AwesomeException> handleUniqueShopNameException() {
        return new ResponseEntity<>(new AwesomeException("Shop with such name exists"), HttpStatus.CREATED);
    }

    @ExceptionHandler(ThereIsNoSuchCartException.class)
    protected ResponseEntity<AwesomeException> handleThereIsNoSuchCartException() {
        return new ResponseEntity<>(new AwesomeException("There is no such cart"), HttpStatus.CREATED);
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;
    }
}
