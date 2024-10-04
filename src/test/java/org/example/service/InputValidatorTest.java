package org.example.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;


class InputValidatorTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("getInputValues")
    void testIsValid(String name, String input) {
        assertThrows(IllegalArgumentException.class, () -> InputValidator.isValid(input));
        assertThrows(IllegalArgumentException.class, () -> InputValidator.isValid(input));
        assertThrows(IllegalArgumentException.class, () -> InputValidator.isValid(input));
    }


    public static Stream<Arguments> getInputValues() {
        return Stream.of(
                Arguments.of("Null input", null),
                Arguments.of("Empty input", ""),
                Arguments.of("Blank input", "   ")
        );
    }
}