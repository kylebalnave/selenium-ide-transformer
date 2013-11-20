package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author kyleb2
 */
public class IO {

    /**
     * Saves the translation string to file
     *
     * @param outPath
     * @return
     */
    public static boolean toFile(String str, String outPath) {
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream(outPath));
            out.print(str);
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }

    /**
     * Reads the contents of a file as a String
     * @param inPath
     * @return 
     */
    public static String fromFile(String inPath) {
        String content = null;
        File file = new File(inPath); //for ex foo.txt
        try {
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
