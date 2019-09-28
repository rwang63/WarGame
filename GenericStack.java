/**
 * Ruifeng Wang
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package rwang_p3EC;

/**
 * @author Ruifeng Wang
 * @version 1.0
 * Creates a stack that stores generic values
 */
public class GenericStack<T> {
	
	private T[] s; // Array to store stack
	private int top; // Keeps track of top of stack
	private int stackSize; // Total stack size
	
	/**
	 * Constructor
	 * @param capacity Maximum number of items to be held in stack
	 */
	public GenericStack(int capacity) {
		s = (T[]) new Object[capacity];
		top = 0;
		stackSize = capacity;
	}
	
	/**
	 * Checks if stack is empty
	 * @return	true if stack is empty, false otherwise
	 */
	public boolean empty() {
		return top == 0;
	}
	
	/**
	 * Adds (pushes) parameter onto stack, throws IllegalArgumentException if
	 * the stack is full
	 * @param x double to push onto stack
	 */
	public void push(T x) {
		if (top == s.length) {
			throw new IllegalArgumentException("Stack is full");
		} else {
			s[top] = x;
			top++;
		}
	}
	
	/**
	 * Pops the double off the top of the stack. Throws IllegalArgumentException
	 * if stack is empty
	 * @return double at the top of the stack
	 */
	public T pop() {
		if(empty()) {
			throw new IllegalArgumentException("Stack is empty");
		} else {
			top--;
			return s[top];
		}
	}
	
	/**
	 * Shows the double at the top of the stack. Throws IllegalArgumentException
	 * if stack is empty 
	 * @return double at the top of the stack
	 */
	public T peek() {
		if(empty()) {
			throw new IllegalArgumentException("Stack is empty");
		} else {
			return s[top-1];
		}
	}
	
	/**
	 * Makes a deep copy of the stack
	 * @return	The copied stack
	 */
	public GenericStack<T> copy() {
		GenericStack<T> copiedStack = new GenericStack<T>(stackSize);
		for(int i = 0; i < top; i++) {
			copiedStack.push(s[i]);
		}
		return copiedStack;
	}
	
	/**
	 * Generates string representation of the stack
	 * @return	String representation of the stack
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		for(int i = 0; i < top; i++) {
			sBuilder.append(s[i] + " ");
		}
		return sBuilder.toString();
	}
	
	/**
	 * Checks if two stacks are equal in both size and content
	 * @param firstStack	Stack to be compared to
	 * @return				True if equal, false otherwise
	 */
	public boolean equals(GenericStack<?> firstStack) {
		if(top != firstStack.top) {
			return false;
		}
		
		for(int i = 0; i < top; i++) {
			if(!(s[i].equals(firstStack.s[i]))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Current size of the stack
	 * @return		number of items in the stack
	 */
	public int stackSize() {
		return top; 
	}
	
}
