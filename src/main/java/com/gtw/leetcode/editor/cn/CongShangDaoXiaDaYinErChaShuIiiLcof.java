package com.gtw.leetcode.editor.cn;

//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 
// 👍 90 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下打印二叉树 III
 *
 * @author gtw
 */
public class CongShangDaoXiaDaYinErChaShuIiiLcof {

    public static void main(String[] args) {
        Solution solution = new CongShangDaoXiaDaYinErChaShuIiiLcof().new Solution();
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            Queue<TreeNode> nodes = new LinkedList<>();
            if (root != null) {
                nodes.add(root);
            }
            int count = 1;
            while (!nodes.isEmpty()) {
                LinkedList<Integer> list = new LinkedList<>();
                for (int i = nodes.size(); i > 0; i--) {
                    TreeNode node = nodes.poll();
                    if (count % 2 == 1) {
                        list.addLast(node.val);
                    } else {
                        list.addFirst(node.val);
                    }

                    if (node.left != null) {
                        nodes.add(node.left);
                    }
                    if (node.right != null) {
                        nodes.add(node.right);
                    }
                }

                result.add(list);
                count++;
            }

            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
