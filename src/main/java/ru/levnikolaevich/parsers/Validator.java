package ru.levnikolaevich.parsers;

import ru.levnikolaevich.WordContainsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validate(String line, String regExpress) throws WordContainsException {
        if(regExpress.length() > 0){
            Pattern p = Pattern.compile(regExpress);
            Matcher m = p.matcher(line);

            if(m.find()){
                throw new WordContainsException("В тексте ресурса присутствуют запрещенные символы");
            }
        }
        return false;
    }

}
