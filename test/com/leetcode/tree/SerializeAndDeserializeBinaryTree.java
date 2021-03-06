package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TreeNode;

// The encoded string should be as compact as possible.
public class SerializeAndDeserializeBinaryTree {
    private static int index = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#!";
        String res = root.val + "!";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split("!");
        index = 0;
        return build(arr);
    }

    private TreeNode build(String[] arr) {
        String val = arr[index++];
        if (val.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = build(arr);
        node.right = build(arr);
        return node;
    }

    @Test
    public void test1() {
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 3});
        assertEquals("1!2!#!#!3!#!#!", serialize(root));
        assertTrue(TreeNode.isSameTree(root, deserialize(serialize(root))));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.build(new Integer[]{1, 2, 3, 4});
        assertTrue(TreeNode.isSameTree(root, deserialize(serialize(root))));
    }
}
