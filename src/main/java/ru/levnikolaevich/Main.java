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
 * Created by Бережной Лев, группа 2 on 05.11.2016.
 *
 * Программа предназначена для поиска числа вхождений слов в тексте (вариант 1 домашнего задания)
 * Поддерживается обработка данных одновременно из нескольких источников
 *
 * Возможные источники
 *      Ссылки на сайт
 *      Текстовые файлы в формате UTF-8, расположенные на локальном ресурсе
 *
 * Ограничения
 *      Программа будет остановлена, если:
 *          Формат  источника не поддерживается
 *
 */
public class Main {

    /**
     * Константы:
     * @param threads - набор потоков
     * @param isInterrupted - набор потоков
     * @param logger - логгер основного потока
     */
    private static Set<Thread> threads  = new HashSet<>();
    public volatile static boolean isInterrupted  = false;
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Главный метод приложения (входная точка)
     *
     * @param args - если входящих параметров нет, то подхватываются данные из класса Sources
     */
    public static void main(String[] args) {
        boolean flag = logger.isDebugEnabled();
        if (logger.isDebugEnabled()) {
            logger.debug("Debug Mode is ON");
        }

        if(args.length == 0 && Sources.sourcePaths.length > 0){
            int i = 0;
            args = new String[Sources.sourcePaths.length];
            for (String path: Sources.sourcePaths) {
                args[i] = path;
                i++;
            }
        }

        for (String path: args) {
            Parser thread = (Parser) createThread(path);

            if(isInterrupted == true) break;

            threads.add(thread);
            thread.start();
        }
        long timeStart = System.currentTimeMillis();
        waitAllThreads();
        long time = System.currentTimeMillis() - timeStart;

        printReport();
        logger.error("Время работы программы: " + time + " милисекунд");
    }

    /**
     * Проверка типа источника и создания соответствующего потока.
     * Если формат источника не известен, идет прерывание хода исполнения программы
     *
     * @param path - path File
     * @return Thread - поток, в котором обрабатывается источник
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
