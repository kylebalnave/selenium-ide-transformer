/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoup.parser;

import exception.ParseException;
import java.io.IOException;
import java.util.List;
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
public class SeleniumTestCaseTest {

    public SeleniumTestCaseTest() {
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
    public void testIsWebUrl() {
        assertTrue(SeleniumTestCase.isWebUrl("http://www.google.com"));
        assertTrue(SeleniumTestCase.isWebUrl("https://www.google.com"));
        assertFalse(SeleniumTestCase.isWebUrl("google.com"));
    }

    @Test
    public void testIsIllegalWebUrl() {
        try {
            SeleniumTestCase testCaseParser = new SeleniumTestCase("www.blah.html");
            fail("Expecting an IOException!");
        } catch (IllegalArgumentException iaEx) {
            assertTrue(iaEx instanceof IllegalArgumentException);
        } catch (ParseException ioEx) {
            fail("Expecting an ParseException, not an IOException!");
        }
    }

    @Test
    public void testLoadInvalidFile() {
        try {
            SeleniumTestCase testCaseParser = new SeleniumTestCase("blah.html");
            fail("Expecting an IOException!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }

    @Test
    public void testLoad404URL() {
        try {
            SeleniumTestCase testCaseParser = new SeleniumTestCase("http://www.blah.html");
            fail("Expecting an IOException!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }

    @Test
    public void testParseMethod() {
        try {
            SeleniumTestCase testCaseParser = new SeleniumTestCase("./test/assets/testCase.html");
            List<List<String>> resultList = testCaseParser.parse();
            assertTrue(resultList.size() == 6);
            assertTrue(resultList.get(0).size() == 3);
            assertTrue("open".equals(resultList.get(0).get(0)));
            System.out.println(resultList.get(0).get(1));
            assertTrue("/?gws_rd=cr&amp;ei=nwaFUoKnCaKV0AW8m4D4DQ".equals(resultList.get(0).get(1)));
            assertTrue("".equals(resultList.get(0).get(2)));
        } catch (ParseException ex) {
            fail("An ParseException failed the test!");
        }
    }

}
