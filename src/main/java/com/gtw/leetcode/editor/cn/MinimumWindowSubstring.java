package com.gtw.leetcode.editor.cn;

//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 
// 👍 1689 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 * @author gtw
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            // 两个窗口
            // 1. 记录窗口中的符合条件的字符
            Map<Character, Integer> window = new HashMap<>();
            // 2. 目标需要的字符以及需要的对应的数量
            Map<Character, Integer> need = new HashMap<>();
            for(Character c : t.toCharArray()) {
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            // 窗口左右指针
            int left = 0,right = 0;

            int count = 0;
            int minLength = Integer.MAX_VALUE;
            int start = 0;
            while(right < s.length()){
                char c = s.charAt(right);
                right++;
                if(need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if(need.get(c).equals(window.get(c))){
                        count++;
                    }
                }
                // 获取到可行解时，开始计算最优解
                while (count == need.size()) {
                    if (right - left < minLength) {
                        minLength = right - left;
                        start = left;
                    }
                    // 更新窗口——这段代码逻辑几乎完全同上面的更新窗口
                    char d = s.charAt(left);
                    left++;
                    if (need.containsKey(d)) {
                        if (need.get(d).equals(window.get(d))) {
                            count--;
                        }
                        window.put(d, window.get(d) - 1);
                    }
                }
            }

            return minLength == Integer.MAX_VALUE ? "" : s.substring(start , start + minLength);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
