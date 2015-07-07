package org.venth.contest.invertbinarytree;

import java.util.Optional;

/**
 * @author Venth on 07/07/2015
 */
public class TreeNode {
    public final int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public Optional<TreeNode> left() {
        return Optional.ofNullable(left);
    }

    public Optional<TreeNode> right() {
        return Optional.ofNullable(right);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode inverted() {
        TreeNode newNode = new TreeNode(val);
        newNode.setLeft(right);
        newNode.setRight(left);
        return newNode;
    }
}
