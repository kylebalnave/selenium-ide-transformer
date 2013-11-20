package ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import static org.junit.Assert.assertTrue;
import selenium.translations.Translator;

/**
 * An Ant Task Class to convert a file
 * @author kyleb2
 */
public class SeleniumTransformTask extends Task {
    
    private String transformer;
    private String testcase;

    public void setTransformer(String transformXmlPath) {
        this.transformer = transformXmlPath;
    }

    public void setTestcase(String testCaseHtmlPath) {
        this.testcase = testCaseHtmlPath;
    }
    
    @Override
    public void execute() {
        try {
            Translator translator = new Translator("./test/assets/java.webdriver.xml", "./test/assets/testCase.html");
            assertTrue(true);
        } catch (Exception ex) {
            throw new BuildException(String.format("Error transforming Selenium IDE Test Case html files: %s", ex.getMessage()));
        }
    }
    
}
