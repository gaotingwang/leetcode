package com.gtw.leetcode.editor.cn;

//一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [4,1,4,6]
//输出：[1,6] 或 [6,1]
// 
//
// 示例 2： 
//
// 输入：nums = [1,2,10,4,1,4,3,3]
//输出：[2,10] 或 [10,2] 
//
// 
//
// 限制： 
//
// 
// 2 <= nums.length <= 10000 
// 
//
// 
// 👍 370 👎 0

import java.util.Arrays;

/**
 * 数组中数字出现的次数
 *
 * @author gtw
 */
public class ShuZuZhongShuZiChuXianDeCiShuLcof {

    public static void main(String[] args) {
        Solution solution = new ShuZuZhongShuZiChuXianDeCiShuLcof().new Solution();
        System.out.println(Arrays.toString(solution.singleNumbers(new int[] {1, 2, 10, 4, 1, 4, 3, 3})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] singleNumbers(int[] nums) {
            int x = 0, y = 0, n = 0, m = 1;
            // 采用异或，相同数字异或为0
            // 1. 遍历异或， 得到的二进制可以看出a,b两个数哪个位置不同，不同的为1
            for (int num : nums) {
                n ^= num;
            }
            // 2. 循环左移，计算 m 位为1
            while ((n & m) == 0) {
                m <<= 1;
            }
            // 3. 遍历 nums 分组
            for (int num : nums) {
                // 4. 当 num & m != 0
                if ((num & m) != 0) {
                    x ^= num;
                } else {
                    y ^= num;
                }
            }
            // 5. 返回出现一次的数字
            return new int[] {x, y};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
