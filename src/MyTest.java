import static org.junit.Assert.*;

import org.junit.Test;

public class MyTest {
	Line line = null;	
	@Test
	public void testLine() {
		String expectedText = "Hello World";
		line = new Line(expectedText);
		int actualNumOfChar = line.getNumOfChar();
		int expectedNumOfChar = 11;
		assertEquals(actualNumOfChar, expectedNumOfChar);
		String actualText = line.getText();
		assertEquals(actualText, expectedText);
		
		System.out.println("Showing number line");
		line.showNumberLine();
		
		System.out.println("Deleting a sub string");
		line.deleteSubString(5, 11);
		
		System.out.println("Copy Line");
		line.copyLine(0, 5);
	}
}
