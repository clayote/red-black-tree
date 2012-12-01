/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

import java.util.Stack;

/**
 *
 * @author sanotehu
 */
public class Tree {

	private Node root;

	public Tree() {
		root = null;
	} // end Tree constructor

	// recursive version
	public void dfs1() {
		if (root != null)
			dfs1(root);
	} // end dfs1 method

	// recursive version
	private void dfs1(Node node) {
		if (node != null) {
			node.visit();
			int numChildren = node.numChildren();
			for (int c=0; c<numChildren; c++)
				dfs1( node.child(c) );
		}
	} // end dfs1(Node) method

	// recursive version with level cut-off
	public void dfs1a(int limit) {
		if (root != null)
			dfs1a(root, limit, 0);
	} // end dfs1a(int) method

	// recursive version with level cut-off
	private void dfs1a(Node node, int limit, int level) {
		if (node != null) {
			node.visit();
			if (level < limit) {
			   int numChildren = node.numChildren();
			   for (int c=0; c<numChildren; c++)
				dfs1a( node.child(c), limit, level+1 );
			} // end inner if
		} // end outer if
	} // end dfs1a(Node, int, int) method

	// non-recursive version
	public void dfs2() {
		if (root != null)
			dfs2(root);
	} // end dfs2 method

	// non-recursive version
	private void dfs2(Node node) {
		Stack s = new Stack();
		Node currNode;

		s.push(node);

		while (!s.empty()) {
			currNode = (Node)s.pop();
			currNode.visit();
			int numChildren = currNode.numChildren();
			for (int c=numChildren-1; c>=0; c--)
				s.push( currNode.child(c) );
		} // end while loop
	} // end dfs2(Node) method

	// non-recursive version with level cut-off
	public void dfs3(int limit) {
		if (root != null)
			dfs2(root);
	} // end dfs3(int) method

	// non-recursive version with level cut-off
	private void dfs3(Node node, int limit) {
		Stack s = new Stack();
		Node currNode;
		int level = 0;
 		node.setLevel(level);

		s.push(node);

		while (!s.empty()) {
			currNode = (Node)s.pop();
			level = currNode.level();
			currNode.visit();
			if (level < limit) {
			   int numChildren = currNode.numChildren();
			   for (int c=numChildren-1; c>=0; c--) {
				currNode.child(c).setLevel(level+1);
				s.push( currNode.child(c) );
			   } // end for loop
			} // end inner if
		} // end while loop
	} // end dfs3(Node, int) method

	// non-recursive level search
	public void bfs() {
	   if (root != null) {
	      Queue q = new Queue();
              Node currNode = root;
              q.enqueue(currNode);

              while (!q.empty()) {
                 currNode = (Node)q.dequeue();
                 currNode.visit();
                 int numChildren = currNode.numChildren();
                 for (int c=0; c<numChildren; c++)
                    q.enqueue( currNode.child(c) );
              }
	   }
	} // end bfs method

	// non-recursive level search
	public void bfs(int limit) {
	   if (root != null) {
	      Queue q = new Queue();
              Node currNode = root;
              int level = 0;
              currNode.setLevel(level);

              q.enqueue(currNode);

              while (!q.empty()) {
                 currNode = (Node)q.dequeue();
                 currNode.visit();
                 level = currNode.level();
                 if (level < limit) {
                    int numChildren = currNode.numChildren();
                    for (int c=0; c<numChildren; c++) {
                       currNode.child(c).setLevel(level+1);
                       q.enqueue( currNode.child(c) );
                    } // end for loop
                 } // end inner if
              } // end while loop
	   } // end outer if
	} // end bfs method

} // end class Tree

