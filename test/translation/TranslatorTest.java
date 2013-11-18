package translation;

import exception.TranslationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kyleb2
 */
public class TranslatorTest {

    public TranslatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory : " + workingDir);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTranslatedList method, of class Translator.
     */
    @Test
    public void testGetTranslatedList() {
        try {
            Translator translator = new Translator("./test/assets/translation.xml", "./test/assets/testCase.html", "./test/generated/SeleniumIdeTestCase.java");
            assertTrue(true);
        } catch (TranslationException ex) {
            fail("An exception should not have been thrown " + ex.toString());
        }
    }

}
