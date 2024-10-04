package org.example.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CacheService {
    private static CacheService INSTANCE;
    private static Map<String, Map<Character, Integer>> cache = new HashMap<>();

    private CacheService() {
    }

    public static CacheService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CacheService();
            return INSTANCE;
        }
        return INSTANCE;
    }

    public void save(String input, Map<Character, Integer> result) {
        cache.put(input, result);
    }

    public boolean isPresent(String input) {
        return cache.containsKey(input);
    }

    public Optional<Map<Character, Integer>> get(String input) {
        return Optional.ofNullable(cache.get(input));
    }
}
