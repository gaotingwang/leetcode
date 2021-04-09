package com.gtw.leetcode.editor.cn;

//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。 
//
// 
//
// 参考以下这颗二叉搜索树： 
//
//      5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1： 
//
// 输入: [1,6,3,2,5]
//输出: false 
//
// 示例 2： 
//
// 输入: [1,3,2,6,5]
//输出: true 
//
// 
//
// 提示： 
//
// 
// 数组长度 <= 1000 
// 
// 👍 239 👎 0

import java.util.ArrayList;

/**
 * 二叉搜索树的后序遍历序列
 *
 * @author gtw
 */
public class ErChaSouSuoShuDeHouXuBianLiXuLieLcof {

    public static void main(String[] args) {
        Solution solution = new ErChaSouSuoShuDeHouXuBianLiXuLieLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public boolean verifyPostorder(int[] postorder) {
            // if(postorder == null || postorder.length == 0) {
            //     return true;
            // }
            // int root = postorder[postorder.length - 1];
            // boolean isBig = false;
            // ArrayList<Integer> pre = new ArrayList<>();
            // ArrayList<Integer> post = new ArrayList<>();
            // // 切记正向取，倒序取造成数组反转，计算有误
            // for(int i = 0 ; i < postorder.length - 1; i++) {
            //     if(postorder[i] < root && !isBig) {
            //         pre.add(postorder[i]);
            //     } else {
            //         isBig = true;
            //         if(postorder[i] > root) {
            //             post.add(postorder[i]);
            //         }else{
            //             return false;
            //         }
            //     }
            // }

            // return verifyPostorder(buildArray(pre)) && verifyPostorder(buildArray(post));
            return isPostorder(postorder, 0, postorder.length - 1);
        }

        // 数组操作要善用指针
        private boolean isPostorder(int[] postorder, int start, int end) {
            if (start >= end) {
                return true;
            }
            int root = postorder[end];
            int i = start;
            while (postorder[i] < root) {
                i++;
            }
            int j = i;
            while (postorder[j] > root) {
                j++;
            }
            return j == end && isPostorder(postorder, start, i - 1) && isPostorder(postorder, i, end - 1);
        }

        private int[] buildArray(ArrayList<Integer> list) {
            if (list == null || list.isEmpty()) {
                return new int[0];
            }
            int[] res = new int[list.size()];
            int i = 0;
            for (Integer num : list) {
                res[i] = num;
                i++;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
