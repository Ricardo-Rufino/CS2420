/******************************************************************************
 *  Name:     Ricardo Rufino
 *  NetID:    N/A
 *  Precept:  N/A
 *
 *  Partner Name:	N/A
 *  Partner NetID:      N/A
 *  Partner Precept:    N/A
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 4: Slider Puzzle


/******************************************************************************
 *  Explain briefly how you implemented the Board data type.
 *****************************************************************************/


	The board class needs a 2d array as an input. It assumes 0 as the empty
	element.


/******************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 *****************************************************************************/



	The node implemented in my program has 3 different fields, which are:
		-Board current = board that node contains.
		-int moves     = number of moves to reach certain node.
		=Node previous = previous node.


/******************************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 *
 *  What is the order of growth of the running time of your isSolvable()
 *  method as function of the board size n? Recall that with order-of-growth
 *  notation, you should discard leading coefficients and lower-order terms,
 *  e.g., n log n or n^3.

 *****************************************************************************/

Description:
	The function first converts the 2d array into a 1d array, where it also
	locates the location of the 0th element. It then calculates for the
	number of inversion in the 1d array.

	With this information, it then does the necessary comparisons to
	determine if the board is solvable.

Order of growth of running time:
	O(n^2)


/******************************************************************************
 *  For each of the following instances, give the minimum number of moves to
 *  solve the instance (as reported by your program). Also, give the amount
 *  of time your program takes with both the Hamming and Manhattan priority
 *  functions. If your program can't solve the instance in a reasonable
 *  amount of time (say, 5 minutes) or memory, indicate that instead. Note
 *  that your program may be able to solve puzzle[xx].txt even if it can't
 *  solve puzzle[yy].txt even if xx > yy.
 *****************************************************************************/


                 min number          seconds
     instance     of moves     Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt      28		   2	       0
   puzzle30.txt      30		   5	       0
   puzzle32.txt      32	 	 memory	       1
   puzzle34.txt      34		 memory	       0
   puzzle36.txt      36		 memory	       9
   puzzle38.txt      38		 memory	       3
   puzzle40.txt      40		 memory        3
   puzzle42.txt      42		 memory	       19



/******************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer: a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 *****************************************************************************/


	A better priority queue, becuase an improvement in the queue does not
	have any cost related to raw materials (faster computer components).


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

	Too slow with Hamming priority queue. Other than that I'm not aware
	of other bugs.

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

	Properly implementing the A* algorithm.

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/


	Worked by myself.


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/

    I really enjoyed this assignment; it really is intellectually stimulating.