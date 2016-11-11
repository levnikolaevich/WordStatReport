package ru.levnikolaevich.parsers;

import ru.levnikolaevich.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validate(String line, String regExpress)
    {

        if(Constants.wordValidator.length() > 0){
            Pattern p = Pattern.compile(regExpress);
            Matcher m = p.matcher(line);

            if(m.find()){
               return true;
            }
        }
        return false;
    }

}
