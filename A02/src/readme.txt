/******************************************************************************
 *  Name:    Ricardo Rufino
 *  NetID:   
 *  Precept: 
 *
 *  Partner Name:     N/A
 *  Partner NetID:    N/A
 *  Partner Precept:  N/A
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 2: Deques and Randomized Queues


/******************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 *****************************************************************************/

	For the randomized queue class, I used an array to organize the data.
	I chose this data structure because accessing a random element in array is much 
	efficient than using a linked-list.

	For the deque class, I used a doubly linked list to organize the data.
	I chose this data strucutre because of the need to access the first and last element.
	The head and last node allows for constant access to the first and last elements.  	
	
/******************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 *****************************************************************************/

Randomized Queue:   ~  42N + 24 bytes

Deque:              ~  24N + 42 bytes


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

	If the args is greater than the array size, for the randomized queue, the system will not work.
	To address it, the system outputs a no such element exception.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/

	My friend Sonu, helped me debug some of the bugs in my code.
	Also, writting down the logic of my program helped me understand the dat structure in more dtail.

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/

	Did not work with a partner.

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

	Undertanding how to use the command prompt to compile and run java programs. 

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/
