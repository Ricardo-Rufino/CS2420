package AutoComplete;

import java.util.Arrays;
import java.util.Comparator;

public final class Term implements Comparable<Term> {
    private final String query;
    private final double weight;

    public Term(String query, double weight) {

        if (query == null)
            throw new NullPointerException();
        if (weight < 0)
            throw new IllegalArgumentException();

        this.query  = query;
        this.weight = weight;
    }

    public static Comparator<Term> byReverseOrder() {
        return new ComparatorByReverseOrderWeight();
    }

    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0)
            throw new IllegalArgumentException();

        return new ComparatorByPrefixOrder(r);
    }

    private static class ComparatorByReverseOrderWeight implements Comparator<Term> {
        @Override
        public int compare(Term first, Term second) {
            if (first.weight == second.weight)
                return 0;
            if (first.weight > second.weight)
                return -1;
            return 1;
        }
    }

    private static class ComparatorByPrefixOrder implements Comparator<Term> {
        private int r;

        private ComparatorByPrefixOrder(int r) {
            this.r = r;
        }

        @Override
        public int compare(Term first, Term second) {
            String prefixA, prefixB;

            if (first.query.length() < r)
                prefixA = first.query;
            else
                prefixA = first.query.substring(0, r);

            if (second.query.length() < r)
                prefixB = second.query;
            else
                prefixB = second.query.substring(0, r);

            return prefixA.compareTo(prefixB);
        }


    }

    @Override
    public int compareTo(Term o) {
        return this.query.compareTo(o.query);
    }

    @Override
    public String toString() {
        return weight + "\t" + query;
    }

    public static void main(String[] args) {
        String first  = "Ricardo";
        String second = "James";

        Term[] array = {new Term(first, 20), new Term(second, 10)};

        Arrays.sort(array);
        for(Term i : array){
            System.out.println(i);
        }
        System.out.println();

        Arrays.sort(array, Term.byPrefixOrder(3));
        for(Term i : array){
            System.out.println(i);
        }
        System.out.println();

        Arrays.sort(array, Term.byReverseOrder());
        for(Term i : array){
            System.out.println(i);
        }
        System.out.println();;
    }
}