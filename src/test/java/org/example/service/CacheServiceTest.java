package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceTest {
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = CacheService.getInstance();
    }

    @Test
    void testGetInstanceReturnsSameInstance() {
        CacheService firstInstance = CacheService.getInstance();
        CacheService secondInstance = CacheService.getInstance();
        assertEquals(firstInstance, secondInstance);
    }

    @Test
    void testSaveResultInCache() {
        String input = "test";
        Map<Character, Integer> result = new HashMap<>();
        result.put('t', 2);
        result.put('e', 1);

        cacheService.save(input, result);

        assertTrue(cacheService.isPresent(input));
        assertEquals(Optional.of(result), cacheService.get(input));
    }

    @Test
    void testIsPresentWhenResultWasProcessedBefore() {
        cacheService.save("Hello", new HashMap<>());

        assertTrue(cacheService.isPresent("Hello"));
    }

    @Test
    void testIsPresentWhenResultWasNotProcessedBefore() {
        assertFalse(cacheService.isPresent("Hello"));
    }

    @Test
    void testGetResultResultWhenResultWanNotProcessedBefore() {
        assertEquals(Optional.empty(), cacheService.get("Hello"));
    }
}
