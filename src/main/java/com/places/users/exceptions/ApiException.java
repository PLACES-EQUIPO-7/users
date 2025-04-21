package com.places.users.exceptions;

import com.places.users.utils.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final ErrorCode errorCode;

    public ApiException(String message) {
        super(message);
        this.errorCode = ErrorCode.USER_NOT_FOUND;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(String message, HttpStatus httpStatus, ErrorCode errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
