package com.places.users.exceptions;

import com.places.users.utils.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends ApiException {

    public UnAuthorizedException(String message, ErrorCode errorCode){
        super(message, HttpStatus.UNAUTHORIZED, errorCode);
    }
}
