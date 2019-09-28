/**
 * Ruifeng Wang
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package rwang_p3EC;

/**
 * @author Ruifeng Wang
 * @version 1.0
 * Creates a queue that stores generic values
 */
public class GenericQueue<T> { 
	
	/**
	*	This class implements a queue based on linked lists.
	*/
    private class Node {
        T value; // Value of the node
        Node next; // Pointer to the next node
        Node prev; // Pointer to the previous node
        
        /**
         * Constructer
         * @param val	Value of the node
         * @param n		Next node
         * @param p		Previous node
         */
        Node(T val, Node n, Node p) {
            this.value = val; 
            this.next = n;
            this.prev = p;
        }      
        
        /**
         * Constructer
         * @param val	Value of the node
         */
        Node(T val) {
        	this(val, null, null);
        }
    }

    private Node front = null; // Pointer to the front of the queue
    private Node rear = null;  // Pointer to the rear of the queue
    
    /**
     * Constructor
     */
    public GenericQueue() {
    	front = null;
    	rear = null;
    }
    
    /**
	* The method enqueue adds a value to the queue.
    * @param s The value to be added to the queue.
    */
    public void enqueue(T s) {
        if (rear != null) {
           rear.next = new Node(s, null, rear);
           rear = rear.next;
        }
        else {
            rear = new Node(s, null, null);
            front = rear;
        }
    }
    
    /**
     * The dequeue method removes and returns the item at the 
     * front of the queue.
     * @return item at front of queue.
    */
    public T dequeue() {
       if (empty()) {
    	   throw new IllegalArgumentException("The queue is empty");
       } else {
           T value = front.value;
           front = front.next;
//         front.prev = null;
           if (front == null) {
        	   rear = null;    
           }
           return value;
       }
    }
    
    /**
    * The empty method checks to see if the queue is empty.
    * @return true if and only if queue is empty.
    */
    public boolean empty()
    {
        return front == null;
    }
    
    /**
    * The method peek returns value at the front of the queue.
    * @return item at front of queue.
    */
    public T peek() {
        if (empty()) {
            throw new IllegalArgumentException("The queue is empty");
        } else {
            return front.value;
        }
    }
    
    /**
    * The toString method concatenates all strings in the queue to give a 
    * string representation of the contents of the queue.        
    * @return String representation of this queue.
    */
    public String toString() {
       StringBuilder sBuilder = new StringBuilder();
       
       // Walk down the list and append all values
       Node p = front;
       sBuilder.append("|");
       while (p != null) {
           sBuilder.append(p.value + "|");
           p = p.next;
       }
       return sBuilder.toString();        
    }
    
    /**
     * Creates a copy of the queue
     * @return	New copy of queue
     */
    public GenericQueue<T> copy() {
    	GenericQueue<T> copied = new GenericQueue<T>();
    	Node temp = front;
    	while(temp != null) {
    		copied.enqueue(temp.value);
    		temp = temp.next;
    	}
    	return copied;
    }
    
    /**
     * Appends a copy of the rule's right side onto the end of the output queue
     * @param in	Queue rule object
     */
    public void append(GenericQueue<?> in) {
    	GenericQueue<T> tempCopy = (GenericQueue<T>) in.copy();
    	Node temp = tempCopy.front;
    	while(temp != null) {
    		enqueue(temp.value);
    		temp = temp.next;
    	}
    }
    
    /**
     * Checks if two queues are equal both in size and content
     * @param in	Queue to be compared to
     * @return		true if equal, false otherwise
     */
    public boolean equals(GenericQueue<?> in) {
    	GenericQueue<T> tempCopy = (GenericQueue<T>) in.copy();
    	Node temp = tempCopy.front;
    	Node temp2 = front;
    	if(size() != in.size()) {
    		return false;
    	}
    	
    	while(temp != null) {
    		if(!temp.value.equals(temp2.value)) {
    			return false;
    		} else {
    			temp = temp.next;
    			temp2 = temp2.next;
    		}
    	}
    	return true;
    }
    
    /**
     * Traverses and finds the size of the queue
     * @return	Integer size of the queue
     */
    private int size() {
    	Node temp = front;
    	int counter = 0;
    	while(temp != null) {
    		counter++;
    		temp = temp.next;
    	}
    	return counter;
    }
    
}
