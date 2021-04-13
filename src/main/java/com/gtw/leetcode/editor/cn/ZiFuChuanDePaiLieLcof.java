package com.gtw.leetcode.editor.cn;

//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 回溯算法 
// 👍 247 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串的排列
 *
 * @author gtw
 */
public class ZiFuChuanDePaiLieLcof {

    public static void main(String[] args) {
        Solution solution = new ZiFuChuanDePaiLieLcof().new Solution();
        System.out.println(Arrays.toString(solution.permutation("aac")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Set<String> result = new HashSet<>();

        /**
         * 穷举所有可能情况，典型回溯算法
         */
        public String[] permutation(String s) {
            StringBuilder tmp = new StringBuilder();
            Set<Integer> index = new HashSet<>();
            permutation(s, tmp, index);
            return result.toArray(new String[result.size()]);
        }

        private void permutation(String s, StringBuilder tmp, Set<Integer> index) {
            // 1. 到达决策树底层，满足条件退出条件
            if (tmp.length() == s.length()) {
                result.add(String.valueOf(tmp));
                return;
            }
            for (int i = 0; i < s.length(); i++) {
                if (index.contains(i)) {
                    continue;
                }
                // 2. 做选择
                index.add(i);
                tmp.append(s.charAt(i));
                // 3. 递归
                permutation(s, tmp, index);
                // 4. 撤销选择回退
                tmp.deleteCharAt(tmp.length() - 1);
                index.remove(i);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
