package com.gtw.leetcode.editor.cn;

//请实现两个函数，分别用来序列化和反序列化二叉树。 
//
// 示例: 
//
// 你可以将以下二叉树：
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//序列化为 "[1,2,3,null,null,4,5]" 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-b
//inary-tree/ 
// Related Topics 树 设计 
// 👍 140 👎 0

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化二叉树
 *
 * @author gtw
 */
public class XuLieHuaErChaShuLcof {

    public static void main(String[] args) {
        Codec codec = new XuLieHuaErChaShuLcof().new Codec();
        String root = "1,2,3,null,null,4,5";
        TreeNode rootNode = codec.deserialize(root);
        System.out.println(codec.serialize(rootNode));
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
    class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            //if (root == null) {
            //    return "[]";
            //}
            //StringBuilder res = new StringBuilder("[");
            //// 使用BFS方法，利用队列新建二叉树
            //Queue<TreeNode> queue = new LinkedList<TreeNode>() {{ add(root); }};
            //while (!queue.isEmpty()) {
            //    TreeNode node = queue.poll();
            //    if (node != null) {
            //        res.append(node.val).append(",");
            //        queue.add(node.left);
            //        queue.add(node.right);
            //    } else {
            //        res.append("null,");
            //    }
            //}
            //res.deleteCharAt(res.length() - 1);
            //res.append("]");
            //return res.toString();

            // DFS 前序遍历
            if (root == null) {
                return "null";
            }
            return root.val + "," + serialize(root.left) + "," + serialize(root.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            //// 使用BFS方法，利用队列新建二叉树
            //if (data == null || "".equals(data) || "[]".equals(data)) {
            //    return null;
            //}
            //data = data.replace("[", "").replace("]", "");
            //String[] str = data.split(",");
            //if (str.length == 0) {
            //    return null;
            //}
            //if ("null".equals(str[0])) {
            //    return null;
            //}
            //TreeNode root = new TreeNode(Integer.parseInt(str[0]));
            //Queue<TreeNode> nodes = new LinkedList<>();
            //nodes.add(root);
            //int i = 1;
            //while (!nodes.isEmpty() && i < str.length) {
            //    TreeNode node = nodes.poll();
            //    if (!"null".equals(str[i])) {
            //        TreeNode left = new TreeNode(Integer.parseInt(str[i]));
            //        node.left = left;
            //        nodes.add(left);
            //    }
            //    i++;
            //    if (!"null".equals(str[i])) {
            //        TreeNode right = new TreeNode(Integer.parseInt(str[i]));
            //        node.right = right;
            //        nodes.add(right);
            //    }
            //    i++;
            //}
            //return root;
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return dfs(queue);
        }

        private TreeNode dfs(Queue<String> queue) {
            String val = queue.poll();
            if (val == null || "null".equals(val)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(val));
            root.left = dfs(queue);
            root.right = dfs(queue);
            return root;
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
    //leetcode submit region end(Prohibit modification and deletion)

}
