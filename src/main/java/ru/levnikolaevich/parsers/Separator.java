package ru.levnikolaevich.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.levnikolaevich.WordContainsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс со статической функцией разделения Содержимого ресурса
 */
public class Separator {

    private static Logger logger = LoggerFactory.getLogger(Separator.class);

    /**
     * Разделить тестовое содержимое ресурса на отдельные слова
     *
     * @param sourceName        - имя ресурса
     * @param fin               - содержимое ресурса
     * @param validateExpress   - регулярное выражение для управление правилом валидации контента
     * @param separateExpress   - регулярное выражение для управление правилом разделения контента
     * @return List<String>     - возвращает List слов
     */
    public static List<String> separate(String sourceName, BufferedReader fin, String validateExpress, String separateExpress) {
        String line;
        String[] wordsLine;
        List<String> words = new ArrayList<>();

        try {
            while ((line = fin.readLine()) != null && !Parser.isInterrupted) {

                Validator.validate(line, validateExpress);

                wordsLine = line.split(separateExpress);
                for (String word : wordsLine) {
                    if (Parser.isInterrupted) break;

                    if (word.length() > 0) {
                        logger.info(sourceName + " - слово: " + word.toUpperCase());
                        words.add(word.trim());
                    }
                }
            }

        } catch (IOException ex) {
            logger.error("IO ошибка на этапе работы Сепаратора слов");
            Parser.isInterrupted = true;
        } catch (WordContainsException ex) {
            logger.error(ex.getMessage() + " : " + sourceName);
            Parser.isInterrupted = true;
        }
        return words;
    }
}
