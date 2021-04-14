package com.gtw.leetcode.editor.cn;

//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。 
//
// 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
//输出: 2 
//
// 
//
// 限制： 
//
// 1 <= 数组长度 <= 50000 
//
// 
//
// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/ 
//
// 
// Related Topics 位运算 分治算法 
// 👍 142 👎 0

/**
 * 数组中出现次数超过一半的数字
 *
 * @author gtw
 */
public class ShuZuZhongChuXianCiShuChaoGuoYiBanDeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new ShuZuZhongChuXianCiShuChaoGuoYiBanDeShuZiLcof().new Solution();
        System.out.println(solution.majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            // 出现过半的，肯定能站到最后
            int res = nums[0];
            int count = 0;
            for (int num : nums) {
                if (count == 0) {
                    res = num;
                }
                count += num == res ? 1 : -1;
            }
            // 但要明确站到最后的不一定是众数，此题明确了数量过半，若是改为众数判断，需要判断x是否过半
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
