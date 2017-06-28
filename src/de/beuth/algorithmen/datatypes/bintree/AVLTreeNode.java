/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beuth.algorithmen.datatypes.bintree;

/**
 *
 * @author Nicolai Brandt
 */
public class AVLTreeNode extends TreeNode {

    private int depth;

    protected AVLTreeNode(KeyAble data) {
        super(data);
        depth = 1;
    }

    public int getDepth() {
        return depth;
    }

    public AbstractTreeNode remove() {
        AVLTreeNode father = (AVLTreeNode)parent;
        AbstractTreeNode newRoot = super.remove();
        if (father != null) {
            father.propagateDepthChange();
        }
        return  newRoot;
    }

    public void setRightChild(AbstractTreeNode c) {
        super.setRightChild(c);
        propagateDepthChange();
    }

    public void setLeftChild(AbstractTreeNode c) {
        super.setLeftChild(c);
        propagateDepthChange();
    }

    private void propagateDepthChange() {
        int lDepth = 0;
        if (left != null) lDepth = left.getDepth();
        int rDepth = 0;
        if (right != null) rDepth = right.getDepth();
        int newDepth = 1 + Math.max(lDepth, rDepth);
        if (depth != newDepth) {
            depth = newDepth;
            if (parent != null)
                ((AVLTreeNode)parent).propagateDepthChange();
        }
    }

    public int getAVLValue() {
        return left.getDepth() - right.getDepth();
    }

    public void rotateLeft() {
        AVLTreeNode a = (AVLTreeNode) this.parent;
        if (a == null) {
            throw new RuntimeException();
        }
        a.right = this.left;
        if (this.left != null) {
            this.left.parent = a;
        }
        this.parent = a.parent;
        a.parent = this;
        this.left = a;        
    }
    
    private void rotateRight() {
        AVLTreeNode a = (AVLTreeNode) this.parent;
        if (a == null) {
            throw new RuntimeException();
        }
        a.left = this.right;
        if (this.right != null) {
            this.right.parent = a;
        }
        this.parent = a.parent;
        a.parent = this;
        this.right = a;
    }
    
    public static void main(String[] args) {
//        IntKeyable a = new IntKeyable(1);
//        IntKeyable b = new IntKeyable(2);
//        IntKeyable c = new IntKeyable(3);
//        AVLTreeNode aa = new AVLTreeNode(a);
//        AVLTreeNode bb = new AVLTreeNode(b);
//        AVLTreeNode cc = new AVLTreeNode(c);
//        aa.insert(bb);
//        aa.insert(cc);
//        
//        System.out.println(aa.toString());
//        bb.rotateLeft();
//        System.out.println(bb.toString());
        
        IntKeyable a = new IntKeyable(3);
        IntKeyable b = new IntKeyable(2);
        IntKeyable c = new IntKeyable(1);
        AVLTreeNode aa = new AVLTreeNode(a);
        AVLTreeNode bb = new AVLTreeNode(b);
        AVLTreeNode cc = new AVLTreeNode(c);
        aa.insert(bb);
        aa.insert(cc);
        
        System.out.println(aa.toString());
        bb.rotateRight();
        System.out.println(bb.toString());
    }
}

