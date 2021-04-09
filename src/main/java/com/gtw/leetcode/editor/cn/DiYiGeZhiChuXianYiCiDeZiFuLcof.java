package com.gtw.leetcode.editor.cn;

//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。 
//
// 示例: 
//
// s = "abaccdeff"
//返回 "b"
//
//s = "" 
//返回 " "
// 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 50000 
// Related Topics 哈希表 
// 👍 83 👎 0

import java.util.HashMap;
import java.util.Map;

/**
 * 第一个只出现一次的字符
 *
 * @author gtw
 */
public class DiYiGeZhiChuXianYiCiDeZiFuLcof {

    public static void main(String[] args) {
        Solution solution = new DiYiGeZhiChuXianYiCiDeZiFuLcof().new Solution();
        System.out.println(solution.firstUniqChar("abaccdeff"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public char firstUniqChar(String s) {
            // 在哈希表的基础上，也可以采用有序哈希表，其键值对是按照插入顺序排序的
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            }
            for (int i = 0; i < s.length(); i++) {
                if (map.get(s.charAt(i)) == 1) {
                    return s.charAt(i);
                }
            }
            return ' ';
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
