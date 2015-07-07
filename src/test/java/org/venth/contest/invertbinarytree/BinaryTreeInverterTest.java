package org.venth.contest.invertbinarytree;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.venth.contest.invertbinarytree.TreeInverter.inverted;

/**
 * @author Venth on 07/07/2015
 */
public class BinaryTreeInverterTest {
    @Test
    public void one_element_tree_is_inverted_to_same_element() {
        //given a binary tree containing one element
        TreeNode oneNode = new TreeNode(1);

        //when one element tree is inverted
        TreeNode invertedTree = inverted(oneNode);

        //then inverted tree contains the same element
        assertThat(toList(invertedTree), contains(oneNode.val));
    }

    @Test
    public void left_is_moved_to_right() {
        //given a binary tree containing root node with left node and right node
        TreeNode rootNode = new TreeNode(1);
        TreeNode leftNode = new TreeNode(2);
        TreeNode rightNode = new TreeNode(3);
        rootNode.setLeft(leftNode);
        rootNode.setRight(rightNode);

        //when three element tree is inverted
        TreeNode invertedTree = inverted(rootNode);

        //then left node is switched with the right one
        assertThat(toList(invertedTree), contains(rightNode.val, rootNode.val, leftNode.val));
    }

    @Test
    public void empty_right_is_moved_to_left() {
        //given a binary tree containing root node with left node and right node
        TreeNode rootNode = new TreeNode(1);
        TreeNode leftNode = new TreeNode(2);
        rootNode.setLeft(leftNode);

        //when three element tree is inverted
        TreeNode invertedTree = inverted(rootNode);

        //then left node replaced the right one leaved left one empty
        assertThat(toList(invertedTree), contains(rootNode.val, leftNode.val));
    }

    @Test
    public void empty_left_is_moved_to_right() {
        //given a binary tree containing root node with left node and right node
        TreeNode rootNode = new TreeNode(1);
        TreeNode rightNode = new TreeNode(3);
        rootNode.setRight(rightNode);

        //when three element tree is inverted
        TreeNode invertedTree = inverted(rootNode);

        //then right node replaced the left one leaved right one empty
        assertThat(toList(invertedTree), contains(rightNode.val, rootNode.val));
    }

    @Test
    public void deeper_left_node_is_switched_with_right_one() {
        //given a binary tree containing root node with left node and right node
        TreeNode rootNode = new TreeNode(1);
        TreeNode leftNode = new TreeNode(2);
        TreeNode rightNode = new TreeNode(3);
        TreeNode left_leftNode = new TreeNode(4);
        TreeNode left_rightNode = new TreeNode(5);
        leftNode.setLeft(left_leftNode);
        leftNode.setRight(left_rightNode);
        TreeNode right_leftNode = new TreeNode(6);
        TreeNode right_rightNode = new TreeNode(7);
        rightNode.setLeft(right_leftNode);
        rightNode.setRight(right_rightNode);
        rootNode.setLeft(leftNode);
        rootNode.setRight(rightNode);

        //when three element tree is inverted
        TreeNode invertedTree = inverted(rootNode);

        //then left node is switched with the right one
        assertThat(toList(invertedTree), contains(
                right_rightNode.val, rightNode.val, right_leftNode.val,
                rootNode.val,
                left_rightNode.val, leftNode.val, left_leftNode.val
        ));
    }

    private List<Integer> toList(TreeNode node) {
        return Stream.of(node)
                .flatMap(this::flattenNode)
                .map(n -> n.val)
                .collect(Collectors.<Integer>toList());
    }

    private Stream<TreeNode> flattenNode(TreeNode node) {
        Stream<TreeNode> left = node.left().map(this::flattenNode).orElse(Stream.empty());
        Stream<TreeNode> right = node.right().map(this::flattenNode).orElse(Stream.empty());
        Stream<TreeNode> flatten = Stream.concat(left, Stream.of(node));
        return Stream.concat(flatten, right);
    }

}
