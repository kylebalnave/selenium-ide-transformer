package assets;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCasetestCase {

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
    public void testSeleniumIDETest() {
        FirefoxDriver wd = new FirefoxDriver();
        wd.get("http://www.google.com");
// open missing translation!
// type missing translation!
// type missing translation!
// click missing translation!
// clickAndWait missing translation!
// click missing translation!
        wd.close();
        fail("This selenium WebDriver test should fail!");
    }
}
