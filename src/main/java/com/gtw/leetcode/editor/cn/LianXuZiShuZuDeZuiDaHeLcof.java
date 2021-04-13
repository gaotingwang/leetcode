package com.gtw.leetcode.editor.cn;

//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。 
//
// 要求时间复杂度为O(n)。 
//
// 
//
// 示例1: 
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// -100 <= arr[i] <= 100 
// 
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
//
// 
// Related Topics 分治算法 动态规划 
// 👍 251 👎 0

import java.util.Arrays;

/**
 * 连续子数组的最大和
 *
 * @author gtw
 */
public class LianXuZiShuZuDeZuiDaHeLcof {

    public static void main(String[] args) {
        Solution solution = new LianXuZiShuZuDeZuiDaHeLcof().new Solution();
        System.out.println(solution.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 存在最优子结构和重复子问题，典型动态规划问题
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            // 1. 初始化base case
            // 为了满足子数组的要求，保证连续性，dp[i] 代表以元素 nums[i] 为结尾的连续子数组最大和
            // todo: 由于 dp[i] 只与 dp[i-1] 和 nums[i] 有关系, 可用单独值存储，空间复杂度从 O(N) 降至 O(1)
            int[] dp = new int[nums.length + 1];
            //dp[0] = nums[0];

            // 2. 穷举状态
            for (int i = 1; i <= nums.length; i++) {
                // 3. 状态转移方程
                if(dp[i - 1] <= 0) {
                    dp[i] = nums[i - 1];
                }else {
                    dp[i] = dp[i - 1] + nums[i - 1];
                }
            }

            int res = dp[1];
            for(int i = 2; i< dp.length; i++) {
                res = Math.max(res, dp[i]);
            }

            return res;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}
