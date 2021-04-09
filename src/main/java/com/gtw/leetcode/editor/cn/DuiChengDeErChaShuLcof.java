package com.gtw.leetcode.editor.cn;

//请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
// 1 
// / \ 
// 2 2 
// / \ / \ 
//3 4 4 3 
//但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
// 1 
// / \ 
// 2 2 
// \ \ 
// 3 3 
//
// 
//
// 示例 1： 
//
// 输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
//
// 输入：root = [1,2,2,null,3,null,3]
//输出：false 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/ 
// Related Topics 树 
// 👍 158 👎 0

/**
 * 对称的二叉树
 *
 * @author gtw
 */
public class DuiChengDeErChaShuLcof {

    public static void main(String[] args) {
        Solution solution = new DuiChengDeErChaShuLcof().new Solution();
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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }

            // 法一：层遍历 + 双端队列
            // LinkedList<TreeNode> list = new LinkedList<>();
            // list.addFirst(root.left);
            // list.addLast(root.right);
            // while(!list.isEmpty()) {
            //     TreeNode left = list.pollFirst();
            //     TreeNode right = list.pollLast();
            //     if(left == null && right == null) {
            //         continue;
            //     }
            //     if(left == null || right == null || left.val != right.val) {
            //         return false;
            //     }
            //     list.addFirst(left.left);
            //     list.addFirst(left.right);
            //     list.addLast(right.right);
            //     list.addLast(right.left);
            // }
            // return true;

            // 法二：递归
            return recursion(root.left, root.right);
        }

        private boolean recursion(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            return recursion(left.left, right.right) && recursion(left.right, right.left);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
