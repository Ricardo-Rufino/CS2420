package WordNet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WordNet{
    private final String   synset;
    private final String   hypernyms;
    private final SAP      sap;
    private final Digraph  graph;

    // Constructor takes the name of the two input files.
    public WordNet(String synsets, String hypernyms){
        if(synsets == null || hypernyms == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        this.synset    = synsets;
        this.hypernyms = hypernyms;

        this.graph     = new Digraph(0);
        this.sap       = new SAP(graph);
    }

    // Returns all WordNet nouns.
    public Iterable<String> nouns() {
        List<String> list = new ArrayList<>();

        In in = new In(synset);
        while(in.hasNextLine()){
            if(isNoun(in.readLine()))
                list.add(in.readLine());
        }

        return list;
    }

    // Is the word a WordNet noun?
    public boolean isNoun(String word){
        if(word == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        String[] array = word.split(",");

        // Checks if the array is the correct size.
        if(array.length == 3){

            // Checks if the first element in the array is an integer.
            try{
                int check = Integer.parseInt(array[0]);
            }
            catch(NumberFormatException | NullPointerException nfe){
                return false;
            }

            return true;
        }
        else
            return false;
    }

    // Distance between nounA and nounB (defined below).
    public int distance(String nounA, String nounB){
        if(nounA == null || nounB == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        if(!isNoun(nounA) || !isNoun(nounB))
            throw new java.lang.IllegalArgumentException("Input must be a noun!");



        return 0;
    }

    /*
    A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    in a shortest ancestral path (defined below).
     */
    public String sap(String nounA, String nounB){
        if(nounA == null || nounB == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        return null;
    }

    // For unit testing.
    public static void main(String[] args) {
        String synsets   = "synsets.txt";
        String hypernyms = "hypernyms.txt";

        WordNet wordNet = new WordNet(synsets, hypernyms);

//        // List of all the nouns
//        for(String i : wordNet.nouns()){
//            System.out.println(i);
//        }

        System.out.println("Is this word a noun? " + wordNet.isNoun("hello"));
        System.out.println("Is this word a noun? " + wordNet.isNoun("82132,zip_gun,a crude homemade pistol"));
    }
}
