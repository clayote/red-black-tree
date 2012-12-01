/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

/**
 *
 * @author sanotehu
 */
public class RedBlackNode extends Node {
    private Boolean color;
    private int numChildren, level;
    private RedBlackNode[] children;

    RedBlackNode(Boolean color) {
        this.color = color;
        this.children = new RedBlackNode[MAX_CHILDREN];
        this.numChildren = 0;
    }

    @Override
    public RedBlackNode child(int index) {
        if (index >= 0 && index < numChildren) {
            return children[index];
        } else {
            return null;
        }
    }

    public Boolean member(RedBlackNode node) {
        if (this == node) {
            return true;
        }
        for (int i = 0; i < this.numChildren; i++) {
            if (this.child(i).member(node)) {
                return true;
            }
        }
        return false;
    }

    public void adopt(RedBlackNode node) {
        if (this.numChildren >= MAX_CHILDREN) {
            return;
        }
        this.children[this.numChildren] = node;
        this.numChildren++;
    }

    public void disown(int i) {
        this.children[i] = null;
        RedBlackNode temp = null;
        for (i = 0; i < this.numChildren; i++) {
            temp = this.child(i);
            // I think I might have trouble when i gets to the end of the list 
            // and I try to pull i+1.
            this.children[i] = this.children[i + 1];
            this.children[i + 1] = temp;
        }
    }
}
