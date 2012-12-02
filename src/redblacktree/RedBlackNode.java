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

    private Boolean red;
    private int numChildren;
    private RedBlackNode[] children;

    RedBlackNode(int key, Boolean red) {
        super(key);
        this.red = red;
        this.children = new RedBlackNode[MAX_CHILDREN];
        this.numChildren = 0;
        this.setLevel(-1);
    }

    public int black_childs() {
        int bc = 0;
        for (int i = 0; i < this.numChildren; i++) {
            if (!this.children[i].red) {
                bc++;
            }
        }
        return bc;
    }

    public int black_descendants() {
        int bd = 0;
        for (int i = 0; i < this.numChildren; i++) {
            if (!this.children[i].red) {
                bd++;
            }
            bd += this.children[i].black_descendants();
        }
        return bd;
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
    
    public int cmp(RedBlackNode that) {
        if(this.key() > that.key()) return 1;
        if(this.key() == that.key()) return 0;
        return -1;
    }
    
    public Boolean gt(RedBlackNode that) {
        return this.cmp(that) == 1;
    }
    
    public Boolean lt(RedBlackNode that) {
        return this.cmp(that) == -1;
    }
    
    public Boolean eq(RedBlackNode that) {
        return this.cmp(that) == 0;
    }
    
    public Boolean ge(RedBlackNode that) {
        if(that == null) return true;
        else return this.cmp(that) >= 1;
    }
    
    public Boolean le(RedBlackNode that) {
        return this.cmp(that) <= 1;
    }

    public Queue path(RedBlackNode node, Queue path) {
        // This is the core pathing function.
        // I am the child of the last node in path.
        // I know I'm not the node we're looking for,
        // because I was already checked in the previous iteration.
        // But one of my children might be...
        for (int i = 0; i < this.numChildren - 1; i++) {
            if (this.child(i) == node) {
                path.add(this.child(i));
                return path;
            }
        }
        // None of my immediate children are the one,
        // so how about their children?
        for (int i = 0; i < this.numChildren - 1; i++) {
            if (this.child(i).member(node)) {
                //This child is an ancestor of the one we want
                path.add(this.child(i));
                return this.child(i).path(node, path);
            }
        }
        // It shouldn't be possible to get this far, because that means 
        // the node we want isn't actually a member.
        return null;

        // This function seems inefficient.
        // Revisit later.
    }

    public Queue path(RedBlackNode node) {
        //A wrapper for the above.
        Queue path = new Queue();
        path.add(this);
        if (this == node) {
            return path;
        }
        return this.path(node, path);
    }

    public void adopt(RedBlackNode node) {
        if (this.numChildren >= MAX_CHILDREN) {
            return;
        }
        if ((this.red && !node.red) || !this.red) {
            // node's legit. make it mine.
            // It's on the level below me, if I have a level at all.
            if (this.level() > -1) {
                node.setLevel(this.level() + 1);
            }
            // I must keep my children in order if the balancing algorithms are going to work.
            if(node.ge(this.children[0])) { // The case where I have no other children is accounted for in RedBlackNode.ge
            this.children[this.numChildren] = node;
            } else {
                RedBlackNode temp1 = this.children[0];
                RedBlackNode temp2 = null;
                this.children[0] = node;
                for(int i=1;i<this.numChildren;i++) {
                    temp2 = this.children[i];
                    this.children[i] = temp1;
                    temp1 = temp2;
                }
            }
            this.numChildren++;
        } else {
            return; //should probably throw an exception, not sure which though
        }

    }

    public void disown(int i) {
        this.children[i] = null;
        RedBlackNode temp = null;
        if(i >= this.numChildren - 1) {
            this.numChildren--;
            return;
        }
        i++;
        while(i<this.numChildren) {
            temp = this.children[i];
            this.children[i-1] = this.children[i];
            this.children[i] = temp;
        }
        this.numChildren--;
    }
}
