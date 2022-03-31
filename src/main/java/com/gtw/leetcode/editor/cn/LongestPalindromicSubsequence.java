package com.gtw.leetcode.editor.cn;

//给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。 
//
// 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bbbab"
//输出：4
//解释：一个可能的最长回文子序列为 "bbbb" 。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出：2
//解释：一个可能的最长回文子序列为 "bb" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 740 👎 0

/**
 * 最长回文子序列
 * @author gtw
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();
        System.out.println(solution.longestPalindromeSubseq("bbbab"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            // 在子串s[i..j]中，最长回文子序列的长度为dp[i][j]，子问题为s[i+1..j-1]中最长回文子序列的长度
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }

            // 倒着遍历 （下到上，左到右）
            //for (int i = n - 1; i >= 0; i--) {
            //    for (int j = i + 1; j < n; j++) {
            //        if (s.charAt(i) == s.charAt(j)) {
            //            dp[i][j] = dp[i + 1][j - 1] + 2;
            //        } else {
            //            dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            //        }
            //    }
            //}

            // https://mp.weixin.qq.com/s/zNai1pzXHeB2tQE6AdOXTA (左到右，下到上)
            for (int j = 1; j < n; j++) {
                for (int i = j - 1; i >= 0; i--) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[0][n - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
