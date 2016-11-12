package ru.levnikolaevich.parsers;

import ru.levnikolaevich.Constants;
import ru.levnikolaevich.Main;

import java.io.*;
import java.util.*;

/**
 * Created by berezhnoy on 05.11.2016.
 */
public class TxtFileParser extends Parser {

    /**
     * Instantiates a new File parser.
     *
     * @param pathFile the path file
     */
    public TxtFileParser(String pathFile) {
       super(pathFile);
    }

    /**
     *
     * @return List of words from source
     */
    protected List<String> SeparateSource() {
        List<String> words = new LinkedList<>();

        // TODO: 05.11.2016  Сделать конвертирование файла в UTF-8 налету

        try{
            File file = new File(pathFile);

//            try(BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(file), "cp1251"))) {
//                String line1 = fin.readLine();
//                String line2 = fin.readLine();
//
//            }

            try(BufferedReader fin = new BufferedReader(new FileReader(file))) {
                words = Separator.separateSource(file.getName(),fin, Constants.wordValidator, Constants.wordSeparator);
            }

        } catch (IOException e) {
            logger.error("ФАЙЛ: " + pathFile + " НЕ НАЙДЕН");
            Main.isInterrupted = true;
        }

        return words;
    }
}
