package com.gtw.leetcode.editor.cn;

//给定一棵二叉搜索树，请找出其中第k大的节点。 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 4 
//
// 示例 2: 
//
// 输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 4 
//
// 
//
// 限制： 
//
// 1 ≤ k ≤ 二叉搜索树元素个数 
// Related Topics 树 
// 👍 143 👎 0

/**
 * 二叉搜索树的第k大节点
 *
 * @author gtw
 */
public class ErChaSouSuoShuDeDiKdaJieDianLcof {

    public static void main(String[] args) {
        Solution solution = new ErChaSouSuoShuDeDiKdaJieDianLcof().new Solution();
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
        int k, num;

        /**
         * 对二叉搜索树中序遍历，即可形成从小到大的有序链表
         */
        public int kthLargest(TreeNode root, int k) {
            this.k = k;
            inOrderDfs(root);
            return num;
        }

        private void inOrderDfs(TreeNode root) {
            if (root == null || k <= 0) {
                return;
            }
            // 中序遍历倒序，即可形成从大到小的有序链表
            inOrderDfs(root.right);
            k--;
            if (k == 0) {
                num = root.val;
                return;
            }
            inOrderDfs(root.left);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
