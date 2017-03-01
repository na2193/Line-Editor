public class Line 
{
	private int numOfChar = 0;
	private static String textOfLine;
	private static String lineBuffer, stringBuffer;
	static Operations op = new Operations();

	public Line()
	{

	}

	/*
	 * Constructor that instantiate the text and number 
	 * of characters in that text
	 */
	public Line(String textOfLine)
	{
		Line.textOfLine = textOfLine;
		numOfChar = textOfLine.length();
	}

	/*
	 * Returns an int of number of characters
	 */
	public int getNumOfChar()
	{
		return numOfChar;
	}

	/*
	 * Returns the text of the line
	 */
	public String getText()
	{
		return textOfLine;
	}

	/*
	 * This method prints out the number line based on the 
	 * certain length of the string
	 */
	public void showNumberLine()
	{
		// using string builder to create the number line
		StringBuilder lineNumber = new StringBuilder();
		StringBuilder lineScaleSymbols = new StringBuilder();

		// using a for loop from 0 to the length of the edited line
		for(int i = 0; i < numOfChar; i++)
		{
			if(i % 10 == 0) // if its divisible by 10
			{
				if(i < 10) // and is less than 10
				{
					lineNumber.append(i); // add the ith number
				}
				else // if its not less than 10
				{
					lineNumber.insert(i - 1, i); // insert the ith number - 1 at i
				}
				lineScaleSymbols.append('|'); // add the '|' symbol
			}
			else if(i % 5 == 0) // if its divisble by 5
			{
				if(i < 10) // and less than 10
				{
					lineNumber.append(i); // add the ith number
				}
				else
				{
					lineNumber.insert(i - 1, i); // insert the i - 1 at i
				}
				lineScaleSymbols.append('+'); // adding the '+' symbol
			}
			else // if it is not divisible by 5 or 10 
			{
				lineNumber.append(' '); // add a blank space
				lineScaleSymbols.append('-'); // add the '-' symbol
			}
		}

		// printing out the line number, symbols, and the text to show the number line
		System.out.println(lineNumber.toString());
		System.out.println(lineScaleSymbols.toString());
		System.out.println(textOfLine);
	}

	/*
	 * This method deletes a substring based on the users choice
	 */
	public void deleteSubString(int fromIndex, int toIndex) 
	{
		// checking if the given parameters are in bound
		if(fromIndex < 0 || fromIndex > numOfChar || toIndex < 0 || toIndex > numOfChar)
		{
			System.out.println("Cannot delete at the given index: Index Out of Bounds");
		}

		StringBuffer sb = new StringBuffer(textOfLine); // creating a string buffer to manipulate the string
		sb.replace(fromIndex, toIndex, ""); // deleting the substring 
		textOfLine = sb.toString(); // assigning the edited line to the main text of line
		numOfChar = textOfLine.length(); // changing the length of the text to the newly edited length
		showNumberLine(); // showing the numbe rline
		op.printELMenuChoice();
	}

	/*
	 * This method copies the range of substring to line buffer
	 */
	public void copyLine(int fromIndex, int toIndex)
	{
		// checking if the given parameters are in the range
		if(fromIndex < 0 || fromIndex > numOfChar || toIndex < 0 || toIndex > numOfChar)
		{
			System.out.println("Cannot copy at the given index: Index Out of Bounds");
		}

		lineBuffer = textOfLine.substring(fromIndex, toIndex); // copying the substring to line buffer
		System.out.println("The text that is copied is: " + lineBuffer);
		op.printELMenuChoice();
	}

	/*
	 * This method will paste in the line buffer based 
	 * on the user's position
	 */
	public void paste(int insertPos)
	{
		// checking if the given parameters are in range
		if(insertPos < 0 || insertPos > numOfChar)
		{
			System.out.println("Cannot paste at the given index: Index Out of Bounds");
		}

		StringBuffer sb = new StringBuffer(textOfLine);
		
        // based on the location of the position, it will put a space where the substring will be pasted in
		if(insertPos == numOfChar)
		{
			sb.insert(insertPos, " " + lineBuffer); // inserting the line buffer at the end of the line
		}
		else if(insertPos == 0)
		{
			sb.insert(insertPos, lineBuffer + " "); // inserting at the beginning of the line
		}
		else
		{
			sb.insert(insertPos,  " " + lineBuffer + " "); // inserting in the middle
		}

		System.out.println("Printing out the line buffer, the copied text: " + lineBuffer);

		// updated the textOfLine 
		textOfLine = sb.toString();
		numOfChar = textOfLine.length();
		op.showNumberLine(textOfLine);
		op.printELMenuChoice();
	}

	/*
	 * This method will copy a range of text and store it in the stringBuffer
	 * It will then call the deleteSubString method to delete that range of text
	 * The deleted text is copied to stringBuffer
	 */
	public void cut(int fromIndex, int toIndex)
	{
		// checking if the given parameters are in the range
		if(fromIndex < 0 || fromIndex > numOfChar || toIndex < 0 || toIndex > numOfChar)
		{
			System.out.println("Cannot cut at the given index: Index Out of Bounds");
		}

		// copying to stringBuffer
		stringBuffer = textOfLine.substring(fromIndex, toIndex);
		System.out.println("The test text that is copied is: " + stringBuffer);
		
		// deleting from the line
		System.out.println("The line after deleting");
		deleteSubString(fromIndex, toIndex);
		
		op.printELMenuChoice(); // printing menu choice
	}

	/*
	 * This methods allows the user to type in a new text into an
	 * existing line. It takes in two parameters, index and the new 
	 * string
	 */
	public void newSubString(int index, String newString)
	{
		// checking if the given parameters are in the range
		if(index < 0 || index > numOfChar)
		{
			System.out.println("Cannot insert at the given index: Index Out of Bounds");
		}

		StringBuffer sb = new StringBuffer(textOfLine);

		// will insert the new string with a space based on the location of the substring
		if(index == numOfChar) // if it is at the end
		{
			sb.insert(index, " " + newString); // insert a space before the new string
		}
		else if(index == 0) // if it is at the beginning
		{
			sb.insert(index, newString + " "); // insert a space at the end of the new string
		}
		else // if it is anywhere else
		{
			sb.insert(index,  " " + newString + " "); // insert a space before and after the new string
		}

		// updating
		textOfLine = sb.toString();
		numOfChar = textOfLine.length();
		showNumberLine(); // showing the number line of the updated line
		op.printELMenuChoice(); // showing the menu choice
	}
}


