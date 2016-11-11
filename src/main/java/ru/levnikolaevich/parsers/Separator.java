package ru.levnikolaevich.parsers;

import ru.levnikolaevich.*;
import ru.levnikolaevich.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by berezhnoy on 06.11.2016.
 */
public class Separator {

    public int summ(int arg1, int arg2){
        return arg1+arg2;
    }

    public void doSome(int arg) throws Exception{
        if(arg == 5)
            throw new RuntimeException();
    }


    public static List<String> separateSource(String sourceName, BufferedReader fin)
    {
        String line;
        String[] wordsLine;
        List<String> words = new ArrayList<String>();

        try {
            while ((line = fin.readLine()) != null && !Main.isInterrupted) {

                if(Constants.wordValidator.length() > 0){
                    Pattern p = Pattern.compile(Constants.wordValidator);
                    Matcher m = p.matcher(line);

                    if(m.find()){
                        Main.isInterrupted = true;
                        throw new WordContainsException("В тексте ресурса" + sourceName + " присутствуют запрещенные символы");
                    }
                }
                wordsLine = line.split(Constants.wordSeparator);
                for (String word:  wordsLine ) {
                    if(Main.isInterrupted) break;

                    if(word.length()>0){
                        System.out.println(sourceName + " - слово: " + word.toUpperCase());
                        words.add(word.trim());
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("IO ошибка на этапе работы Сепаратора слов");
            Main.isInterrupted = true;
        } catch(WordContainsException ex)
        {
            System.out.println(ex.getMessage());
            Main.isInterrupted = true;
        }
        return words;
    }
}
