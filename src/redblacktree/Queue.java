/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

/**
 *
 * @author sanotehu
 */

public class Queue extends java.util.ArrayList {

	// rear of queue == end of Vector

	public void enqueue(Object element) {
		if (element != null)
			add(element);
	} // end enqueue method

	public boolean empty() {
		return isEmpty();
	} // end empty predicate

	public Object dequeue() {
		if (!empty())
			return remove(0);
		else
			return null;
	} // end dequeue method

	public Object front() {
		if (!empty())
			return get(0);
		else
			return null;
	} // end front method

} // end class Queue

