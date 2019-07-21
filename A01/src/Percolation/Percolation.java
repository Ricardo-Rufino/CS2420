/*
 * Ricardo Rufino
 * Assignment A01
 * Percolation Class
 */

package Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* note: Empty and open are defined as followed:
 * Blocked = 0
 * Open    = 1
 */

class Percolation
{	
	private int[][] grid;
	private int[] gridArray;
	private final int size;
	
	private int n;
	private int top;
	private int bottom;
	private WeightedQuickUnionUF uf;

	//This method creates and nxn grid, with all sides blocked.------------------------------------------------------------
	public Percolation (int n)
	{
		if (n <= 0)
			throw new IllegalArgumentException();
		
		this.n = n;
		top    = n*n;
		bottom = n*n + 1;
		
		grid   = new int[n][n];
		uf     = new WeightedQuickUnionUF(n*n + 2);	
		size   = n;
	}
	
	//This method opens the site(row i, column j) if it is not open already.-----------------------------------------------
	public void open(int i, int j)
	{
		isValid(i, j);
		
		if(grid[i][j] == 0)
		{
			grid[i][j] = 1;
			
			if(i > 0 && isOpen(i-1, j))
				uf.union(toArray(i,j), toArray(i-1, j));	//Set union with top block.
			if(i < (n-1) && isOpen(i+1, j))
				uf.union(toArray(i,j), toArray(i+1, j));	//Set union with bottom block.
			if(j > 0 && isOpen(i, j-1))
				uf.union(toArray(i,j), toArray(i, j-1));	//Set union with left block.
			if(j < (n-1) && isOpen(i, j+1))
				uf.union(toArray(i,j), toArray(i, j+1));	//Set union with right block.
			if(i == 0)										
				uf.union(toArray(i, j), top);				//If element is in the top row; set union with imaginary top block.
			if(i == (n - 1))								
				uf.union(toArray(i, j), bottom);			//If element is in the bottom row; set union with imaginary bottom block.
		}
	}
	
	//This method answers the following: is the site open? (open = True, closed = False)-----------------------------------
	public boolean isOpen(int i, int j)
	{
		isValid(i,j);
		return grid[i][j] == 1;
	}
	
	//This method answers the following: is the site full? (full = True, empty = False)------------------------------------
	public boolean isFull(int i, int j)
	{
		return uf.connected(toArray(i, j), top);
	}
	
	//This method answers the following: does the system percolate? (yes = True, no = False)-------------------------------
	public boolean percolates()
	{
		return uf.connected(top, bottom);
	}
	
	private void isValid(int i, int j)
	{
		if(i < 0 || i > (size-1) || j < 0 || j > (size-1))
		{
			throw new IndexOutOfBoundsException();
		}
	}
	
	//This function transforms the index of the 2d array to the index of an array.-----------------------------------------
	private int toArray(int i, int j)
	{
		return i*n + j;
	}
}
