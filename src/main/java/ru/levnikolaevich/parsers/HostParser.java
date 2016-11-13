package ru.levnikolaevich.parsers;

import ru.levnikolaevich.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by berezhnoy on 05.11.2016.
 *
 * Класс, предназначенный для парсинга удаленных ресурсов
 */
public class HostParser extends Parser {

    /**
     * Конструктор класса HostParser
     *
     * @param pathFile путь к файлу на локальном диске
     */
    public HostParser(String pathFile)
    {
       super(pathFile);
    }

    /**
     * Разделить содержимое ресурса
     * @return набор слов List<String>
     */
    @Override
    public List<String> SeparateSource() {
        List<String> words = new LinkedList<>();

        try{
            URL url = new URL(pathFile);

            try(BufferedReader fin = new BufferedReader(new InputStreamReader(url.openStream()))) {
                words = Separator.separate(url.toString(),fin, Constants.wordValidator, Constants.wordSeparator);
            }

        } catch (IOException e) {
            logger.error("Удаленый ресурс: " + pathFile + " НЕ ДОСТУПЕН");
            Parser.isInterrupted = true;
        }

        return words;
    }
}
