package exception;

/**
 * A TranslationException is used when ever an error occurs during parsing
 * @author kyleb2
 */
public class ParseException extends Exception
{
      //Parameterless Constructor
      public ParseException() {}

      // Constructor that accepts a message
      public ParseException(String message)
      {
         super(message);
      }
      
      // Constructor that accepts a message and the original Exception
      public ParseException(String message, Throwable originalEx)
      {
         super(message, originalEx);
      }
 }
