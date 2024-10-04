package org.example;

import org.example.service.CacheService;
import org.example.service.InputValidator;
import org.example.service.LetterCounter;

import java.util.Scanner;

public class ApplicationRunner {
    private LetterCounter letterCounter = new LetterCounter(CacheService.getInstance());

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();
        InputValidator.isValid(input);
        var result = letterCounter.getLetterFrequency(input);
        System.out.println(result);
    }
}
