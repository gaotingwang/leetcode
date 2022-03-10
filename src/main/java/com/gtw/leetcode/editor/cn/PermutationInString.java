package com.gtw.leetcode.editor.cn;

//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。 
//
// 换句话说，s1 的排列之一是 s2 的 子串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
// 
//
// 示例 2： 
//
// 
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 104 
// s1 和 s2 仅包含小写字母 
// 
// Related Topics 哈希表 双指针 字符串 滑动窗口 
// 👍 597 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串的排列
 * @author gtw
 */
public class PermutationInString {

    public static void main(String[] args) {
        Solution solution = new PermutationInString().new Solution();
        System.out.println(solution.checkInclusion("ab", "eidboaoo"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            // 两个窗口
            // 1. 记录窗口中的符合条件的字符
            Map<Character, Integer> window = new HashMap<>();
            // 2. 目标需要的字符以及需要的对应的数量
            Map<Character, Integer> need = new HashMap<>();
            for(Character c : s1.toCharArray()) {
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            // 窗口左右指针
            int left = 0,right = 0;
            int count = 0;
            while (right < s2.length()) {
                char c  = s2.charAt(right);
                right++;
                if(need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if(window.get(c).equals(need.get(c))) {
                        count++;
                    }
                }

                // 这里与【最小覆盖子串】不同的是，要求包含连续的子串，子串不能被分割
                while (right - left >= s1.length()) {
                    if (count == need.size()) {
                        return true;
                    }
                    char d = s2.charAt(left);
                    left++;
                    if (need.containsKey(d)) {
                        if (window.get(d).equals(need.get(d))) {
                            count--;
                        }
                        window.put(d, window.get(d) - 1);
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
