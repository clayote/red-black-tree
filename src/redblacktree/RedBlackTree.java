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

    public int black_per_path() {

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

    public Boolean insert(RedBlackNode node, RedBlackNode pointer) {
	//Pointer should be root to begin with
	//First determine if there's room among pointer's children
	//where I could put node. If there isn't, recurse into the
	//child at the would-be insertion point j
	//--or the last child if j is MAX_CHILDREN--and if the
	//recursive call turns up false, recurse into the child
	//just before j, if that wouldn't be redundant.
	if(pointer.numChildren() == MAX_CHILDREN) {
	    int j = 0;
	    while(j < pointer.numChildren()) {
		if(node.lt(pointer.child(j))) j++;
		else break;
	    }
	    if(j == MAX_CHILDREN) {
		return this.insert(node, pointer.child(MAX_CHILDREN - 1));
	    } else {
		if(this.insert(node, pointer.child(j))) return true;
		else return this.insert(node, pointer.child(jMAX_CHILDREN));
	    }
	} else {
	    //Since there IS room, check colors.
	    if(node.red && !pointer.red) {
		//legit
		pointer.adopt(node);
		return true;
	    } else if(!node.red && pointer.red) {
		//*potentially* legit, but only if:
		//
		//(a) there's at least one other black child already,
		//which I shall presume was inserted there correctly, or
		//
		//(b) the path resulting from insertion is the correct length
		//
		//Since all children of a red node must be black, I
		//don't actually need to check the color--only see if
		//there are any.
		if(pointer.numChildren() > 0) {
		    pointer.adopt(node);
		    return true;
		} else if(this.black_per_path() - 1 = 
		    
		    
    }
}
