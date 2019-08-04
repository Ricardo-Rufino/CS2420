package WordNet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WordNet
{
    private final String  synset;
    private final String  hypernyms;
    private final SAP     sap;
    private final Digraph graph;
    private int vertices;

    private List<String> nounList;

    private SeparateChainingHashST<Integer, List<String>>  vLocator;
    private SeparateChainingHashST<String,  List<Integer>> iLocator;
    private SeparateChainingHashST<Integer, Integer[]>     eLocator;

    // Constructor takes the name of the two input files. --------------------------------------------------------------
    public WordNet(String synsets, String hypernyms)
    {
        if(synsets == null || hypernyms == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        nounList = new ArrayList<>();
        vLocator = new SeparateChainingHashST<>();
        iLocator = new SeparateChainingHashST<>();
        eLocator = new SeparateChainingHashST<>();

        this.synset    = synsets;
        this.hypernyms = hypernyms;
        hashTableConstructor();                         // Reads data from synset file and creates the iterable list.

        this.graph     = new Digraph(vertices);
        diagraphConstructor();                          // Creates diagraph with hypernyms file.
        this.sap       = new SAP(graph);

        if(!sap.isRootedDAG())
            throw new java.lang.IllegalArgumentException("Input must be a rooted DAG!");
    }

    // Helper function that constructs both the hashtable and iterable list of nouns.
    private void hashTableConstructor()
    {
        int id;
        String[] nouns;
        String[] line;

        In in = new In(synset);
        while(in.hasNextLine()){
            vertices++;

            line  = in.readLine().split(",");     // [0] nounID; [1] noun; [2] noun definition.
            id    = Integer.parseInt(line[0]);
            nouns = line[1].split(" ");

            vLocator.put(id, new ArrayList<>());
            for(String noun : nouns){
                if(!iLocator.contains(noun)){
                    nounList.add(noun);

                    vLocator.get(id).add(noun);
                    iLocator.put(noun, new ArrayList<>());
                }
                iLocator.get(noun).add(id);
            }
        }
    }

    // Helper function that constructs the diagraph.
    private void diagraphConstructor()
    {
        String[]  line;
        Integer[] edges;
        int vID;

        In in = new In(hypernyms);
        while(in.hasNextLine()){
            line  = in.readLine().split(",");
            edges = new Integer[line.length-1];
            vID   = Integer.parseInt(line[0]);

            for(int i = 1; i < line.length; i++){
                graph.addEdge(vID, Integer.parseInt(line[i]));
                edges[i-1] = Integer.parseInt(line[i]);
            }

            eLocator.put(vID, edges);
        }
    }

    // Returns all WordNet nouns. --------------------------------------------------------------------------------------
    public Iterable<String> nouns()
    {
        return nounList;
    }

    // Is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        return iLocator.contains(word);
    }

    // Distance between nounA and nounB (defined below).
    public int distance(String nounA, String nounB)
    {
        if(nounA == null || nounB == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        if(!isNoun(nounA) || !isNoun(nounB))
            throw new java.lang.IllegalArgumentException("Input must be a noun!");

        return sap.ancestor(iLocator.get(nounA), iLocator.get(nounB));
    }

    /*
    A synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    in a shortest ancestral path (defined below).
     */
    public String sap(String nounA, String nounB)
    {
        if(nounA == null || nounB == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        Iterable<Integer> listA = iLocator.get(nounA);
        Iterable<Integer> listB = iLocator.get(nounB);

        int commonAncestorID = sap.ancestor(listA, listB);

        String result = "";
        for(String i : vLocator.get(commonAncestorID)){
            result += i;
        }

        return result;
    }

    // For unit testing. -----------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        String synsets   = "synsets.txt";
        String hypernyms = "hypernyms.txt";

        WordNet wordNet = new WordNet(synsets, hypernyms);

//        // List of all the nouns
//        for(String i : wordNet.nouns()){
//            System.out.println(i);
//        }

        System.out.println("Is this word a noun? " + wordNet.isNoun("zz"));
        System.out.println("Is this word a noun? " + wordNet.isNoun("zoolatry"));
        System.out.println("Is this graph rooted and DAG? " + wordNet.sap.isRootedDAG());
        System.out.println("Ancestor of zip_gun and ");
    }
}
