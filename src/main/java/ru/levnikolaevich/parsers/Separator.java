package ru.levnikolaevich.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.levnikolaevich.Constants;
import ru.levnikolaevich.Main;
import ru.levnikolaevich.WordContainsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by berezhnoy on 06.11.2016.
 */
public class Separator {

    private static Logger logger = LoggerFactory.getLogger(Separator.class);

    public static List<String> separateSource(String sourceName, BufferedReader fin, String regExpress) {
        String line;
        String[] wordsLine;
        List<String> words = new ArrayList<>();

        try {
            while ((line = fin.readLine()) != null && !Main.isInterrupted) {

                if (Validator.validate(line, regExpress)) {
                    Main.isInterrupted = true;
                    throw new WordContainsException("В тексте ресурса" + sourceName + " присутствуют запрещенные символы");
                }

                wordsLine = line.split(Constants.wordSeparator);
                for (String word : wordsLine) {
                    if (Main.isInterrupted) break;

                    if (word.length() > 0) {

                        logger.info(sourceName + " - слово: " + word.toUpperCase());
                        words.add(word.trim());

                    }
                }
            }

        } catch (IOException ex) {
            logger.error("IO ошибка на этапе работы Сепаратора слов");
            Main.isInterrupted = true;
        } catch (WordContainsException ex) {
            logger.error(ex.getMessage());
            Main.isInterrupted = true;
        }
        return words;
    }
}
