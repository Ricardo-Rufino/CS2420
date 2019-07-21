package Percolation;
import edu.princeton.cs.algs4.StdStats;


public class PercolationApp
{
	public static void main(String[] args)
	{

		PercolationStats per = new PercolationStats(2, 100000);
		
		System.out.println(per.mean());
		System.out.println(per.stddev());
		System.out.println(per.confidenceLow());
		System.out.println(per.confidenceHighLow());
	}

}
