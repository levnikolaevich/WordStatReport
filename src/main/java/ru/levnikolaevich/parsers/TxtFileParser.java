package ru.levnikolaevich.parsers;

import ru.levnikolaevich.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by berezhnoy on 05.11.2016.
 *
 * Класс, предназначенный для парсинга txt файлов
 */
public class TxtFileParser extends Parser {

    /**
     * Конструктор класса TxtFileParser
     *
     * @param pathFile путь к файлу на локальном диске
     */
    public TxtFileParser(String pathFile) {
       super(pathFile);
    }

    /**
     * Разделить содержимое ресурса
     * @return набор слов List<String>
     */
    @Override
    protected List<String> SeparateSource() {
        List<String> words = new LinkedList<>();

        // TODO: 05.11.2016  Сделать конвертирование файла в UTF-8 налету

        try{
            File file = new File(pathFile);

            try(BufferedReader fin = new BufferedReader(new FileReader(file))) {
                words = Separator.separate(file.getName(),fin, Constants.wordValidator, Constants.wordSeparator);
            }

        } catch (IOException e) {
            logger.error("ФАЙЛ: " + pathFile + " НЕ НАЙДЕН");
            Parser.isInterrupted = true;
        }

        return words;
    }
}
