package files;

import exception.ParseException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Loads a well formed XML file
 *
 * @author kyleb2
 */
public class XMLLoader {

    public static Document load(String filePath) throws ParseException {
        try {
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            return dBuilder.parse(fXmlFile);
        } catch (ParserConfigurationException ex) {
            throw new ParseException(String.format("ParserConfigurationException loading xml %s", filePath), ex);
        } catch (SAXException ex) {
            throw new ParseException(String.format("SAXException loading xml %s", filePath), ex);
        } catch (IOException ex) {
            throw new ParseException(String.format("IOException loading xml %s", filePath), ex);
        }
    }
}
