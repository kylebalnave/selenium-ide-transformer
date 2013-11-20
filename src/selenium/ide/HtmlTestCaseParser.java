package selenium.ide;

import exception.ParseException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Parses a Selenum IDE TestCase.html source Creates an Map<String,
 * List<String>> of method names and parameters
 *
 * @author kyleb2
 */
public final class HtmlTestCaseParser {
    
    Document doc;

    public HtmlTestCaseParser(String urlOrFilePath) throws ParseException {
        try {
            if (isWebUrl(urlOrFilePath)) {
                doc = Jsoup.connect(urlOrFilePath).get();
            } else {
                // assume file System
                File input = new File(urlOrFilePath);
                doc = Jsoup.parse(input, "UTF-8");
            }
        } catch (IOException ioEx) {
            throw new ParseException(String.format("Error loading file or url", urlOrFilePath, ioEx));
        } catch (IllegalArgumentException iaEx) {
            throw new IllegalArgumentException(String.format("Web url is malformed.  URLs need to start with http:// or https://", urlOrFilePath, iaEx));
        }
    }
    
    /**
     * Parses the table and creates a map of all the Selenium IDE actions
     * @return 
     */
    public List<TestCaseAction> parse() {
        List<TestCaseAction> resultList = new ArrayList();
        Element $table = doc.select("table").first();
        Elements $tRows = $table.select("tbody > tr");
        for(Element $row : $tRows) {
            Elements $columns = $row.select("td");
            resultList.add(new TestCaseAction($columns.get(0).html(), $columns.get(1).html(), $columns.get(2).html()));
        }
        return resultList;
    }

    /**
     * Decide if a string is a web url
     *
     * @param urlOrFilePath
     * @return
     */
    private boolean isWebUrl(String urlOrFilePath) {
        Pattern regex = Pattern.compile("http://|https://|www.");
        Matcher regexMatcher = regex.matcher(urlOrFilePath);
        return regexMatcher.find();
    }

}
