package org.venth.contest.invertbinarytree;

import java.lang.ref.WeakReference;
import java.util.Optional;

/**
 * @author Venth on 07/07/2015
 */
public class TreeInverter extends TreeNode {
    private final WeakReference<TreeNode> node;

    public TreeInverter(TreeNode node) {
        super(node.val);
        this.node = new WeakReference<>(node);
    }

    public static TreeNode inverted(TreeNode node) {
        return new TreeInverter(node).apply();
    }

    public TreeNode apply() {
        Optional<TreeNode> right = node.get().right();
        if (right.isPresent()) {
            this.setLeft(new TreeInverter(right.get()).apply());
        }
        Optional<TreeNode> left = node.get().left();
        if (left.isPresent()) {
            this.setRight(new TreeInverter(left.get()).apply());
        }

        return this;
    }

}
