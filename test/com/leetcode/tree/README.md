## Tree
Here's a list of all questions related to the tree data structure. 

### Tree traversal
        3       Pre-order: 3 4 1 5 7
      4   7     Post-order: 1 5 4 7 3
    1  5        In-order: 1 4 5 3 7
 - Recursive solution is trivial. But it should be the first approach. 
 - Iteration is tricky. 
  - Pre-order: Easy. [Code](https://github.com/jiguan/LeetCode/blob/master/test/com/leetcode/tree/BinaryTreePreorderTraversal.java)
        1. Push root to stack 
        2. Inside while loop, pop out the node, and save its value to result list 
        3. If node has left, push right to stack, so as left node  
  - Post-order: Tricky. Similar to pre-order. [Code](https://github.com/jiguan/LeetCode/blob/master/test/com/leetcode/tree/BinaryTreePostorderTraversal.java)
        1. Push the root to the stack 
        2. Pop up the stack, if node'e left is not null, add left to the stack, so as the right node. Record the node's val 
        3. **Outside the while loop, reverse res**
  - In-order: Tricky. [Code](https://github.com/jiguan/LeetCode/blob/master/test/com/leetcode/tree/BinaryTreeInorderTraversal.java)
        1. Create a node variable outside while loop and do not push node to the stack 
        1. `while(prev!=null || !stack.isEmpty())`, keep pushing node's left to the stack 
        1. Update the node by pop up the stack, and record its value 
        1. Make node point to node.right

**Pre-order and post-order are very similar** [Code](https://github.com/jiguan/LeetCode/blob/master/src/com/leetcode/util/Tree.java)

| Pre-order        | Post-order      |
| ---------------- | --------------- |
| Push right first | Push left first |
| Add to result    | Reverse result  |

### From in-order and pre-order arrays
The basic idea is:
  1. Create a builder function which takes `pre_start`, `pre_end`, `in_start`, `in_end`
  1. We can notice that the `preorder`'s structure is like this : head -> left -> right; `inorder`'s structure is left -> head -> right
  1. The first element `pre_start` inside `preorder` is always a root and we need to confirm the boundary between left and right. The left subtree length can be determined by checking `inorder` array. 
    * First find the index of root element in `inorder`, then the `leftSubLen = in_root - in_start` 
    * Determine the `pre_start` for right subtree by `pre_start + leftSubLen + 1`
```
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    
    private TreeNode build(int[] preorder, int pre_start, int pre_end, int[] inorder, int in_start, int in_end) {
        if(pre_start > pre_end || in_start > in_end) return null;
        
        TreeNode root = new TreeNode(preorder[pre_start]);
        
        int in_root = in_start;
        while(inorder[in_root]!= root.val) {
            in_root++;
        }
        //here calculate the left sub-tree length
        int leftSubLen = in_root - in_start;
        root.left = build(preorder, pre_start+1, pre_start + leftSubLen, inorder, in_start, in_root -1);
        root.right = build(preorder, pre_start + leftSubLen +1, pre_end, inorder, in_root+1, in_end);
        return root;
    }
```

### Questions
#### BST inorder traversal generates a sorted array
  1. [Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/)

#### Tree nodes/depth counting
  1. [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/) Try to solve it using recursive.
  1. [Find Bottom Left Tree Value](https://leetcode.com/problems/find-bottom-left-tree-value/description/)

#### BFS and DFS
  1. [Average of Levels in Binary Tree](https://leetcode.com/problems/average-of-levels-in-binary-tree/description/)
  1. [Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/)
