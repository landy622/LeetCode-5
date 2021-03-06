package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class LowestCommonAncestorOfABinaryTree {
    // In general, the computational time required for this algorithm is O(h) where h is the height of the tree (length
    // of longest path from a leaf to the root)
    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        if (left != null) return left;
        if (right != null) return right;

        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // node - parent
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        parent.put(root, null);
        stack.push(root);

        // traverse all nodes until both p and q are visited
        while (!(parent.containsKey(p) && parent.containsKey(q))) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancesstors = new HashSet<>();
        while (p != null) {
            ancesstors.add(p);
            p = parent.get(p);
        }
        while (!ancesstors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }

    //@formatter:off
	/*
	 *  _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
	 */
	//@formatter:on

    @Test
    public void test0() {
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5), node1 = new TreeNode(1), node6 = new TreeNode(6), node2 = new TreeNode(2),
                node0 = new TreeNode(0), node8 = new TreeNode(8), node7 = new TreeNode(7), node4 = new TreeNode(4);
        root.left = node5;
        root.right = node1;
        node5.left = node6;
        node5.right = node2;
        node2.left = node7;
        node2.right = node4;
        node1.left = node0;
        node1.right = node8;

        assertEquals(node5, lowestCommonAncestor(root, node6, node4));
        assertEquals(node2, lowestCommonAncestor(root, node2, node4));
        assertEquals(root, lowestCommonAncestor(root, node6, node8));
    }

}
