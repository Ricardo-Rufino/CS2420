import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BooksApp
{

	public static void main(String[] args) 
	{		
		
		List<Books> list = Books.getList("books.csv");
		System.out.println(list.size() + "\n");
				
		// Printing the list in the natural order
		Collections.sort(list);
		for (Books i : list)
		{
			System.out.println(i.toString());
		}
		System.out.println();
		
		
		
		// Printing the list in the reverse order
		Collections.sort(list, new BooksComparator());
		for (Books i : list)
		{
			System.out.println(i.toString());
		}
		System.out.println();
	}
	

}
