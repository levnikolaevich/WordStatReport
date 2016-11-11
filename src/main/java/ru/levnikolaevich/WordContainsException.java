package ru.levnikolaevich;

/**
 * Created by berezhnoy on 07.11.2016.
 */
public class WordContainsException extends Exception
{
    //Constructor that accepts a message
    public WordContainsException(String message)
    {
        super(message);
    }
}