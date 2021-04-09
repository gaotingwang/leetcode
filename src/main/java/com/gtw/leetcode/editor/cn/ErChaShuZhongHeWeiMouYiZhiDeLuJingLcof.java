package com.gtw.leetcode.editor.cn;

//输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。 
//
// 
//
// 示例: 
//给定如下二叉树，以及目标和 target = 22， 
//
// 
//              5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
// 
//
// 返回: 
//
// 
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 10000 
// 
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/ 
// Related Topics 树 深度优先搜索 
// 👍 162 👎 0

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中和为某一值的路径
 *
 * @author gtw
 */
public class ErChaShuZhongHeWeiMouYiZhiDeLuJingLcof {

    public static void main(String[] args) {
        Solution solution = new ErChaShuZhongHeWeiMouYiZhiDeLuJingLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int target) {
            List<List<Integer>> result = new ArrayList<>();
            preOrderDfs(result, new ArrayList<>(), root, target);
            return result;
        }

        // 前序遍历
        private void preOrderDfs(List<List<Integer>> result, List<Integer> oneBranch, TreeNode node, int target) {
            if (node == null) {
                return;
            }
            oneBranch.add(node.val);
            if (target - node.val == 0 && node.left == null && node.right == null) {
                result.add(oneBranch);
                return;
            }
            preOrderDfs(result, new ArrayList<>(oneBranch), node.left, target - node.val);
            preOrderDfs(result, new ArrayList<>(oneBranch), node.right, target - node.val);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
