package ru.levnikolaevich.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by berezhnoy on 05.11.2016.
 *
 * Поток, обрабатывающий ичтоники и формирующий результирующий Map
 */
public abstract class Parser extends Thread {

    protected static Logger logger = LoggerFactory.getLogger(Parser.class);
    public static Map monitor = new HashMap<String, Integer>();
    public volatile static boolean isInterrupted  = false;
    protected String pathFile;

    public Parser(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public void run() {

        AddReport(SeparateSource());
    }

    /**
     * Добавление набора слов в общий для всех потоков словарь с подсчетом числа вхожденй данного слова
     *      Обеспечено потоко-защищенное обращение к словарю
     *
     * @param words  the List of words
     */
    private void AddReport(List<String> words) {
        Integer count;
        synchronized (monitor)
        {
            for (String word : words) {
                if(isInterrupted){
                    logger.error("Прервано заполнение словаря. Поток: " + this.getName());
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
     * Разделение файлового контента на отдельные слова
     *
     * @return набор слов
     */
    protected List<String> SeparateSource() {
        return null;
    }
}
