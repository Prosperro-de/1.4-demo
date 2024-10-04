package org.example.service;

public class InputValidator {
    public static void isValid(String input) {
         if (input == null || input.isBlank()) {
             throw new IllegalArgumentException("Input cannot be null or empty");
         }
    }
}
