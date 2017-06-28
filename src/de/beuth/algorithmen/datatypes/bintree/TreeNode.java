package de.beuth.algorithmen.datatypes.bintree;

public class TreeNode extends AbstractTreeNode {

    protected TreeNode(KeyAble data) {
        super(data);
    }

    @Override
    public TreeNode getRoot() {
        // TODO Auto-generated method stub
        TreeNode tn = this;
        while (tn.getParent() != null) {
            tn = (TreeNode) tn.getParent();
        }
        return tn;
    }

    @Override
    public int getDepth() {
        // TODO Auto-generated method stub
        if (left == null) {
            if (right == null) {
                return 0;
            } else {
                return right.getDepth() + 1;
            }
        } else if (right == null) {
            return left.getDepth() + 1;
        } else {
            return Math.max(left.getDepth(), right.getDepth()) + 1;
        }
    }

    @Override
    public int getRootDistance() {
        // TODO Auto-generated method stub
        int d = 0;
        TreeNode tn = this;
        while (tn.getParent() != null) {
            tn = (TreeNode) tn.getParent();
            d++;
        }
        return d;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (this == null) return "null";

        TreeNode lc = (TreeNode) this.getLeftChild();
        TreeNode rc = (TreeNode) this.getRightChild();

        String l;
        String r;

        if (lc == null) {
            l = "null";
        } else {
            l = lc.toString();
        }
        if (rc == null) {
            r = "null";
        } else {
            r = rc.toString();
        }

        return "(" + this.getData().getKey() + ": " + l + ", " + r + ")";
    }

    @Override
    public TreeNode getMinimumNodeInSubTree() {
        // TODO Auto-generated method stub
        TreeNode tn = this;
        while (tn.getLeftChild() != null) {
            tn = (TreeNode) tn.getLeftChild();
        }
        return tn;
    }

    @Override
    public TreeNode getMaximumNodeInSubTree() {
        // TODO Auto-generated method stub
        TreeNode tn = this;
        while (tn.getRightChild() != null) {
            tn = (TreeNode) tn.getRightChild();
        }
        return tn;
    }

    @Override
    public TreeNode getSuccessor() {
        // TODO Auto-generated method stub
        if (this == null) return null;

        if (this.getRightChild() != null) {
            return (TreeNode) this.getRightChild().getMinimumNodeInSubTree();
        }

        TreeNode y = (TreeNode) this.getParent();
        TreeNode x = this;

        while (y != null && x == (TreeNode) y.getRightChild()) {
            x = y;
            y = (TreeNode) y.getParent();
        }
        return y;
    }

    @Override
    public TreeNode getPredecessor() {
        // TODO Auto-generated method stub
        if (this == null) return null;

        if (this.getLeftChild() != null) {
            return (TreeNode) this.getLeftChild().getMaximumNodeInSubTree();
        }

        TreeNode y = (TreeNode) this.getParent();
        TreeNode x = this;

        while (y != null && x == (TreeNode) y.getLeftChild()) {
            x = y;
            y = (TreeNode) y.getParent();
        }
        return y;
    }

    @Override
    public int getSubtreeSize() {
        // TODO Auto-generated method stub
        if (this == null) return 0;
        TreeNode lc = (TreeNode) this.getLeftChild();
        TreeNode rc = (TreeNode) this.getRightChild();
        int l = 0;
        int r = 0;
        if (lc != null) l = lc.getSubtreeSize();
        if (rc != null) r = rc.getSubtreeSize();
        return l + r + 1;
    }


}
