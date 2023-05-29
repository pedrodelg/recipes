package com.mendix.recipes.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RecipesException exc){

        ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(exc.getMessage()).timeStamp(System.currentTimeMillis()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOExeption(RecipesException exc){

        ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exc.getMessage()).timeStamp(System.currentTimeMillis()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
