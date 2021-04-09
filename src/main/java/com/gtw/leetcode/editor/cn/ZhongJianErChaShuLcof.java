package com.gtw.leetcode.editor.cn;

//输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
//
// 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 递归 
// 👍 408 👎 0

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 重建二叉树
 *
 * @author gtw
 */
public class ZhongJianErChaShuLcof {

    public static void main(String[] args) {
        Solution solution = new ZhongJianErChaShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // return recursion(preorder, inorder);
            return dieDai(preorder, inorder);
        }

        /**
         * 方法一：使用递归
         * 前序遍历性质： 节点按照 [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ] 排序。
         * 中序遍历性质： 节点按照 [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ] 排序。
         */
        private TreeNode recursion(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            if (inorder == null || inorder.length == 0) {
                return null;
            }
            TreeNode node = new TreeNode(preorder[0]);
            if (preorder.length == 1 || inorder.length == 1) {
                return node;
            }

            int left = 0, right = 0, index = 0;
            for (int num : inorder) {
                if (preorder[0] == num) {
                    left = index - 1;
                    right = index + 1;
                    break;
                }
                index++;
            }

            int leftSize = left + 1;
            node.left = recursion(Arrays.copyOfRange(preorder, 1, leftSize + 1),
                Arrays.copyOfRange(inorder, 0, leftSize + 1));
            node.right = recursion(Arrays.copyOfRange(preorder, leftSize + 1, preorder.length),
                Arrays.copyOfRange(inorder, right, inorder.length));
            return node;
        }

        // 方法二：前序遍历，从根节点root开始，只要有左子节点，就一直会往左下方走，直到最左下角。 而中序遍历，是从最左下角往上（示例中的4-5-8-9-3），如果碰到节点有右子节点，则会转向（示例中的8-10）。
        private TreeNode dieDai(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.push(root);
            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int preorderVal = preorder[i];
                TreeNode node = stack.peek();
                // 代码中的if块是用前序数组一直构建左子树
                if (node.val != inorder[inorderIndex]) {
                    node.left = new TreeNode(preorderVal);
                    stack.push(node.left);
                } else {
                    // 如果碰到了inorder[inorderIndex]，表示到了左下角，这时就需要往上走并处理右子树
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex++;
                    }
                    node.right = new TreeNode(preorderVal);
                    stack.push(node.right);
                }
            }
            return root;
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)

}
