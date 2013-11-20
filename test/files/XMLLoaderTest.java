/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package files;

import exception.ParseException;
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
public class XMLLoaderTest {
    
    public XMLLoaderTest() {
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

@Test
    public void testSAXException() throws Exception {
        try {
            XMLLoader.load("./test/assets/invalid_translation.xml");
            fail("Expecting an ParserConfigurationException!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }
    
    @Test
    public void testIOException() throws Exception {
        try {
            XMLLoader.load("./test/assets/blah.xml");
            fail("Expecting an IOException!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }
    
    
}
