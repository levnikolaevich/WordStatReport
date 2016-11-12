package ru.levnikolaevich.parsers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SeparatorTest {
    private BufferedReader bufferedReader = null;
    private String validatePattern;
    private String separatePattern;

    @Before
    public void setUp() {
        bufferedReader = Mockito.mock(BufferedReader.class);
        validatePattern = "";
        separatePattern = "\\s*[^а-яА-Я]+\\s*";
    }

    @Test
    public void test_separate_source_empty() throws IOException {
        Mockito.when(bufferedReader.readLine()).thenReturn("line1: word11 word12, word13, ! word14", "line2: word21 word22, word23, ! word24", "line3: word31 word32, word33, ! word34", null);
        List<String> result = Separator.separateSource("SourceName", bufferedReader, validatePattern, separatePattern);
        Assert.assertEquals(new ArrayList<String>(), result);
    }

    @Test
    public void test_separate_source_success() throws IOException {
        List<String> expected = new ArrayList<String>() {{
            add("строка");
            add("слово");
            add("слово");
            add("слово");
            add("слово");
            add("слово");
            add("слово");
            add("слово");
        }};

        Mockito.when(bufferedReader.readLine()).thenReturn("строка-1: слово-11 слово-12, слово-13, ! слово-14", "line-2: слово-21 слово-22, слово-23, ! word24", null);
        List<String> result = Separator.separateSource("SourceName", bufferedReader, validatePattern, separatePattern);
        Assert.assertEquals(expected, result);
    }
}
