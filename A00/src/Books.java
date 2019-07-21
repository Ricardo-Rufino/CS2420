// Ricardo Rufino
// Assignment A00

import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;

public class Books implements Comparable<Books>
{
	private String title;
	private String author;
	private int year;
	
	public Books(String title, String author, int year)
	{
		this.title  = title;
		this.author = author;
		this.year   = year;
	}

	public String getTitle()
	{
		return title;
	}

	public String getAuthor()
	{
		return author;
	}

	public int getYear()
	{
		return year;
	}

	public static List<Books> getList(String file)
	{
		String temp[];
		String line;
		
		String title; String author; int year;
		
		List<Books> list = new ArrayList<>();
		
		try(Scanner reader = new Scanner(new FileReader("src\\" + file))) 
		{
			while(reader.hasNextLine())
			{
				line = reader.nextLine();
				temp = line.split(",");
				
				if(temp.length == 3)
				{
					title  = temp[0];
					author = temp[1];
					year   = Integer.parseInt(temp[2]);
					
					list.add(new Books(title, author, year));
				}
				else
				{
					System.err.println("Problem reading in: " + line);
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("Something went wrong");
		}
		
		return list;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s by %s (%d)", title, author, year);
	}

	@Override
	public int compareTo(Books other)
	{
		return this.getTitle().compareTo(other.getTitle());
	}

	
}
