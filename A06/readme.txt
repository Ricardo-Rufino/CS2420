/******************************************************************************
 *  Name:     Ricardo Rufino
 *  NetID:    N/A
 *  Precept:  N/A
 *
 *  Partner Name:	N/A     
 *  Partner NetID:    	N/A
 *  Partner Precept:  	N/A
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 6: WordNet


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 *****************************************************************************/

	Used two hash tables. One that uses the nouns as a key and the other
	uses the noun ID as the key.

/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 *****************************************************************************/

	Used a hash table. Used the ID as the key and set the edges as
	the values (type Integer[]).

/******************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithms as a function of the number of vertices V and the
 *  number of edges E in the digraph?
 *****************************************************************************/

Description:
	To check if graph is a rooted, the WordNet class looks if there exists
	a vertex that has no outgoing edges. If one exists, the graph is rooted.
	
	To check if the graph is a DAG, a recursive helper method is called that 
	performs a BFS. A list of the vertices that have been visited by the
	recursive method is stored; if the these are visited more than once,
	there exists a cycle.


Order of growth of running time:
	N + N^2

/******************************************************************************
 *  Describe concisely your algorithm to compute the shortest common
 *  ancestor in ShortestCommonAncestor. For each method, what is the order of
 *  growth of the worst-case running time as a function of the number of
 *  vertices V and the number of edges E in the digraph? For each method,
 *  what is the order of growth of the best-case running time?
 *
 *  If you use hashing, you should assume the uniform hashing assumption
 *  so that put() and get() take constant time.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't
 *  forget to count the time needed to initialize the marked[],
 *  edgeTo[], and distTo[] arrays.
 *****************************************************************************/

Description:
	To find the shortest ancestor, the algorithms looks at every vertex
	that both nouns connect to via BFS. The vertex with the minimum
	distance is the nearest ancestor.
                                              running time
method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w)			V + E		     V + E

ancestor(int v, int w)			V + E		     V + E

length(Iterable<Integer> v,		V + E		     V + E
       Iterable<Integer> w)

ancestor(Iterable<Integer> v,	        V + E		     V + E
         Iterable<Integer> w)


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

	None so far.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/

	None.

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

	Finding cycles in the graph.

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/

	Did not worth with someone else.

/**********************************************************************
 *  Have you completed the mid-semester survey? If you haven't yet,
 *  please complete the brief mid-course survey at https://goo.gl/gB3Gzw
 * 
 ***********************************************************************/

	N/A

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/

	Enjoyed practicing DFS.
