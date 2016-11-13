package ru.levnikolaevich.parsers;

import ru.levnikolaevich.WordContainsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс со статической функцией валидации Содержимого ресурса
 * Если в содержимом встречается контент, подходящий под регулярное выражение, возвращается Исключение
 */
public class Validator {

    /**
     * Провалидировать контент
     * @param line                   - контент на вход
     * @param regExpress             - регулярное выражение в качестве шаблона для проверки
     * @throws WordContainsException - если в тексте присутствует контент, подходящий под регулярное выражение, выбрасывается исключение
     */
    public static void validate(String line, String regExpress) throws WordContainsException {
        if(regExpress.length() > 0){
            Pattern p = Pattern.compile(regExpress);
            Matcher m = p.matcher(line);

            if(m.find()){
                throw new WordContainsException("В тексте ресурса присутствуют запрещенные символы: "+ regExpress);
            }
        }
    }
}
