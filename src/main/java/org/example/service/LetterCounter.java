package org.example.service;

import org.example.exception.CacheNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

public class LetterCounter {
    private final CacheService cacheService;

    public LetterCounter(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public Map<Character, Integer> getLetterFrequency(String input) {
        if (cacheService.isPresent(input)) {
            return cacheService.get(input)
                    .orElseThrow(() -> new CacheNotFoundException("Cache not found"));
        }
        var result = countLetterFrequency(input);
        cacheService.save(input, result);
        return result;
    }

    private Map<Character, Integer> countLetterFrequency(String input) {
        Map<Character, Integer> charCountMap = new LinkedHashMap<>();
        for (char ch : input.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }
        return charCountMap;
    }
}
