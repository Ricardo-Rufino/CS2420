package AutoComplete;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public final class Autocomplete {

    private final Term[] terms;

    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new NullPointerException("Terms can't be null");
        this.terms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++)
            this.terms[i] = terms[i];
        Arrays.sort(this.terms);
    }

    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException();

        int first = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        if (first == -1)
            return new Term[0];

        int last = BinarySearchDeluxe.lastIndexOf (terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        Term[] matchTerms = new Term[last - first + 1];

        for (int i = 0; i < matchTerms.length; i++)
            matchTerms[i] = terms[first++];

        Arrays.sort(matchTerms, Term.byReverseOrder());

        return matchTerms;
    }

    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new NullPointerException("Prefix can't be null");
        return 1 + BinarySearchDeluxe.lastIndexOf (terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length())) -
                   BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
    }

    public static void main(String[] args) {

        //Large arbitrary number
        int N = 10000;                                  // Should be changed for every file

        // Read in the terms from a file
        String filename = args[0];
        In in           = new In(filename);
        Term[] terms    = new Term[N];

        System.out.println("Fields have been declared.");

        for(int i = 0; i < N; i++){
            double weight = in.readDouble();

            in.readChar();

            String query = in.readLine();
            terms[i] = new Term(query, weight);
        }

        System.out.println("terms array has been created");

        // Read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        System.out.println("Autocomplete object has been created");

        while(StdIn.hasNextLine()){
            String prefix  = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);

            for(int i = 0; i < Math.min(k, results.length); i++){
                System.out.println(results[i]);
                System.out.println(i);
            }
        }

        System.out.println("Complete implementation");

    }

}
