/******************************************************************************
 *  Name:    Alec Mills
 *  Partner: None
 *
 * Hours to complete assignment (optional):
 *     Not sure, maybe 6 for the bulk of the assignment
 *     plus random refactoring here and there, plus maybe an hour on this
 *     document itself
 ******************************************************************************/

Programming Assignment 2: Deques and Randomized Queues


/******************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 *****************************************************************************/
 I chose to use a doubly-linked list for the deque. Because adding and removing
 are constrained to the head and tail of the queue, a doubly-linked list can
 provide
 constant worst-case time for both operations at both ends of queue.

 I chose to use a resizing array implementation for the randomized queue.
 Because we need 1) random access and 2) the ability to reorder our data, a
 resizing array provides both operations in constant amortized time. Our array
 doubles in size when full, and halves in size when one-quarter full. Thus,
 over a sequence of N operations, [1..N - 1] will be constant, and the Nth
 operation will take linear time, for an amortized cost of constant time.

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

Randomized Queue:   ~  8N + 56 bytes:
    16:       Object overhead
    8N + 32:  Item array
              8N for references to items
              8 for reference to array itself
              24 for array overhead
    4:        int field
    4:        padding

Deque:              ~ 40N +  40 bytes
    16:       Object overhead
    16:       two object references (first and last)
    4:        int field
    4:        padding
    40N:      each Node has:
        16:   Object overhead
        8:    reference to item
        8:    reference to next (can be null if last node)
        8:    reference to prev (can be null if first node)




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
 Neither structure accepts null items

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
 I spoke to Margaret Posch about implementation details and memory usage and
 read the instructions and checklist thoroughly


/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/
 I did not work with a partner


/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/
 I needed clarification on the instructions, and I struggled to calculate memory
  usage accurately. I need more practice/reading on the topic of memory


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
 N/A
