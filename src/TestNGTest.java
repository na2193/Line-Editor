import static org.testng.Assert.*;
import org.testng.annotations.*;

public class TestNGTest {
	private Line line;
	private String expectedText;
	private int expectedNumOfChar;
	
	@BeforeClass
	public void oneTimeSetUp() {
		System.out.println("@BeforeClass - oneTimeSetUp");
	}
	
	@AfterClass
	public void oneTimeTearDown() {
		System.out.println("@AfterClass - oneTimeTearDown");
	}
	
	@BeforeMethod
	public void setUp() {
		System.out.println("@BeforeMethod - Setting Up Before Method");
		expectedText = "Hello World";
		line = new Line(expectedText);
		expectedNumOfChar = 11;
	}
	
	@Test
	public void testLine() {
		int actualNumOfChar = line.getNumOfChar();
		assertEquals(actualNumOfChar, expectedNumOfChar);
		
		String actualText = line.getText();
		assertEquals(actualText, expectedText);
	}
}
