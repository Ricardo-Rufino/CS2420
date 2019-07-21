/*
 * Ricardo Rufino
 * Assignment A02
 * Due Date: 06/04/2019
 */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
	private Node<Item> head;								// Node that references the first item in the list.
	private Node<Item> tail;								// Node that references the last item in the list.
	private Node<Item> newNode;
	private int size; 										// Number of nodes in the list
	
	public Deque()
	{								
		size = 0;											// The list is empty, no nodes in the list.
	}
	
	public boolean isEmpty()			
	{
		return size == 0;									// The only time the head node = null is when the list is empty.
	}														// ... Null items cannot be inserted.
	
	public int size()
	{
		return size;
	}
	
	public void addFirst(Item item)
	{
		if(item == null)
			throw new java.lang.NullPointerException();
		else
		{
			newNode = new Node(item);
			
			if(head != null)
			{
				newNode.next  = head;
				head.previous = newNode;
			}
			head = newNode;
			
			if(tail == null)
				tail = head;
			
			size++;
		}
	}
	
	public void addLast(Item item)
	{
		if(item == null)
			throw new java.lang.NullPointerException();
		else
			
		{	
			newNode = new Node(item);
			
			if(tail != null)
			{
				tail.next        = newNode;
				newNode.previous = tail;
			}
			
			if(head == null)
				head = tail;
			
			size++;
		}
		
	}
	
	public Item removeFirst()
	{
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		else
		{
			Item item = head.item;							// Temporarily store the value of the current, first node's item to this variable.
			
			head      = head.next;							// Set the new head to the old's next pointer.
			
			size--;											// Decrease size counter.
			
			return item;
		}
	}
	
	public Item removeLast()
	{
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		else
		{
			Item item = tail.item;							// Temporarily store the value of the current, last node's item to this variable.
			
			tail      = tail.previous;						// Set the new tail to the old's previous pointer.
			
			size--;											// Decrease the size counter.
			
			return item;
		}
	}

	// Overriding Iterator method w/ inner class ---------------------------------------------------------------------------
	@Override
	public Iterator<Item> iterator() 
	{
		return new dequeIterator();
		
	}
	
	private class dequeIterator implements Iterator<Item>
	{
		Node<Item> current = head;
		Node<Item> temp;
		
		@Override
		public boolean hasNext() 
		{
			return current != null;
		}

		@Override
		public Item next() 
		{
			if(current == null)
				throw new java.util.NoSuchElementException();
				
			temp    = current;
			current = current.next;
			
			return temp.item;
		}
		
		@Override
		public void remove()
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}
	
	// Private class for linked-list data  implementation ---------------------------------------------------------
	private class Node<Item>
	{
		public Node<Item> previous;
		public Item item;
		public Node<Item> next;
		
		public Node(Item item)
		{
			this.item = item;
		}
		public Node(Node<Item> next, Item item, Node<Item> previous)
		{
			this.previous = previous;
			this.item     = item;
			this.next     = next;
		}
	}
	
	// For feasibility testing *********************************************************************************************
	public static void main(String[] args) 
	{
		Deque<String> dq = new Deque<>();
		
		dq.addFirst("is");
		dq.addFirst("Test");
		dq.addLast("complete");
		
		Iterator<String> iter = dq.iterator();
		
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		System.out.println();
		
//		System.out.println(dq.isEmpty());
//		System.out.println(dq.size());
//		System.out.println(dq.removeFirst());
//		System.out.println(dq.size());
//		System.out.println(dq.removeLast());
//		System.out.println(dq.size());
//		System.out.println(dq.removeLast());
		
	}

}
