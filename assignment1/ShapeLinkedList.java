package assignment1;

import java.util.Arrays;

/**
 * Shaped link list stores shape objects in it in the form of nodes.
 * With these nodes, there are several methods to deal with the usage of these stored objects that are all documented with javadocs.
 * @author mjkovac, 042338145
 */
public class ShapeLinkedList
{
	public Node head; // Head is first node in linked list public ShapeLinkedList() { }
	private static int count; //used to scale the size of the list
	
	/**
	 * @param head Sets the list's head to one outside of its scope
	 */
	public ShapeLinkedList(Node head) 
	{
		this.head = head;
	}
	/**
	 * Default constructor, will assume safe state of:
	 * Head = null
	 * Size = 0;
	 */
	public ShapeLinkedList() 
	{
		head = null;
		count = 0;
	}
	
	/**
	 * Checks if the list has any nodes in it
	 * @return "zero" if the list has no nodes
	 */
	public boolean isEmpty()
	{
		return length() == 0;
	}
	/**
	 * Inserts the Data Object in the end of the list.
	 * @see insertAtIndex
	 * @param data = Shape Object to be inserted at the end
	 */
	public void insertAtEnd(Shape data) //reuse code, literally identical idea so this works well
	{ 
		insertAtIndex(count, data); 
	}
	/**
	 * Inserts the Data Object in the beginning of the list.
	 * @see insertAtIndex
	 * @param data = Shape Object to be inserted at beginning
	 */
	public void insertAtBeginning(Shape data)  
	{
		insertAtIndex(0, data); 
	}
	/**
	 * @return Node of the last Node in the list.
	 * Also see findAtIndex
	 */
	public Node tail()
	{
		return findAtIndex(length());
	}
	/**
	 * Number of elements in the list
	 * @return The size of the list in the form of an Integer.
	 */
	public int length()
	{
		return count;
	}
	/**
	 * Inserts Shape Object into index and allows all shapes to remain intact.
	 * @param idx = Index in which data is to be inserted. 
	 * @param data = Data which comes in the form of a Shape Object, to be inserted.
	 * @param idx
	 * @param data 
	 */
	void insertAtIndex(int idx, Shape data)
	{
		if (head == null)
		{
			head = new Node(data);
		}
		
		Node temp = new Node(data);
		Node curr = head;
		if (idx != 0)
		{		
			if (curr != null)
				for (int i = 0; i < length() && curr.getNext() != null; i++)
					curr = curr.getNext();
		}
		
		temp.setNext(curr.getNext());
		curr.setNext(temp);
		
		count++;
	}
	/**
	 * Returns the node in the list at the specified index
	 * @param idx Index in which we are looking for node
	 * @return Node at the specified index
	 */
	Node findAtIndex(int idx) 
	{
		if (idx >= length())
		{
			throw new NullPointerException("Enter a value below or equal to " + String.valueOf(length()-1));
		}
		Node temp = head;		
		for (int i = 0; i < idx+1; i++)
				temp = temp.getNext();
	
		return temp;
	}
	/**
	 * Deletes the node at the specified index. As a result, the size of the list will be decreased by one. 
	 * @param idx Index of the node
	 */
	void deleteAtIndex(int idx) 
	{
		Node curr = head;
		if (head != null)
		{
			for (int i = 0; i < idx; i++)
			{
				if (curr.getNext() == null)
				{
					System.out.println("Cannot reach element.");
					return;
				}
				curr = curr.getNext();
			}
			curr.setNext(curr.getNext().getNext());
			count--;
		}
	}
	/**
	 * Will find the shape object in the list and remove it. Also reduces the size of the list by one.
	 * @param s Shape object
	 */
	void deleteData(Shape s) 
	{
		Node curr = head;
		if (head != null)
		{
			for (int i = 0; i < length(); i++)
			{
				if (curr.getNext() == null)
				{
					System.out.println("Cannot reach element.");
					return;
				}
				if (curr.getNext().getData().equals(s) || curr.getNext().getData() == s)
				{
					curr.setNext(curr.getNext().getNext());
					count--;
					return;
				}
				curr = curr.getNext();
			}
			System.out.println("Could not find the shape in the list.");
		}
	}
	@Override
	public String toString()
	{ 
		String output = "";
		
		if (head != null)
		{
			Node curr = head.getNext();
			while (curr != null)
			{
				output += curr.getData().toString() + "\n";
				curr = curr.getNext();
			}
		}
		
		return output;
	}
	@Override
	public int hashCode() 
	{
		return Arrays.hashCode(new Object[]{head});
	}
	@Override
	public boolean equals(Object obj) 
	{
		return (this == obj);
	} 
	
	public static class Node 
	{
		private Shape data; 
		private Node next;
		
		public Node(Shape d)
		{
			next = null;
			data = d;
		}
		public Node(Shape d, Node n)
		{
			next = n;
			data = d;
		}
		public void setData(Shape d) 
		{
			data = d;
		}
		public void setNext(Node n)
		{
			next = n;
		}
		public Shape getData()
		{
			return data;
		}
		public Node getNext() 
		{
			return next;
		}
	}
}
