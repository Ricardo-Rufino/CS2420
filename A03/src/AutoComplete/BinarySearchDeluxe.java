package AutoComplete;

import java.util.Arrays;
import java.util.Comparator;


public class BinarySearchDeluxe {

    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException();

        int lo = 0;
        int hi = a.length - 1;
        int mid;

        if (comparator.compare(a[0], key) == 0)
            return 0;

        while (lo <= hi) {

            mid = lo + (hi - lo) / 2;

            if (comparator.compare(key, a[mid]) < 0)
                hi = mid - 1;
            else if (comparator.compare(key, a[mid]) > 0)
                lo = mid + 1;
            else if (comparator.compare(a[mid - 1], a[mid]) == 0)
                hi = mid - 1;
            else
                return mid;
        }

        return -1;
    }

    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null)
            throw new IllegalArgumentException();

        int lo = 0;
        int hi = a.length - 1;

        if (comparator.compare(a[hi], key) == 0)
            return hi;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (comparator.compare(key, a[mid]) < 0)
                hi = mid - 1;
            else if (comparator.compare(key, a[mid]) > 0)
                lo = mid + 1;
            else if (comparator.compare(a[mid + 1], a[mid]) == 0)
                lo = mid + 1;
            else
                return mid;
        }

        return -1;
    }
    public static void main(String[] args) {

        Term[] array = {new Term("Ricardo", 20), new Term("James", 10),
                        new Term("Zeek", 50),    new Term("Brendan", 22),
                        new Term("Ricky", 15),   new Term("Alex", 0)};

        Arrays.sort(array);

        for(Term i : array){
            System.out.println(i);
        }
        System.out.println();

        int firstIndex = BinarySearchDeluxe.firstIndexOf(array, new Term("Ricardo", 0), Term.byPrefixOrder(1));
        System.out.println("first: " + firstIndex);

    }

}
