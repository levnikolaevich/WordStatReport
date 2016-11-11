package ru.levnikolaevich;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.levnikolaevich.parsers.HostParser;
import ru.levnikolaevich.parsers.Parser;
import ru.levnikolaevich.parsers.TxtFileParser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by berezhnoy on 05.11.2016.
 */
public class Main {

    /**
     * The constant threads - all threads of application.
     */
    private static Set<Thread> threads  = new HashSet<>();
    public volatile static boolean isInterrupted  = false;
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        //logger.error("someddddddddddddddddddddddddddddddddd");

        for (String path: Sources.sourcePaths) {
            Parser thread = (Parser) createThread(path);

            if(isInterrupted == true) break;

            threads.add(thread);
            thread.start();
        }
        long timeStart = System.currentTimeMillis();
        waitAllThreads();
        long time = System.currentTimeMillis() - timeStart;

        printReport();
        System.out.println("Время работы программы: " + time + " милисекунд");
    }

    /**
     * Check type of source
     *
     * @param path - path File
     */
    private static Thread createThread(String path)
    {
        Pattern p = Pattern.compile("^(https?:\\/\\/)?([\\w\\.]+)\\.([a-z]{2,6}\\.?)(\\/[\\w\\.]*)*\\/?$");
        Matcher m = p.matcher(path);

        if(m.matches())
            return new HostParser(path);

        if(path.endsWith(".txt"))
            return new TxtFileParser(path);

        isInterrupted = true;
        logger.error("Формат источника не поддерживается: {}", path);
        return null;
    }

    /**
     * Wait until all threads finish
     */
    private static void waitAllThreads() {

        Iterator<Thread> itr = threads.iterator();
        while (itr.hasNext()) {
            try {
                itr.next().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Print Report to Console
     */
    private static void printReport() {
        Map<String, Integer> treeMap = new TreeMap<>(Parser.monitor);
        for (String word : treeMap.keySet()) {
            logger.info("Слово: {} вхождений: {}", word.toUpperCase(), Parser.monitor.get(word));
        }
    }
}
