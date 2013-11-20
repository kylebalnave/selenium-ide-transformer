/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package selenium.ide;

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
public class TestCaseActionTest {
    
    public TestCaseActionTest() {
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
     * Test of getName method, of class TestCaseAction.
     */
    @Test
    public void testGetName() {
        TestCaseAction instance = new TestCaseAction("1", "2", "3");
        assertTrue(instance.getName().equals("1"));
    }

    /**
     * Test of getParam1 method, of class TestCaseAction.
     */
    @Test
    public void testGetParam1() {
        TestCaseAction instance = new TestCaseAction("1", "2", "3");
        assertTrue(instance.getParam1().equals("2"));
    }

    /**
     * Test of getParam2 method, of class TestCaseAction.
     */
    @Test
    public void testGetParam2() {
        TestCaseAction instance = new TestCaseAction("1", "2", "3");
        assertTrue(instance.getParam2().equals("3"));
    }
    
}
