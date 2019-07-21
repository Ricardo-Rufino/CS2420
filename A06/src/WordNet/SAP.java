package WordNet;

import edu.princeton.cs.algs4.Digraph;

public class SAP {
    private final Digraph G;

    // Constructor takes a digraph (not necessarily a DAG).
    public SAP(Digraph G){
        this.G = G;
    }

    // Is the digraph a directed acyclic graph?
    public boolean isDAG(){
        return false;
    }

    // Is the digraph a rooted DAG?
    public boolean isRootedDAG(){
        return false;
    }

    // Length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){
        return 0;
    }

    // A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w){
        return 0;
    }

    // Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        return 0;
    }

    // Unit testing
    public static void main(String[] args) {

    }
}
