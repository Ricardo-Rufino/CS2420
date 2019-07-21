import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] q;
	private int nItems;
	private int arraySize;
	
	public RandomizedQueue()
	{
		q         = (Item[]) new Object[2];
		nItems    = 0;
		arraySize = 2;
	}
	
	public boolean isEmpty()
	{
		return nItems == 0;
	}
	
	public int size()
	{
		return nItems;
	}
	
	public void enqueue(Item item)
	{
		if(item == null)
			throw new java.lang.NullPointerException();
		else
		{
			q[nItems] = item;													//Add item to the queue.
			nItems++;															//Increment item counter.
			resize();															//Resize the array if needed.
		}
	}
	
	public Item dequeue()
	{
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		else
		{
			int rand = StdRandom.uniform(nItems);
			
			Item randItem = q[rand];
			
			if(rand != (nItems-1))
				q[rand] = q[nItems-1];
			
			q[nItems-1] = null;
			nItems--;
			resize();
			
			return randItem;
		}
	}
	
	public Item sample()
	{
		int rand = StdRandom.uniform(nItems);
		
		return q[rand];
	}
	
	//This function resizes the array as needed-------------------------------------------------------------------------------------------------
	public void resize()
	{
		if(nItems >= arraySize/2)												//If array is half full, the array becomes twice as large.
		{			
			Item[] tempArray = (Item[]) new Object[2*arraySize];
			
			for(int i = 0; i < nItems; i++)
			{
				tempArray[i] = q[i];
			}
			
			q         = tempArray;												//Re-initialization of q array.
			arraySize = 2*arraySize;											//Array size is doubled.
		}
		else if(nItems <= arraySize/4)											//If array is a quarter full, the array size is halved.
		{
			Item[] tempArray = (Item[]) new Object[arraySize/2];						
			
			for(int i = 0; i < nItems; i++)
			{
				tempArray[i] = q[i];	
			}
			
			q         = tempArray;												//Re-initialization of q array.
			arraySize = arraySize/2;											//Array size is halved.
		}
		else
			return;
	}
	
	// Implementation of Iterator interface------------------------------------------------------------------------------------------------------
	@Override
	public Iterator<Item> iterator() 
	{
		return new rqIterator();
	}
	
	private class rqIterator implements Iterator<Item>
	{

		@Override
		public boolean hasNext() 
		{
			return false;
		}

		@Override
		public Item next() 
		{
			return null;
		}
		
		@Override
		public void remove()
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}

	//For feasibility testing*******************************************************************************************************************
	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		rq.enqueue("comoplete");
		rq.enqueue("is");
		rq.enqueue("Test");
		
		System.out.println(rq.size());
	}

}
