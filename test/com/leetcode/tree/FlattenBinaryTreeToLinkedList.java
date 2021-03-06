package com.leetcode.tree;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class FlattenBinaryTreeToLinkedList {
    private TreeNode prev = null;
    
    public void flatten0(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public void flatten(TreeNode root) {
        flat(root);
    }
    
    private TreeNode flat(TreeNode root) {
        if(root == null) return null;
        TreeNode node = root;
        TreeNode right = flat(node.right);
        node.right = flat(node.left);
        node.left = null;
        while(node.right!=null) node = node.right;
        node.right = right;
        
        return root;
    }
    
    @Test
    public void test0() {
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 5, 3, 4, null, 6});
        TreeNode expected = TreeNode.build(new Integer[]{1, null, 2, null, 3, null, 4, null, 5, null, 6});
        flatten(root);
        assertTrue(TreeNode.isSameTree(expected, root));
    }
}
