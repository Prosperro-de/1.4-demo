package org.example.service;

import org.example.exception.CacheNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LetterCounterTest {

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private LetterCounter letterCounter;

    @Test
    void testGetLetterFrequency() {
        String input = "Hello Java";
        Map<Character, Integer> expectedResult = getHelloJavaExpectedResult();
        when(cacheService.isPresent(input)).thenReturn(false);

        Map<Character, Integer> result = letterCounter.getLetterFrequency(input);

        assertEquals(expectedResult, result);
        verify(cacheService).save(input, expectedResult);
        verify(cacheService).isPresent(input);
        verify(cacheService, times(0)).get(input);
    }

    @Test
    void testGetLetterFrequencyReturnsCashedResult() {
        String input = "Hello Java";
        Map<Character, Integer> cachedResult = getHelloJavaExpectedResult();
        when(cacheService.isPresent(input)).thenReturn(true);
        when(cacheService.get(input)).thenReturn(Optional.of(cachedResult));

        Map<Character, Integer> result = letterCounter.getLetterFrequency(input);

        assertEquals(cachedResult, result);
        verify(cacheService).get(input);
        verify(cacheService).isPresent(input);
        verify(cacheService, times(0)).save(input, cachedResult);
    }

    @Test
    void testGetLetterFrequencyThrowsExceptionIfCacheNotFound() {
        String input = "Hello Java";
        when(cacheService.isPresent(input)).thenReturn(true);
        when(cacheService.get(input)).thenReturn(Optional.empty());

        assertThrows(CacheNotFoundException.class, () -> letterCounter.getLetterFrequency(input));
    }

    private static Map<Character, Integer> getHelloJavaExpectedResult() {
        Map<Character, Integer> cachedResult = new LinkedHashMap<>();
        cachedResult.put('H', 1);
        cachedResult.put('e', 1);
        cachedResult.put('l', 2);
        cachedResult.put('o', 1);
        cachedResult.put(' ', 1);
        cachedResult.put('J', 1);
        cachedResult.put('v', 1);
        cachedResult.put('a', 2);
        return cachedResult;
    }
}