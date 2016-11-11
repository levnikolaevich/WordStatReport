package ru.levnikolaevich.parsers;

import ru.levnikolaevich.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by berezhnoy on 05.11.2016.
 */
public abstract class Parser extends Thread {

    public static Map monitor = new HashMap<String, Integer>();
    protected String pathFile;

    public Parser(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public void run() {

        AddReport(SeparateSource());
    }

    /**
     * Instantiates a new Parser.
     *
     * @param words  the List of words
     */
    private void AddReport(List<String> words) {
        Integer count;
        synchronized (monitor)
        {
            for (String word : words) {
                if(Main.isInterrupted){
                    System.out.println("Прервано заполнение словаря. Поток: " + this.getName());
                    break;
                }

                word = word.toLowerCase();

                count = (Integer)monitor.get(word);
                if(count != null)
                {
                    count++;
                    monitor.put(word, count);
                    continue;
                }
                monitor.put(word, 1);

            }
        }
    }

    /**
     *
     * @return ArrayList String - list of words from resources
     */
    abstract public List<String> SeparateSource();
}
