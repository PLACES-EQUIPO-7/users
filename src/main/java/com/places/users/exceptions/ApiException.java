package com.places.users.exceptions;

import com.places.users.utils.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private HttpStatus httpStatus;

    private ErrorCode errorCode;

    public ApiException(String message) {
        super(message);
    }
}
