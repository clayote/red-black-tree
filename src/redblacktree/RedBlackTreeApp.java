/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;

/**
 *
 * @author sanotehu
 */
public class RedBlackTreeApp {

    public Boolean RED = true;
    public Boolean BLACK = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RedBlackFrame frame = new RedBlackFrame();
        RedBlackTree tree = new RedBlackTree();
        tree.listen_to(frame.ins);
    }
}
