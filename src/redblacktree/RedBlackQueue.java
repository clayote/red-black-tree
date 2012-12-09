/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

/**
 *
 * @author sanotehu
 */
public class RedBlackQueue extends Queue {

    public void enqueue(RedBlackNode element) {
        if (element != null) {
            add(element);
        }
    } // end enqueue method

    @Override
    public RedBlackNode dequeue() {
        if (!empty()) {
            return (RedBlackNode) remove(0);
        } else {
            return null;
        }
    } // end dequeue method

    @Override
    public RedBlackNode front() {
        if (!empty()) {
            return (RedBlackNode) get(0);
        } else {
            return null;
        }
    } // end front method

    @Override
    public RedBlackNode[] toArray() {
        return (RedBlackNode[]) super.toArray();
    }
}
