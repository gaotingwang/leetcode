package com.gtw.leetcode.editor.cn;

//在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [3,4,3,3]
//输出：4
// 
//
// 示例 2： 
//
// 输入：nums = [9,1,7,9,7,9,7]
//输出：1 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10000 
// 1 <= nums[i] < 2^31 
// 
//
// 
// 👍 167 👎 0

/**
 * 数组中数字出现的次数 II
 *
 * @author gtw
 */
public class ShuZuZhongShuZiChuXianDeCiShuIiLcof {

    public static void main(String[] args) {
        Solution solution = new ShuZuZhongShuZiChuXianDeCiShuIiLcof().new Solution();
        System.out.println(solution.singleNumber(new int[] {9, 1, 7, 9, 7, 9, 7}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 上一题出现偶数次，可以考虑使用异或方式，相同数异或变为0
         * 本题出现奇数次，异或不合适，但还可以沿用位运算的思路
         * 如果一个数字出现三次,那么它的二进制表示的每一位(0或者1)也出现三次。如果把所有出现三次的数字的二进制表示的每一位都分别加起来,那么每一位的和都能被3整除
         */
        public int singleNumber(int[] nums) {
            // 用于记录各二进制位的 1 的出现次数
            int[] counts = new int[32];
            for(int num : nums) {
                for(int j = 0; j < 32; j++) {
                    // 当前数第j位是否为1，为1则加1，更新第 j 位
                    counts[j] += num & 1;
                    // 第 j 位 --> 第 j + 1 位
                    num >>>= 1;
                }
            }
            int res = 0, m = 3;
            for(int i = 0; i < 32; i++) {
                res <<= 1;
                // 与3取余的值,恢复第 i 位的值到 res
                res |= counts[31 - i] % m;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
