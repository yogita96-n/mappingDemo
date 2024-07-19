package com.example.meraApi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> IdNotFoundhandler(IdNotFoundException idException){
       return new ResponseEntity<>(idException.getMessage(), HttpStatus.OK);

    }
    @ExceptionHandler(StopIdNotFoundEx.class)
    public ResponseEntity<String>stopIdHandlerEx(StopIdNotFoundEx foundEx){
       return new ResponseEntity<>(foundEx.getMessage(),HttpStatus.OK);
    }
}
