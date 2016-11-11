package ru.levnikolaevich.parsers;

import ru.levnikolaevich.Constants;
import ru.levnikolaevich.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by berezhnoy on 05.11.2016.
 */
public class HostParser extends Parser {

    public HostParser(String pathFile)
    {
       super(pathFile);
    }

    @Override
    public List<String> SeparateSource() {
        List<String> words = new LinkedList<>();

        try{
            URL url = new URL(pathFile);

            try(BufferedReader fin = new BufferedReader(new InputStreamReader(url.openStream()))) {
                words = Separator.separateSource(url.toString(),fin, Constants.wordValidator);
            }

        } catch (IOException e) {
            System.out.println("Удаленый ресурс: " + pathFile + " НЕ ДОСТУПЕН");
            Main.isInterrupted = true;
        }

        return words;
    }
}
