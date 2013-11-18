package translation;

import exception.ParseException;
import exception.TranslationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import jsoup.parser.SeleniumTestCase;

/**
 * Converts a SeleniumTestCase file into another language
 * @author kyleb2
 */
public final class Translator {

    public Translator(String translationFile, String testCaseFile, String outFile) throws TranslationException {
        try {
            File fOut = new File(outFile);
            Loader xmlLoader = new Loader(translationFile);
            SeleniumTestCase testCaseLoader = new SeleniumTestCase(testCaseFile);
            List<String> translatedList = translate(xmlLoader, testCaseLoader);
            String translation = generateTranslation(xmlLoader, translatedList, fOut.getName().split("\\.")[0]);
            boolean out = toFile(translation, outFile);
            if(!out) {
                throw new TranslationException(String.format("Cannot save translation out to file! Is the path correct? %s", outFile));
            }
        } catch (ParseException | TranslationException ex) {
            throw new TranslationException("TranslationException", ex);
        }
    }

    /**
     *
     * @param xmlLoader
     * @param testCaseLoader
     * @return
     */
    private List<String> translate(Loader xmlLoader, SeleniumTestCase testCaseLoader) {
        List<String> translatedCase = new ArrayList<>();
        for (List<String> testCase : testCaseLoader.parse()) {
            translatedCase.add(xmlLoader.translate(testCase.get(0), testCase.get(1), testCase.get(2)));
        }
        return translatedCase;
    }

    /**
     * Generates the translation String
     * @param xmlLoader
     * @param translatedList
     * @return 
     */
    private String generateTranslation(Loader xmlLoader, List<String> translatedList, String className) {
        StringBuilder sBuffer = new StringBuilder();
        for (String code : translatedList) {
            sBuffer.append(code);
        }
        return xmlLoader.translate("jUnitTemplate", className, sBuffer.toString());
    }

    /**
     * Saves the translation string to file
     * @param outPath
     * @return 
     */
    private boolean toFile(String translation, String outPath) {
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream(outPath));
            out.print(translation);
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }

}
