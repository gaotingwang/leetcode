package com.gtw.leetcode.editor.cn;

//输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。 
//
// 例如： 
//
// 给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 10000 
// 
//
// 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tre
//e/ 
// Related Topics 树 深度优先搜索 
// 👍 104 👎 0

/**
 * 二叉树的深度
 *
 * @author gtw
 */
public class ErChaShuDeShenDuLcof {

    public static void main(String[] args) {
        Solution solution = new ErChaShuDeShenDuLcof().new Solution();
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

        public int maxDepth(TreeNode root) {
            // 方法一：层遍历 BFS
            //if (root == null) { return 0; }
            //// 1. 创建队列
            //List<TreeNode> queue = new LinkedList<>() {{ add(root); }}, tmp;
            //// 2. 初始化放入第一个节点
            //int res = 0;
            //// 3. 循环判断队列不为空
            //while (!queue.isEmpty()) {
            //    tmp = new LinkedList<>();
            //    for (TreeNode node : queue) {
            //        // 4. 放入当前层的节点
            //        if (node.left != null) { tmp.add(node.left); }
            //        if (node.right != null) { tmp.add(node.right); }
            //    }
            //    queue = tmp;
            //    res++;
            //}
            //return res;

            // 方法二：前序遍历 DFS
            return preOrderDfs(root, 0);
        }

        private int preOrderDfs(TreeNode node, int maxNum) {
            if (node == null) {
                return maxNum;
            }
            maxNum++;
            return Math.max(preOrderDfs(node.left, maxNum), preOrderDfs(node.right, maxNum));
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
