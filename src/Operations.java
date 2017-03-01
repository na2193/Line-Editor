import java.util.Scanner;

public class Operations 
{
	Scanner keyboard = new Scanner(System.in);
	DoublyLinkedList list = new DoublyLinkedList();
	static Line line = new Line();
	static Document doc = new Document();
	
	/*
	 * This method prints out the menu option to the user
	 */
	public void menuOptions()
	{
		System.out.println("Menu: m         Delete line: dl");
		System.out.println("Load file: l    Delete range: dr");
		System.out.println("Show all: sa    Copy range: cr");
		System.out.println("Show line: sl   Paste lines: pl");
		System.out.println("Show range: sr  Write to file: w");
		System.out.println("New line: nl    Quit: q");
		System.out.println("Edit line: el   Write and quit: wq");
	}

	/*
	 * This method reads in the userInput for the menu selection
	 * and based on the user choice, goes to that specific 
	 * method to perform that action
	 */
	public void printMenuChoices()
	{
		Operations op = new Operations();
		op.menuOptions();
		System.out.print("\n--> ");
		String userChoice = keyboard.nextLine();

		/*
		 * Using a switch statement to go to different methods
		 * based on user selections
		 */
		switch(userChoice)
		{
			case "m":
				printMenuChoices();
				break;
			case "l":
				doc.loadFile();
				break;
			case "sa":
				doc.showAll();
				break;
			case "sl":
				doc.showLine();
				break;
			case "sr":
				doc.showRange();
				break;
			case "nl":
				doc.newLine();
				break;
			case "el":
				doc.editLine();
				break;
			case "dl":
				doc.deleteLine();
				break;
			case "dr":
				doc.deleteRange();
				break;
			case "cr":
				doc.copyRange();
				break;
			case "pl":
				doc.pasteLines();
				break;
			case "w":
				doc.writeFile();
				op.printMenuChoices();
				break;
			case "q":
				System.out.println("Quiting the program...");
				System.exit(0);
				break;
			case "wq":
				doc.writeQuit();
				break;
			default: 
				System.out.println("Invalid Choice. Try Again");
				printMenuChoices();
				break;
		}
	}
	
	/*
	 * This method will print out the edit line menu choice to the user
	 */
	public void editLineMenuChoice()
	{
		System.out.println("   Show line: s");
		System.out.println("   Copy to string buffer: c");
		System.out.println("   Cut: t");
		System.out.println("   Paste from string buffer: p");
		System.out.println("   Enter new substring: e");
		System.out.println("   Delete substring: d");
		System.out.println("   Quit line: q");
	}
	
	/*
	 * This method will get the users choice for the edit menu choice
	 */
	public void printELMenuChoice()
	{
		Operations op = new Operations();
		op.editLineMenuChoice();
		
		System.out.print("\n--> ");
		String userChoice = keyboard.nextLine();
		
		// Using a switch statement to go to different methods
		switch(userChoice)
		{
			case "s":
				line.showNumberLine();
				printELMenuChoice();
				break;
			case "c":
				copyLine();
				break;
			case "t":
				cut();
				break;
			case "p":
				pasteLine();
				break;
			case "e":
				newSubString();
				break;
			case "d":
				deleteSubString();
				break;
			case "q":
				System.out.println("Returnin to main menu... \n");
				sendNewLine();
				op.printMenuChoices();
				break;
			default:
				System.out.println("Invalid Choice. Try Again");
				printELMenuChoice();
				break;	
		}
	}
	
	/*
	 * This method will show the number line 
	 */
	public void showNumberLine(String data)
	{
		line = new Line(data); // creating a new line based on the given string data
		line.showNumberLine(); // printing out the number line
	}
	
	/*
	 * This method gets user input to copy the substring of the line
	 */
	public void copyLine()
	{
		line.showNumberLine(); // showing the number line
		// getting user input
		System.out.print("\ncopy from: ");
		String copyFrom = keyboard.nextLine();
		System.out.print("copy to: ");
		String copyTo = keyboard.nextLine();
		int from = Integer.parseInt(copyFrom);
		int to = Integer.parseInt(copyTo);
		
		line.copyLine(from, to); // calling the method to copy the substring of the line
	}
	
	/*
	 * This method gets user input to paste the line
	 */
	public void pasteLine()
	{
		line.showNumberLine();
		System.out.print("\ninsert at position: "); // getting user input on what position of the line
		String insertString = keyboard.nextLine();
		int insertPos = Integer.parseInt(insertString); // converting the input string to int
		
		line.paste(insertPos); // calling the method that pastes the line at the given position
	}
	
	/*
	 * This method gets user input to cut a substring
	 */
	public void cut()
	{
		line.showNumberLine(); // showing the number line
		System.out.print("\ncut from: ");
		String cutFrom = keyboard.nextLine();
		System.out.print("cut to: ");
		String cutTo = keyboard.nextLine();
		int from = Integer.parseInt(cutFrom);
		int to = Integer.parseInt(cutTo);
		
		line.cut(from, to); // calling the method that cuts the substring
	}
	
	/*
	 * This method create a new substring at a certain position
	 */
	public void newSubString()
	{
		line.showNumberLine();
		System.out.print("\ninsert at position: "); // getting the position the user wants
		String insertString = keyboard.nextLine();
		System.out.print("text: "); // Retrieving the text to insert at
		String text = keyboard.nextLine();
		
		int index = Integer.parseInt(insertString);
		
		line.newSubString(index, text); // passing in the index and text to the new substring method
	}
	
	/*
	 * This method will delete a substring based on the user's input
	 */
	public void deleteSubString()
	{
		line.showNumberLine(); // showing the number line
		System.out.print("\ndelete from: ");
		String deleteFrom = keyboard.nextLine();
		System.out.print("delete to: ");
		String deleteTo = keyboard.nextLine();
		
		int from = Integer.parseInt(deleteFrom);
		int to = Integer.parseInt(deleteTo);
		
		line.deleteSubString(from, to); // passing in the from and to position to the method so it can delete it
	}
	
	/*
	 * This method sends the newly edited line to the Document class 
	 */
	public void sendNewLine()
	{
		doc.getNewEditedLine(line.getText());
	}
}