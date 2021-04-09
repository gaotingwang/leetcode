package com.gtw.leetcode.editor.cn;

//从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。 
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
// 返回： 
//
// [3,9,20,15,7]
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
// 👍 79 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 从上到下打印二叉树
 *
 * @author gtw
 */
public class CongShangDaoXiaDaYinErChaShuLcof {

    public static void main(String[] args) {
        Solution solution = new CongShangDaoXiaDaYinErChaShuLcof().new Solution();
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
        // 从左到右遍历，即层遍历
        public int[] levelOrder(TreeNode root) {
            // 从左到右顺序处理，所以用队列把下次要处理的节点存起来，每次从队列里取节点进行处理
            if (root == null) {
                return new int[0];
            }

            List<Integer> list = new ArrayList<>();
            // 1. 创建队列
            LinkedList<TreeNode> nodes = new LinkedList<>();
            // 2. 初始化放入第一个节点
            nodes.add(root);
            // 3. 循环判断队列不为空
            while (!nodes.isEmpty()) {
                TreeNode node = nodes.poll();
                list.add(node.val);
                // 4. 放入当前层的节点
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            int[] result = new int[list.size()];
            int i = 0;
            for (int num : list) {
                result[i] = num;
                i++;
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
