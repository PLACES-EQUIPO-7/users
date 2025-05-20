package com.places.users.controller;

import com.places.users.DTOs.ErrorResponseDTO;
import com.places.users.exceptions.DataNotFoundException;
import com.places.users.exceptions.UnAuthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataNotFoundException(DataNotFoundException e) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(e.getMessage());
        errorResponseDTO.setCode(e.getErrorCode());

        return ResponseEntity.status(e.getHttpStatus()).body(errorResponseDTO);
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUnAuthorizedException(UnAuthorizedException e) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(e.getMessage());
        errorResponseDTO.setCode(e.getErrorCode());

        return ResponseEntity.status(e.getHttpStatus()).body(errorResponseDTO);
    }
}
