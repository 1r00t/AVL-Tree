/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beuth.algorithmen.datatypes.bintree;

/**
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
        AVLTreeNode father = (AVLTreeNode) parent;
        AbstractTreeNode newRoot = super.remove();
        if (father != null) {
            father.propagateDepthChange();
        }
        return newRoot;
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
                ((AVLTreeNode) parent).propagateDepthChange();
        }
    }

    public int getAVLValue() {
        return left.getDepth() - right.getDepth();
    }

    public void rotateLeft() {
        AVLTreeNode father = (AVLTreeNode) this.parent;
        if (father == null || isLeftChild()) {
            throw new RuntimeException();
        }
        father.right = this.left;
        if (this.left != null) {
            this.left.parent = father;
        }
        this.parent = father.parent;
        father.parent = this;
        this.left = father;
        father.propagateDepthChange();
    }

    private void rotateRight() {
        AVLTreeNode father = (AVLTreeNode) this.parent;
        if (father == null || isRightChild()) {
            throw new RuntimeException();
        }
        father.left = this.right;
        if (this.right != null) {
            this.right.parent = father;
        }
        this.parent = father.parent;
        father.parent = this;
        this.right = father;
        father.propagateDepthChange();
    }

    private void doubleRotateLeft() {
        rotateRight();
        rotateLeft();
    }

    private void doubleRotateRight() {
        rotateLeft();
        rotateRight();
    }

    public AbstractTreeNode insert(AbstractTreeNode btn) {
        AbstractTreeNode root = super.insert(btn);

        if((this.left != btn && this.right != btn) || btn.parent.parent == null) {
            return root;
        }

        while(Math.abs(((AVLTreeNode) btn.parent.parent).getAVLValue()) != 2){
            btn = btn.parent;
            if(btn.parent.parent == null) return root;
        }

        if(btn.isLeftChild()){
            if(btn.parent.isLeftChild()) {
                ((AVLTreeNode) btn.parent).rotateRight();
            } else {
                ((AVLTreeNode) btn).doubleRotateLeft();
            }
        } else {
            if(btn.parent.isLeftChild()) {
                ((AVLTreeNode) btn).doubleRotateRight();
            } else {
                ((AVLTreeNode) btn.parent).rotateLeft();
            }
        }

        return getRoot();
    }

    public static void main(String[] args) {


        AVLTreeNode a = new AVLTreeNode(new IntKeyable(6));
        AVLTreeNode b = new AVLTreeNode(new IntKeyable(8));
        AVLTreeNode c = new AVLTreeNode(new IntKeyable(10));
        AVLTreeNode d = new AVLTreeNode(new IntKeyable(5));
        AVLTreeNode e = new AVLTreeNode(new IntKeyable(7));
        AVLTreeNode f = new AVLTreeNode(new IntKeyable(9));
        AVLTreeNode g = new AVLTreeNode(new IntKeyable(15));
        c.insert(g);
        c.insert(a);
        a.insert(d);
        a.insert(b);
        b.insert(e);
        b.insert(f);

        System.out.println(a.getRoot().toString());
    }
}

