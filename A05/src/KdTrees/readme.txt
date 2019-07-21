/******************************************************************************
 *  Name:     Ricardo Rufino
 *  NetID:    N/A
 *  Precept:  N/A
 *
 *  Partner Name:      None    
 *  Partner NetID:     N/A 
 *  Partner Precept:   N/A 
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 5: Kd-Trees


/******************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 *****************************************************************************/

	I used a binary tree as my data structure. 

/******************************************************************************
 *  Describe your method for range search in a kd-tree.
 *****************************************************************************/
	
	The method implements a recursive call. It inspects if the node's
	rectangle intersects the given rectangle; if it doesn't, it does
	not it stops pursuing that node. It then recursively calls the left
	node followed by the right.

/******************************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 *****************************************************************************/

	It looks if the distance between the node's point and the query point.
	If the distance is greater than the current closest point, it stops
	pursuing that node. If the distance is smaller; the node's point 
	becomes the nearest point. It then recursively calls the left node 
	followed by the right node; only if the node's rectangle contain the
	query point.

/******************************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Show the math how you used to determine
 *  the operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 *****************************************************************************/

                       calls to nearest() per second
                     brute force               2d-tree
                     ---------------------------------
input100K.txt	     	0.241			250.0

input1M.txt	     	0.008			333.3

This was calculated by measuring the difference in time (nano seconds) between
The first and last point inputed into the nearest() function. Then, the number
Of points were divided by time (after converting to seconds).

/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

	None that I know; so far.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
	
	Stackoverflow

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

	Understanding recursive methods.

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/

	Did not work with a partner.

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on  how helpful the class meeting was and on how much you learned 
 * from doing the assignment, and whether you enjoyed doing it.
 *****************************************************************************/

	This assignment helped me to better understand how to implement
	recursive methods. 

