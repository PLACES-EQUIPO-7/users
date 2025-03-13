package com.places.users.exceptions;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends ApiException {

    public DataNotFoundException(String message){
        super(message);
        this.setHttpStatus(HttpStatus.NOT_FOUND);
    }
}
