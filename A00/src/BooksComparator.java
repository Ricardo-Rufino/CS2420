import java.util.Comparator;

public class BooksComparator implements Comparator<Books>
{
	@Override
	public int compare(Books first, Books second)
	{
		return (int) second.getTitle().compareTo(first.getTitle());
	}
}
