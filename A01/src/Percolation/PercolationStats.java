/*
 * Ricardo Rufino
 * Assignment A01
 * PercolationStas Class
 */

package Percolation;

import java.util.Random;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
	private int n;
	private int T;
	private int i;
	private int j;
	private double openBlocks;
	private double[] p;
	
	private double average;
	private double std;
	
	private Percolation per;
	
	public PercolationStats(int n, int T)
	{
		if (n < 1 || T < 1)
			throw new IllegalArgumentException();
		
		this.n     = n;
		this.T     = T;
		p = new double[T];
		
		for(int trial = 0; trial < T; trial++)
		{
			per        = new Percolation(n);
			openBlocks = 0;
			
			while(!per.percolates())
			{
				i = StdRandom.uniform(n);
				j = StdRandom.uniform(n);
				per.open(i, j);
			}
			
			for(int x = 0; x < n; x++)
			{
				for(int y = 0; y < n; y++)
				{
					if(per.isOpen(x, y))
						openBlocks++;
				}
			}
			p[trial] = openBlocks/(n*n);
		}
		
		average = StdStats.mean(p);
		std     = StdStats.stddev(p);
		
	}
	
	public double mean()
	{
		return average;
	}
	
	public double stddev()
	{
		return std;
	}
	
	public double confidenceLow()
	{
		return average - 1.96*std/Math.pow(T, 0.5);
	}
	
	public double confidenceHighLow()
	{
		return average + 1.96*std/Math.pow(T, 0.5);
	}
	
}
