/*
 <?xml version="1.0"?>
 <rosetta>
 <glyph phrase="open"><![CDATA[
 webdriver.open("{{ 1 }}");
 ]]></glyph>
 <glyph phrase="type"><![CDATA[
 webdriver.focus("{{ 1 }}");
 webdriver.type("{{ 2 }}");
 ]]></glyph>
 <glyph phrase="click"><![CDATA[
 webdriver.focus("{{ 1 }}");
 webdriver.click();
 ]]></glyph>
 <glyph phrase="clickAndWait"><![CDATA[
 webdriver.focus("{{ 1 }}");
 webdriver.click();
 webdriver.waitFroPageLoad();
 ]]></glyph>
 </rosetta>
 */
package translation;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import exception.ParseException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Loads an XML file containing to|from xml nodes
 *
 * @author kyleb2
 */
public class Loader {

    Document doc;
    Map<String, String> translationMap = new HashMap<>();

    public Loader(String filePath) throws ParseException {
        try {
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            parse();
        } catch (ParserConfigurationException ex) {
            throw new ParseException(String.format("ParserConfigurationException loading xml %s", filePath), ex);
        } catch (SAXException ex) {
            throw new ParseException(String.format("SAXException loading xml %s", filePath), ex);
        } catch (IOException ex) {
            throw new ParseException(String.format("IOException loading xml %s", filePath), ex);
        }
    }

    private void parse() throws ParseException {
        NodeList nList = doc.getElementsByTagName("glyph");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String phrase = eElement.getAttribute("phrase");
                String translation = eElement.getTextContent();
                if (phrase.isEmpty()) {
                    throw new ParseException("Glyph phrase attributes cannot be empty!");
                } else if (translationMap.containsKey(phrase)) {
                    throw new ParseException("Glyph phrase must be unique!");
                } else {
                    translationMap.put(phrase, translation);
                }
            }
        }
        if (!translationMap.containsKey("jUnitTemplate")) {
            throw new ParseException("Each translation file requires a jUnitTemplate glyph!");
        }
    }

    /**
     * Get one of the stored keys
     *
     * @param key
     * @param args
     * @return
     */
    public String translate(String key, String... args) {
        String result = "";
        if (translationMap.containsKey(key)) {
            result = replaceReferences(translationMap.get(key), args);
        }
        return result;
    }

    public String replaceReferences(String src, String... args) {
        if(args.length == 0) {
            return src;
        }
        // replace number values with standard args
        Properties properties = System.getProperties();
        Pattern numPattern = Pattern.compile("\\{\\{ ([0-9a-zA-Z\\.-_]+) \\}\\}");
        Matcher numMatcher = numPattern.matcher(src);
        while (numMatcher.find()) {
            String fullMatch = numMatcher.group(0);
            String paramName = numMatcher.group(1);
            if (paramName.matches("^\\d+$")) {
                try {
                    int argIndex = Integer.parseInt(paramName);
                    src = args.length >= argIndex ? src.replace(fullMatch, args[argIndex]) : "";
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // skip this value
                }
            } else if (properties.containsKey(paramName)) {
                src = src.replace(fullMatch, (String) properties.get(paramName));
            }
        }
        return src;
    }
}
