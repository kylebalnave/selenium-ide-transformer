/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package selenium.ide;

import exception.ParseException;
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
public class HtmlTestCaseParserTest {
    
    public HtmlTestCaseParserTest() {
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
    public void testLoadInvalidFile() {
        try {
            HtmlTestCaseParser testCaseParser = new HtmlTestCaseParser("blah.html");
            fail("Expecting an IOException!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }

    @Test
    public void testLoad404URL() {
        try {
            HtmlTestCaseParser testCaseParser = new HtmlTestCaseParser("http://www.blah.html");
            fail("Expecting an IOException!");
        } catch (ParseException ex) {
            assertTrue(ex instanceof ParseException);
        }
    }

    @Test
    public void testParseMethod() {
        try {
            HtmlTestCaseParser testCaseParser = new HtmlTestCaseParser("./test/assets/testCase.html");
            List<TestCaseAction> resultList = testCaseParser.parse();
            assertTrue(resultList.size() == 6);
            assertTrue("open".equals(resultList.get(0).getName()));
            assertTrue("/?gws_rd=cr&amp;ei=nwaFUoKnCaKV0AW8m4D4DQ".equals(resultList.get(0).getParam1()));
            assertTrue("".equals(resultList.get(0).getParam2()));
        } catch (ParseException ex) {
            fail("An ParseException failed the test!");
        }
    }

    
}
