package com.example.imageuploder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomResponse> handleImageUploadException(ImageUploadException imageUploadException){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CustomResponse.builder()
                .message(imageUploadException.getMessage()).success(false).build());

    }

}
