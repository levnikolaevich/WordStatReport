package ru.levnikolaevich.parsers;

import org.junit.Test;
import ru.levnikolaevich.WordContainsException;

public class ValidatorTest {
    private String validatePattern = "";

    @Test(expected = WordContainsException.class)
    public void test_validator_exception() throws WordContainsException {
        validatePattern = "[a-zA-Z]";
        String line = "line-2: слово-21 слово-22, слово-23, ! word24";
        Validator.validate(line, validatePattern);
    }

    @Test
    public void test_validator_success() throws WordContainsException {
        validatePattern = "";
        String line = "line-2: слово-21 слово-22, слово-23, ! word24";
        Validator.validate(line, validatePattern);
    }
}

