package exception;

/**
 * A TranslationException is used when ever an error occurs during translation
 * @author kyleb2
 */
public class TranslationException extends Exception
{
      //Parameterless Constructor
      public TranslationException() {}

      // Constructor that accepts a message
      public TranslationException(String message)
      {
         super(message);
      }
      
      // Constructor that accepts a message and the original Exception
      public TranslationException(String message, Exception originalEx)
      {
         super(message, originalEx);
      }
 }
