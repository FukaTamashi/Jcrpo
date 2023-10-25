package com.example.lab6.controller;

import com.example.lab6.model.ExceptionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    @ExceptionHandler({InvalidParameterException.class})
    public ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException ex) {

        var exceptionModel = new ExceptionModel();

        exceptionModel.setMessage(ex.getMessage());
        exceptionModel.setError(HttpStatus.valueOf(400));

        logger.error(ex.getMessage());

        return new ResponseEntity<>(exceptionModel, exceptionModel.getError());

    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {

        var exceptionModel = new ExceptionModel();

        exceptionModel.setMessage("Exception" + ex.getMessage());
        exceptionModel.setError(HttpStatus.valueOf(500));

        logger.error("Error" + ex.getMessage());

        return new ResponseEntity<>(exceptionModel, exceptionModel.getError());
    }
}
