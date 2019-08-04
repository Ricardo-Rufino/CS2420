package WordNet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.List;

public class SAP
{
    private final Digraph graph;
    private boolean rooted;

    // Constructor takes a digraph (not necessarily a DAG).
    public SAP(Digraph graph)
    {
        if(graph == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        this.graph = graph;

        // Check if there's a root vertex.
        int apperance = 0;
        for(int v = 0; v < graph.V(); v++)
        {
            if(graph.outdegree(v) == 0)
                apperance++;
        }

        if(apperance == 1)
            rooted = true;
    }

    // Is the digraph a directed acyclic graph? ------------------------------------------------------------------------
    public boolean isDag()
    {
        boolean[] visited  = new boolean[graph.V()];
        boolean[] recStack = new boolean[graph.V()];

        for(int v = 0; v < graph.V(); v++){
            if(isCyclic(v, visited, recStack))
                return false;
        }

        return true;
    }

    // Helper function that calculates distance from vertex to root. Returns true if graph is a DAG.
    private boolean isCyclic(int v, boolean[] visited, boolean[] recStack)
    {
        if(recStack[v])                         // Checks if v is in the recursive stack; if yes, cycle exists.
            return true;

        if(visited[v])                          // No need to look at vertex if already visited.
            return false;

        visited[v]  = true;                     // Vertex is now visited.
        recStack[v] = true;                     // Vertex is added to recursive stack.

        // We now look at all the adjacent nodes of v.
        for(int w : graph.adj(v)){
            if(isCyclic(w, visited, recStack))
                return true;
        }

        recStack[v] = false;                    // Vertex is removed from recursive stack.

        return false;                           // No cycles found.
    }

    // Is the digraph a rooted DAG?
    public boolean isRootedDAG()
    {
        return rooted && isDag();
    }

    // Length of shortest ancestral path between v and w; -1 if no such path -------------------------------------------
    public int length(int v, int w)
    {
        if(v < 0 || w < 0 || v > graph.V()-1 || w > graph.V()-1)
            throw new java.lang.IndexOutOfBoundsException();

        return BFS(v, w)[1];
    }

    // A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        if(v < 0 || w < 0 || v > graph.V()-1 || w > graph.V()-1)
            throw new java.lang.IndexOutOfBoundsException();

        return BFS(v, w)[0];
    }

    // A common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        if(v == null || w == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        int minDistance = -1;
        int ancestor    = -1;
        int[] results = new int[2];             // First element = ancestor; second element = minimum distance.

        for(int i : v)
        {
            for(int j : w)
            {
                results = BFS(i, j);

                if(minDistance < 0 || results[1] < minDistance){
                    ancestor    = results[0];
                    minDistance = results[1];
                }
            }
        }

        return ancestor;
    }

    // Helper function that performs BFS.
    private int[] BFS(int v, int w)
    {

        BreadthFirstDirectedPaths vBFS = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths wBFS = new BreadthFirstDirectedPaths(graph, w);

        int ancestor    = -1;
        int minDistance = -1;
        int distance;

        for(int i = 0; i < graph.V(); i++)
        {
            if(vBFS.hasPathTo(i) && wBFS.hasPathTo(i)){
                distance = vBFS.distTo(i) + wBFS.distTo(i);

                if(minDistance < 0 || distance < minDistance)
                {
                    minDistance = distance;
                    ancestor = i;
                }
            }
        }

        return new int[] {ancestor, minDistance};
    }

    // Unit testing ----------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        // Testing a cyclic graph.
        System.out.println("This graph has a cycle; checking if class detects it.");
        Digraph graph1 = new Digraph(3);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 0);

        SAP sapCycle = new SAP(graph1);
        System.out.println("\tGraph does " + (sapCycle.isDag()? "not have a cycle." : "have a cycle."));
        System.out.println();

        // Testing acyclic graph.
        System.out.println("Checking if class detects a DAG.");
        Digraph graph2 = new Digraph(11);
        graph2.addEdge(1, 0);   graph2.addEdge(6,  3);
        graph2.addEdge(2, 0);   graph2.addEdge(7,  3);
        graph2.addEdge(3, 1);   graph2.addEdge(8,  5);
        graph2.addEdge(4, 1);   graph2.addEdge(9,  5);
        graph2.addEdge(5, 1);   graph2.addEdge(10, 9);

        SAP sapDAG = new SAP(graph2);
        System.out.println("\tGraph does " + (sapDAG.isDag()? "not have a cycle." : "have a cycle."));
        System.out.println("\tShortest ancestral of (1, 2):  " + sapDAG.ancestor(1, 2));
        System.out.println("\tShortest ancestral of (3, 5):  " + sapDAG.ancestor(3, 5));
        System.out.println("\tShortest ancestral of (6, 10): " + sapDAG.ancestor(6, 10));
        System.out.println("\tShortest ancestral of (2, 10): " + sapDAG.ancestor(2, 10));

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        list1.add(6); list1.add(7);
        list2.add(8); list2.add(9);

        System.out.println("\tShortest ancestral of list: {6, 7} and {8, 9}:  " + sapDAG.ancestor(list1, list2));
    }
}
