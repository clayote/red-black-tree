/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

/**
 *
 * @author sanotehu
 */

public class Node {

	private int key, numChildren, level;
	private Node[] children;

	public static final int MAX_CHILDREN=20;

	public Node(int key) {
		this.key = key;
		children = new Node[MAX_CHILDREN];
		numChildren = 0;
	} // end Node constructor
        
        public Node() {
            
        }

	public int numChildren() {
		return numChildren;
	} // end numChildren getter

	public Node child(int index) {
		if (index >= 0 && index<numChildren)
			return children[index];
		else
			return null;
	} // end child method

	public void visit() {
		// whatever visiting a node entails
	} // end visit method

	public int level() {
		return level;
	} // end level getter

	public void setLevel(int l) {
		level = l;
	} // end setLevel method
} // end class node

