/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

import java.util.ArrayList;

/**
 *
 * @author sanotehu
 */
public class RedBlackTree extends Tree {

    private RedBlackNode root;

    RedBlackTree() {
        super();
    }
    
    RedBlackTree(RedBlackNode init) {
        super();
        this.root = init;
    }

    public Boolean empty() {
        return this.root == null;
    }

    public Boolean member(RedBlackNode node) {
        if (this.empty()) {
            return false;
        }
        return this.root.member(node);
    }
    
    public ArrayList<RedBlackNode> path(RedBlackNode node) {
        return this.root.path(node);
    }
}
