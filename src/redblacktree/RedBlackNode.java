/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

import redblacktree.RedBlackSentinel;

/**
 *
 * @author sanotehu
 */
public class RedBlackNode extends Node {

    public boolean red;
    private int numChildren, key;
    private RedBlackNode[] children;
    private RedBlackTree tree;

    RedBlackNode(int key, boolean color, RedBlackTree tree) {
        super(key);
        this.red = color;
        this.children = new RedBlackNode[MAX_CHILDREN];
        this.numChildren = 0;
        for (int i = 0; i < MAX_CHILDREN; i++) {
            this.children[i] = new RedBlackSentinel(0, false, tree);
        }
        this.key = key;
        this.tree = tree;
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

    public int black_in_path(RedBlackNode node) {
        RedBlackQueue path = this.path(node);
        RedBlackNode check = path.dequeue();
        int bct = 0;
        while (check != null) {
            if (!check.red) {
                bct++;
            }
            check = path.dequeue();
        }
        return bct;
    }

    @Override
    public RedBlackNode child(int index) {
        if (index >= 0 && index < numChildren) {
            return children[index];
        } else {
            return null;
        }
    }

    public boolean member(RedBlackNode node) {
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
        if (this.key > that.key) {
            return 1;
        }
        if (this.key == that.key) {
            return 0;
        }
        return -1;
    }

    public boolean gt(RedBlackNode that) {
        return this.cmp(that) == 1;
    }

    public boolean lt(RedBlackNode that) {
        return this.cmp(that) == -1;
    }

    public boolean eq(RedBlackNode that) {
        return this.cmp(that) == 0;
    }

    public boolean ge(RedBlackNode that) {
        if (that == null) {
            return true;
        } else {
            return this.cmp(that) >= 1;
        }
    }

    public boolean le(RedBlackNode that) {
        return this.cmp(that) <= 1;
    }

    public RedBlackQueue path(RedBlackNode node, RedBlackQueue path) {
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

    public RedBlackQueue path(RedBlackNode node) {
        //A wrapper for the above.
        RedBlackQueue path = new RedBlackQueue();
        path.add(this);
        if (this == node) {
            return path;
        }
        return this.path(node, path);
    }

    public boolean adopt(RedBlackNode that) {
        //Attempt to adopt the given node while obeying the invariants of red-black trees.
        //Return true on success, false on failure.
        if (this.full()) {
            return false;
        }
        else if (this.red && that.red) {
            return false;
        }
        else if (!that.red) {
            //Try adopting it. Then check if I'm still in compliance with invariant 4, "Every simple path from a node to a descendant leaf contains the same number of black nodes."
            //If not, disown the new guy.
            this.append(that);
            if(this.obedient()) {
                return true;
            }
            else {
                this.disown(that);
                return false;
            }
        } else {
            //It's red, I'm not, everything's great.
            this.append(that);
            return true;
        }
    }

    public void disown(int i) {
        this.children[i] = new RedBlackSentinel(0, false, this.tree);
        RedBlackNode temp = null;
        while (i < this.numChildren) {
            temp = this.children[i];
            this.children[i] = this.children[i];
            this.children[i + 1] = temp;
            i++;
        }
        this.numChildren--;
    }
    
    public void disown(RedBlackNode child) {
        for(int i=0;i<this.numChildren;i++) {
            if(this.child(i) == child) {
                this.disown(i);
            }
        }
    }

    public int children() {
        return this.numChildren;
    }

    public boolean full() {
        return this.numChildren < RedBlackNode.MAX_CHILDREN;
    }

    private void append(RedBlackNode node) {
        //Append the given node to children without checking anything.
        this.children[this.numChildren] = node;
        this.numChildren++;
    }
    
    private RedBlackQueue fringe(RedBlackQueue fringe) {
        for(int i=0;i<this.numChildren;i++) {
            if(this.child(i).getClass() == RedBlackSentinel.class) {
                fringe.enqueue(this.child(i));
            } else {
                RedBlackQueue lilfringe = this.child(i).fringe(fringe);
                fringe.addAll(lilfringe);
            }
        }
        return fringe;
    }
    
    public RedBlackQueue fringe() {
        return this.fringe(new RedBlackQueue());
    }
    
    private boolean obedient() {
        //Check for compliance with invariant 4.
        RedBlackQueue fringe = this.fringe();
        RedBlackNode leaf = fringe.dequeue();
        int numblack = this.black_in_path(leaf);
        leaf = fringe.dequeue();
        while(leaf != null) {
            if(this.black_in_path(leaf) != numblack) return false;
            leaf = fringe.dequeue();
        }
        return true;
    }
}
