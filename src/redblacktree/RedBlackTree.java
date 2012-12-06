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

    RedBlackTree(int init_key) {
        super();
        this.root = new RedBlackNode(init_key, false, this);
    }
    

    public boolean empty() {
        return this.root == null;
    }

    public boolean member(RedBlackNode node) {
        if (this.empty()) {
            return false;
        }
        return this.root.member(node);
    }

    public ArrayList<RedBlackNode> path(RedBlackNode node) {
        return this.root.path(node);
    }

    public boolean insert(RedBlackNode node, RedBlackNode pointer) {
        //Pointer should be root to begin with
        //First determine if there's room among pointer's children
        //where I could put node. If there isn't, recurse into the
        //child at the would-be insertion point j
        //--or the last child if j is MAX_CHILDREN--and if the
        //recursive call turns up false, recurse into the child
        //just before j, if that wouldn't be redundant.
        if (pointer.numChildren() == RedBlackNode.MAX_CHILDREN) {
            int j = 0;
            while (j < pointer.numChildren()) {
                if (node.lt(pointer.child(j))) {
                    j++;
                } else {
                    break;
                }
            }
            if (j == RedBlackNode.MAX_CHILDREN) {
                return this.insert(node, pointer.child(RedBlackNode.MAX_CHILDREN - 1));
            } else {
                if (this.insert(node, pointer.child(j))) {
                    return true;
                } else {
                    return this.insert(node, pointer.child(RedBlackNode.MAX_CHILDREN));
                }
            }
        } else if(pointer.adopt(node)) return true;
        else {
            for(int i=0;i<pointer.numChildren();i++) {
                if(this.insert(node, pointer)) return true;
            }
            return false;
        }
    }
    
    public int black_in_path(RedBlackNode node) {
        return root.black_in_path(node);
    }
}