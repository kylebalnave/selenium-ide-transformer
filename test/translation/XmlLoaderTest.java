/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;

import exception.ParseException;
import java.util.Properties;
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
public class XmlLoaderTest {

    public XmlLoaderTest() {
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
    
    @Test
    public void testInit() throws Exception {
        Loader instance = new Loader("./test/assets/translation.xml");
    }
    
    @Test
    public void testSAXException() throws Exception {
        try {
            Loader instance = new Loader("./test/assets/invalid_translation.xml");
            fail("Expecting an ParserConfigurationException!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }
    
    @Test
    public void testIOException() throws Exception {
        try {
            Loader instance = new Loader("./test/assets/blah.xml");
            fail("Expecting an IOException!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }
    
    
    @Test
    public void testDuplicateEntryParse() throws Exception {
        try {
            Loader instance = new Loader("./test/assets/duplicate_translation.xml");
            fail("Expecting an SAXException thrown by duplicate Node!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }
    
    @Test
    public void testTranslateValidKey() throws Exception {
        Loader instance = new Loader("./test/assets/translation.xml");
        assertTrue(instance.translate("open"), instance.translate("open").contains("webdriver.open(\"{{ 0 }}\")")); 
        assertTrue(instance.translate("open", "apple"), instance.translate("open", "apple").contains("webdriver.open(\"apple\")")); 
        assertTrue(instance.translate("open", "apple", "pear").contains("webdriver.open(\"apple\")"));
        assertTrue(instance.translate("type").contains("webdriver.focus(\"{{ 0 }}\");")); 
        assertTrue(instance.translate("type").contains("webdriver.type(\"{{ 1 }}\");")); 
        assertTrue(instance.translate("type", "apple").contains("webdriver.focus(\"apple\");")); 
        assertTrue(instance.translate("type", "apple").contains("webdriver.type(\"{{ 1 }}\");"));
        assertTrue(instance.translate("type", "apple", "pear").contains("webdriver.focus(\"apple\");")); 
        assertTrue(instance.translate("type", "apple", "pear").contains("webdriver.type(\"pear\");"));
        Properties props = System.getProperties();
        props.setProperty("driver.type", "firefox");
        assertTrue(instance.translate("driver", "firefox").contains("firefox"));
    }
    
    @Test
    public void testTranslateInvalidKey() throws Exception {
        Loader instance = new Loader("./test/assets/translation.xml");
        assertTrue(instance.translate("invalidkey").isEmpty()); 
    }

}
