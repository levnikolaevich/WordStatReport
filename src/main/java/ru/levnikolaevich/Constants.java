package ru.levnikolaevich;

/**
 * Список констант для работы приложения
 * Перед началом тестирования регулярное вырожение для валидатор лучше выставить на пустую строку.
 */

public class Constants {
    public static String wordSeparator = "\\s*[^а-яА-Я0-9]+\\s*";
   // public static String wordValidator = "[a-zA-Z]";
    public static String wordValidator = "";   //для тестов лучше раскомментировать эту строчку и закомментировать строчку выше. В ином случае набор источников для проведения тестов придется составить самостоятельно
}
