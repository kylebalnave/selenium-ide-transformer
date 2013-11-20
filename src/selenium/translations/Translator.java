package selenium.translations;

import exception.ParseException;
import exception.TranslationException;
import files.IO;
import files.XMLLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import selenium.ide.HtmlTestCaseParser;
import org.w3c.dom.Document;
import selenium.ide.TestCaseAction;

/**
 * Converts a SeleniumTestCase file into another language
 *
 * @author kyleb2
 */
public final class Translator {

    public Translator(String translationFile, String testCaseFile) throws TranslationException {
            //
        // load the translator xml file
        Document doc = null;
        try {
            doc = XMLLoader.load(translationFile);
        } catch (ParseException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, String> translatorMap = null;
        try {
            translatorMap = parseTranslationDoc(doc);
        } catch (ParseException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        }
            //
        // load the test case html file
        HtmlTestCaseParser testCaseLoader = null;
        try {
            testCaseLoader = new HtmlTestCaseParser(testCaseFile);
        } catch (ParseException ex) {
            Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
        }
            //
        // translate the individual action
        List<String> translatedList = translateTestCase(testCaseLoader, translatorMap);
            //
        // output the complete translation
        StringBuilder sBuffer = new StringBuilder();
        for (String code : translatedList) {
            sBuffer.append(code);
        }
        File fTestCase = new File(testCaseFile);
        String className = fTestCase.getName().split("\\.")[0];
        File fOut = new File(fTestCase.getParent() + File.separator + "TestCase" + className + ".java");
        TestCaseAction templateAction = new TestCaseAction("template-file", "TestCase" + className, sBuffer.toString());
        boolean out = IO.toFile(translateAction(templateAction, translatorMap), fOut.getAbsolutePath());
        if (!out) {
            throw new TranslationException(String.format("Cannot save translation out to file! Is the path correct? %s", fOut.getAbsolutePath()));
        } else {
            System.out.println(String.format("File transformed to %s", fOut.getAbsolutePath()));
        }
    }

    /**
     * Translates a single TestCaseAction
     *
     * @param translatorMap
     * @param testCaseAction
     * @return
     */
    private String translateAction(TestCaseAction testCaseAction, Map<String, String> translatorMap) {

        if (translatorMap != null && translatorMap.containsKey(testCaseAction.getName())) {
            return replaceReferences(
                    translatorMap,
                    translatorMap.get(testCaseAction.getName()),
                    testCaseAction.getParam1(),
                    testCaseAction.getParam2());
        }
        return String.format("\n// %s missing translation!", testCaseAction.getName());
    }

    /**
     * Translate an entire TestCase.html
     *
     * @param testCaseLoader
     * @param translatorMap
     * @return
     */
    private List<String> translateTestCase(HtmlTestCaseParser testCaseLoader, Map<String, String> translatorMap) {
        List<String> translatedCase = new ArrayList();
        for (TestCaseAction testCase : testCaseLoader.parse()) {
            String translation = translateAction(testCase, translatorMap);
            translatedCase.add(translation);
        }
        return translatedCase;
    }

    /**
     * Parses the loaded translation doc into a Map
     *
     * @param doc
     * @return
     * @throws ParseException
     */
    private Map<String, String> parseTranslationDoc(Document doc) throws ParseException {
        Map<String, String> translationMap = new HashMap();
        NodeList nList = doc.getElementsByTagName("glyph");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String phrase = eElement.getAttribute("id");
                String translation = eElement.getTextContent();
                if (phrase.isEmpty()) {
                    throw new ParseException("Glyph phrase attributes cannot be empty!");
                } else if (translationMap.containsKey(phrase)) {
                    throw new ParseException(String.format("Glyph phrase %s must be unique!", phrase));
                } else {
                    translationMap.put(phrase, translation);
                }
            }
        }
        if (!translationMap.containsKey("template-file")) {
            throw new ParseException("Each translation file requires a template-file glyph!");
        }
                        
        return translationMap;
    }

    /**
     * Replaces references in a src String
     *
     * @param src
     * @param args
     * @return
     */
    private String replaceReferences(Map<String, String> translatorMap, String src, String... args) {
        if (args.length == 0) {
            return src;
        }
        Properties sysProps = System.getProperties();
        // replace number values with standard args
        Pattern numPattern = Pattern.compile("\\{\\{ ([0-9a-zA-Z\\.-_]+) \\}\\}");
        Matcher numMatcher = numPattern.matcher(src);
        while (numMatcher.find()) {
            String fullMatch = numMatcher.group(0);
            String paramName = numMatcher.group(1);
            if (paramName.matches("^\\d+$")) {
                // needs a args[int]
                try {
                    int argIndex = Integer.parseInt(paramName) - 1;
                    src = args.length >= argIndex ? src.replace(fullMatch, args[argIndex]) : "";
                } catch (ArrayIndexOutOfBoundsException ex) {
                    src = src.replace(fullMatch, String.format("/* ref %s out of arg bounds */", paramName));
                }
            } else if (translatorMap.containsKey(paramName)) {
                src = src.replace(fullMatch, (String) translatorMap.get(paramName));
            } else if (sysProps.containsKey(paramName)){
                src = src.replace(fullMatch, (String) sysProps.get(paramName));
            } else {
                src = src.replace(fullMatch, String.format("/* unknown ref %s */", paramName));
            }
        }
        return src;
    }

}
