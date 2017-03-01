public class DoublyLinkedList 
{
	private int size = 1; // keeping track of the size
	private Node head;

	/*
	 * Checking if the list is empty
	 * Returns true if the head is null
	 * and returns false if the head is not null
	 */
	public boolean isEmpty()
	{
		return head == null;
	}

	/*
	 * Returns the size of the list
	 */
	public int getSize()
	{
		return size;
	}

	/*
	 * Prints the list
	 */
	public void print()
	{
		if(isEmpty())
			System.out.println("Emtpy List");

		Node current = head; // Assigning a temporary node to head
		int i = 1;
		while(current != null) // traversing the list
		{
			System.out.println(i + ": " + current.data); // printing out the list
			current = current.next; // advancing 
			i++;
		}
	}
	
	/*
	 * This method inserts a node at the beginning of the list
	 */
	public void insertAtStart(String data)
	{
		Node ptr = new Node(data);
		if(head == null) // checking if the list is empty
			head = ptr; // if list is empty assign the head to the new node
		else
		{
			head.prev = ptr; // pointing the prev pointer of head to the new node so we don't lose the list
			ptr.next = head; // pointing the next pointer of the new node to head
			head = ptr; // head is now the new node
		}
		size++;
	}

	/*
	 * This method inserts a node at the end of the list
	 */
	public void insertAtEnd(String data)
	{
		if(head == null) // if the list is empty
			insertAtStart(data); // insert at the beginning of the list
		else
		{
			Node current = head;
			while(current.next != null) // traversing through the list
			{
				current = current.next; // advancing
			}
			Node newNode = new Node(data);
			current.next = newNode; // the next pointer of current is assigned the new node
		}
		size++;
	}
	
	/* 
	 * This method inserts a node at a given position
	 */
	public void insertAt(String data, int pos)
	{
		/*
		 * If the given pos is not in range, show error message
		 */
		if(pos < 1 || pos > size)
		{
			System.out.println("Position Out Of Bounds");
			return;
		}
		
	    if(head == null)
	    {
	        head = new Node(data);
	    }
	    else
	    {
	        Node newNode = new Node(data);
	        Node current = head , prevToCurrent = null;
	        
	        /*
	         * while the current node is not empty and is less than the given position
	         */
	        for(int x = 1; current != null && x < pos; x++)
	        {
	            prevToCurrent = current; 
	            current = current.next; // advancing
	        }
	        // Case One: if current is equal to the head node
	        if(current == head)
	        {
	            if(head.next != null) 
	            {
	            	// inserting the new node after the first node
	                newNode.next = head;
	                head = newNode;
	            }
	            else
	            {
	            	// if the node is first, then the new node is head
	                head = newNode;
	            }
	        }
	        // second case:
	        // if the current node is null
	        else if(current == null)
	        {
	        	// insert at the end
	            prevToCurrent.next = newNode;
	            newNode.prev = prevToCurrent;
	        }
	        else
	        {
	        	// insert at the given location
	            current.prev.next = newNode;
	            newNode.prev = current.prev.next;

	            newNode.next = current;
	            current.prev = newNode;
	        }
	    }
	    size++;
	}
	
	/*
	 * This method removes a node at the beginning
	 */
	public void removeAtFront()
	{
		if(head == null)
			System.out.println("Emtpy List");

		head = head.next;
		head.prev = null; // removing
		size--;
	}

	/* 
	 * Removing a node at the end of the list
	 */
	public void removeAtEnd()
	{
		if(head == null)
			System.out.println("Emtpy List");

		/*
		 * Only if there is one node
		 */
		if(head.next == null)
		{
			head = null; // removing
			size--;
		}

		Node current = head;
		// traversing for the next node
		while(current.next.next != null)
		{
			current = current.next; // advancing
		}
		current.next = null; // the next pointer is null so that means it is at the end
		size--;
	}

	/*
	 * Removing a node at a given position
	 */
	public void removeAt(int pos)
	{
		if(head == null)
			System.out.println("Emtpy List");
		// checking if the given pos is in bounds of the list
		if(pos < 1 || pos > size)
			System.out.println("Position Out Of Bounds");

		Node current = head;
		int i = 1;
		// traversing through the list until the position
		while(i < pos)
		{
			current = current.next; // advancing
			i++;
		}

		// if the node is at the end
		if(current.next == null)
		{
			current.prev.next = null; // remove the node that is pointing to the last node
		}
		else if(current.prev == null) // removing the node that is second to last
		{
			current = current.next;
			current.prev = null;
			head = current;
		}
		else // remove the node at the position
		{
			current.prev.next = current.next;
			current.next.prev = current.prev;
		}
		size--;
	}
	
	/*
	 * Returning a string of the data for the given position
	 */
	public String get(int pos)
	{
		if(pos < 1 || pos > size)
		{
			System.out.println("Position Out Of Bounds");
			return null;
		}
		
		if(isEmpty())
		{
			System.out.println("List is empty");
			return null;
		}
		
		Node current = head;
		// traversing the list until the position
		for(int i = 1; i < pos; i++)
		{
			current = current.next; //advancing to that node
		}
		return current.data; // returning a string of the node
	}

	/*
	 * Inner Class Node
	 */
	private class Node
	{
		Node next, prev;
		String data;

		/*
		 * Constructor to initialize the data
		 */
		public Node(String data)
		{
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}
}
