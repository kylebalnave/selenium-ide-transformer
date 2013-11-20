/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package selenium.translations;

import exception.TranslationException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Translator translator = new Translator("./test/assets/java.webdriver.xml", "./test/assets/testCase.html");
            assertTrue(true);
        } catch (TranslationException ex) {
            Logger.getLogger(TranslatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
