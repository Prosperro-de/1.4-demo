package org.example.exception;

public class CacheNotFoundException extends RuntimeException {
    public CacheNotFoundException(String message) {
        super(message);
    }
}
