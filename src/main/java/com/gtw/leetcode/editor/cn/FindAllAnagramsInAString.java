package com.gtw.leetcode.editor.cn;

//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 104 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 800 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 找到字符串中所有字母异位词
 * @author gtw
 */
public class FindAllAnagramsInAString {

    public static void main(String[] args) {
        Solution solution = new FindAllAnagramsInAString().new Solution();
        System.out.println(Arrays.toString(solution.findAnagrams("cbaebabacd", "abc").toArray()));
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // s = "cbaebabacd", p = "abc"
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> result = new ArrayList<>();
            // 两个窗口
            // 1. 记录窗口中的符合条件的字符
            Map<Character, Integer> window = new HashMap<>();
            // 2. 目标需要的字符以及需要的对应的数量
            Map<Character, Integer> need = new HashMap<>();
            for(Character c : p.toCharArray()) {
                need.put(c, need.getOrDefault(c, 0) + 1);
            }

            // 窗口左右指针
            int left = 0,right = 0;
            int count = 0;
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;
                if(need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if(window.get(c).equals(need.get(c))) {
                        count++;
                    }
                }

                while (right - left >= p.length()) {
                    if(count == need.size()) {
                        result.add(left);
                    }
                    char d = s.charAt(left);
                    left++;
                    if(need.containsKey(d)) {
                        if(window.get(d).equals(need.get(d))) {
                            count--;
                        }
                        window.put(d, window.get(d) - 1);
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
