package com.example.imageuploder.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomResponse {
    private String message;
    private boolean success;
}