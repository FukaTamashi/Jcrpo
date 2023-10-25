package com.example.lab6.model;

import org.springframework.http.HttpStatus;


public class ExceptionModel {

    private String message;

    private HttpStatus error;

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public HttpStatus getError() {return error;}

    public void setError(HttpStatus error) {this.error = error;}
}
