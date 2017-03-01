import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Document
{
	static DoublyLinkedList list = new DoublyLinkedList();
	Line line;
	Operations op = new Operations();
	Scanner keyboard = new Scanner(System.in);
	int lineNum;
	boolean isFileLoad = false;
	String loadFileName;
	String lineBuffer = "";
	
	/*
	 * This method inserts a new line to the list
	 */
	public void newLine()
	{
		// If the list is empty, insert the data at the beginning of the list
		if(list.isEmpty())
		{
			String userDataText = "";
			int numberLine = 1;
			
			// Printing out to the user and getting values back
			System.out.print("type line? (y/n):   ");
			String userChoice = keyboard.nextLine();
			System.out.print(numberLine + ": ");
			userDataText = keyboard.nextLine();
			list.insertAtStart(userDataText); // inserting at the beginning
			
			System.out.print("type line? (y/n):   ");
			userChoice = keyboard.nextLine();
			
			// looping until the user does not want to enter any more lines
			while(!userChoice.equalsIgnoreCase("n"))
			{
				numberLine++;
				System.out.print(numberLine + ": ");
				userDataText = keyboard.nextLine();
				list.insertAt(userDataText, numberLine);

				System.out.print("type line? (y/n):   ");
				userChoice = keyboard.nextLine();
			}
			op.printMenuChoices(); // printing out the menu choices
		}
		else // if the list is not empty insert at a given position
		{
			// User input output
			System.out.print("Insert after line number: ");
			String lineNum = keyboard.nextLine();
			int lineNumber = Integer.parseInt(lineNum); // converting to integer 
			System.out.println("inserting after:");
			String data = list.get(lineNumber); // retrieving the data node of the given line number
			System.out.println(data);
			System.out.print("type line? (y/n):   ");
			String userChoice = keyboard.nextLine();
			
			String userDataText = "";
			// looping until the user doesn't want to enter any more lines
			while(!userChoice.equalsIgnoreCase("n"))
			{
				System.out.print(lineNumber + ": ");
				userDataText = keyboard.nextLine();
				list.insertAt(userDataText, lineNumber);

				System.out.print("type line? (y/n):   ");
				userChoice = keyboard.nextLine();
			}
			op.printMenuChoices();
		}
	}
	
	/*
	 * This method prints out all the string data for the list
	 */
	public void showAll()
	{
		// checking if the list is empty
		if(list.isEmpty())
		{
			System.out.println("No data \n"); // if it is print out error message
			op.printMenuChoices(); // show menu choice
		}
		else // if the list is not empty
		{
			list.print(); // print the list
			op.printMenuChoices();
		}
	}
	
	/*
	 * This method will show the specific line the user wishes
	 */
	public void showLine()
	{
		if(list.isEmpty())
		{
			System.out.println("No data \n");
			op.printMenuChoices();
		}
		else
		{
			// getting the line number from the user
			System.out.print("line number: ");
			String line = keyboard.nextLine();
			int lineNum = Integer.parseInt(line);
			String data = list.get(lineNum); // getting the string data for that line number
			System.out.println(data); // printing the line number the user wanted
			op.printMenuChoices();
		}
	}
	
	/*
	 * This will show a range of lines that the user enters
	 */
	public void showRange()
	{
		if(list.isEmpty())
		{
			System.out.println("No data \n");
			op.printMenuChoices();
		}
		else
		{
			// getting the range of lines
			System.out.print("from: ");
			String fromLine = keyboard.nextLine();
			System.out.print("to: ");
			String toLine = keyboard.nextLine();
			
			int from = Integer.parseInt(fromLine);
			int to = Integer.parseInt(toLine);
			
			// checking if the from line number is in bound
			if(from < 0 || from > list.getSize())
			{
				System.out.println("From Positions out of bounds");
			}
			else // if the from line number is in bound
			{
				if(to > list.getSize() + 1) // checking if the to line number is less than the size of the list
					to = list.getSize(); // if it is greater than the to line number will be the size of the list
				
				// looping to get all the lines the user wanted
				for(int i = from; i <= to; i++)
				{
					String data = list.get(i);
					System.out.println(i + ": " + data); // printing out the lines
				}
			}
			op.printMenuChoices();
		}
	}
	
	/*
	 * This method will copy the range of lines to line buffer
	 */
	public void copyRange()
	{
		if(list.isEmpty())
		{
			System.out.println("No data \n");
			op.printMenuChoices();
		}
		else
		{
			System.out.print("from: ");
			String fromLine = keyboard.nextLine();
			System.out.print("to: ");
			String toLine = keyboard.nextLine();
			
			int from = Integer.parseInt(fromLine);
			int to = Integer.parseInt(toLine);
			
			// checking if the user entered correct information that is bounded
			if(from < 0 || from > list.getSize() && to < 0 || to > list.getSize())
			{
				System.out.println("From Positions out of bounds");
			}
			else // if the user data is correct
			{
				for(int i = from; i <= to; i++)
				{
					lineBuffer += list.get(i) + "\n"; // store the lines to line buffer
				}
				System.out.println(lineBuffer);
				op.printMenuChoices();
			}
		}
	}
	
	/*
	 * This method will paste the lines from line buffer
	 * to the given line number position
	 */
	public void pasteLines()
	{
		if(list.isEmpty())
		{
			System.out.println("No data \n");
			op.printMenuChoices();
		}
		else
		{
			System.out.print("paste after line number: ");
			String pasteLine = keyboard.nextLine();
			
			int lineNumber = Integer.parseInt(pasteLine);
			if(lineNumber < 0 || lineNumber > list.getSize() + 1)
			{
				System.out.println("Line Number Out Of Bounds");
			}
			else
			{
				list.insertAt(lineBuffer, lineNumber); // inserting the line buffer at the given position
				System.out.println(lineBuffer);
				op.printMenuChoices();
			}
		}
	}
	
	/*
	 * This will delete a line at the given position
	 */
	public void deleteLine()
	{
		if(list.isEmpty())
		{
			System.out.println("No data \n");
			op.printMenuChoices();
		}
		else
		{
			// getting the position to delete at
			System.out.print("line number to delete: ");
			String line = keyboard.nextLine();
			int lineNum = Integer.parseInt(line);
			String data = list.get(lineNum);
			System.out.println(data);
			System.out.print("Delete (y/n)       "); // checking with the user if they want to delete
			String delete = keyboard.nextLine();
			
			if(delete.equalsIgnoreCase("y"))
				list.removeAt(lineNum); // removing the node
			else
				deleteLine();
			
			op.printMenuChoices();
		}
	}
	
	/*
	 * This will delete a range of lines from the user position
	 */
	public void deleteRange()
	{
		if(list.isEmpty())
		{
			System.out.println("No data \n");
			op.printMenuChoices();
		}
		else
		{
			System.out.print("from: ");
			String fromString = keyboard.nextLine();
			System.out.print("to: ");
			String toString = keyboard.nextLine();
			
			int from = Integer.parseInt(fromString);
			int to = Integer.parseInt(toString);
			
			// looping through all the nodes based on the from and to position
			for(int i = from; i <= to; i++)
			{
				list.removeAt(i); // removing all the nodes from those position
			}
			
			op.printMenuChoices();
		}
	}
	
	/*
	 * This method will allow the user to go to a another menu where
	 * they will be allowed to edit a certain line
	 */
	public void editLine()
	{
		if(list.isEmpty())
		{
			System.out.println("No data \n");
			op.printMenuChoices();
		}
		else
		{
			// retrieving the line number to edit
			System.out.print("line number: ");
			String lineS = keyboard.nextLine();
			lineNum = Integer.parseInt(lineS);
			String data = list.get(lineNum);
			
			op.showNumberLine(data); // showing the number line for the given data
			op.printELMenuChoice(); // showing the edit line menu choice
		}
	}
	
	/*
	 * This method will get the newly edited string and update the list
	 */
	public void getNewEditedLine(String data)
	{
		list.removeAt(lineNum); // remove the line where the old node was
		list.insertAt(data, lineNum); // insert the new edited string for the same line number
	}
	
	/*
	 * This method wil load a file from the user
	 */
	public void loadFile()
	{
		String line = null; // this will reference one line at a time
		System.out.println("Enter the file name to open");
		loadFileName = keyboard.nextLine();
		
		try
		{
			FileReader fileReader = new FileReader(loadFileName); // FileReader reads text files in the default encoding
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// reading each line of the file
			while((line = bufferedReader.readLine()) != null)
			{
			//	System.out.println(line);
				list.insertAtStart(line); // inserting each line at the start
			}
			/// changing the boolean to true so that in the write method, we could see if the file is already loaded
			isFileLoad = true; 
            bufferedReader.close(); // closing the buffered reader
            op.printMenuChoices();
		}
		// catching exceptions if there was an error with opening the file
		catch(FileNotFoundException ex)
		{
			System.out.println("Unable to open file '" + loadFileName + "'");    
			System.out.println("Please try again");
			loadFile();
		}
		catch(IOException ex)
		{
			System.out.println("Error reading file '" + loadFileName + "'");
			System.out.println("Please try again");
			loadFile();
		}
	}
	
	/*
	 * This method will write content to a file
	 */
	public void writeFile()
	{
		BufferedWriter bw = null;
		FileWriter fw = null;
		String fileName = "";
		
		try
		{
			if(isFileLoad) // checking if the user already loaded the file, if they did
			{
				fw = new FileWriter(loadFileName); // create a new file writer will the loaded file name
				bw = new BufferedWriter(fw);

				for(int i = 1; i < list.getSize(); i++)
				{
					bw.write(list.get(i) + ""); // writing each line from the list to the file
					bw.newLine();
				}
				System.out.println("Successfully updated file");
			}
			else // if no file is loaded
			{
				// asking the user for the name of a new file to write
				System.out.print("write to file: ");
				fileName = keyboard.nextLine();
				
				fw = new FileWriter(fileName);
				bw = new BufferedWriter(fw);

				for(int i = 1; i < list.getSize(); i++)
				{
					bw.write(list.get(i) + ""); // writing each line to the new file
					bw.newLine();
				}
				System.out.println("Successfully created file");
			}
		}
		catch(IOException ex)
		{
			System.out.println("Error writing to file '" + fileName + "'");
			System.out.println("Please try again");
			writeFile();
		}
		finally
		{
			try 
			{
				bw.close(); // closing both writers
				fw.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	/*
	 * This method will write to a file and quit the program
	 */
	public void writeQuit()
	{
		writeFile(); // calling the write file method to write to a file
		System.out.println("Quiting the program...");
		System.exit(0);
	}
}
