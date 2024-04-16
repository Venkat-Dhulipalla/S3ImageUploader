package com.example.imageuploder.exceptions;

import java.io.IOException;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException(String message) {
        super(message);
    }
}
