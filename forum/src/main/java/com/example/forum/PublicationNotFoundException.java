package com.example.forum;

public class PublicationNotFoundException extends RuntimeException {
    public PublicationNotFoundException(String message) {
        super(message);
    }
}
