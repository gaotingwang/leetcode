package com.gtw.leetcode.editor.cn;

//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// 👍 391 👎 0

/**
 * 数组中的逆序对
 *
 * @author gtw
 */
public class ShuZuZhongDeNiXuDuiLcof {

    public static void main(String[] args) {
        Solution solution = new ShuZuZhongDeNiXuDuiLcof().new Solution();
        System.out.println(solution.reversePairs(new int[] {7, 5, 6, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int reversePairs(int[] nums) {
            int[] tmp = new int[nums.length];
            return reverse(nums, 0, nums.length - 1, tmp);
        }

        /**
         * 采用归并排序，左侧有序数组和右侧有序数组，在并的时候，左侧有几个数比右侧当前位大，count就加几
         */
        private int reverse(int[] nums, int left, int right, int[] tmp) {
            if (left >= right) {
                return 0;
            }
            int mid = left + (right - left) / 2;

            int leftReverseCount = reverse(nums, left, mid, tmp);
            int rightReverseCount = reverse(nums, mid + 1, right, tmp);

            // 优化点，已经完全有序了，当前的逆序对为0
            if (nums[mid] <= nums[mid + 1]) {
                return leftReverseCount + rightReverseCount;

            }

            for (int index = left; index <= right; index++) {
                tmp[index] = nums[index];
            }
            int currentReverseCount = 0;
            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j];
                    j++;
                } else if (j == right + 1) {
                    nums[k] = tmp[i];
                    i++;
                } else if (tmp[i] > tmp[j]) {
                    nums[k] = tmp[j];
                    j++;
                    // 只有这里会产生逆序，进行统计次数
                    currentReverseCount += (mid - i + 1);
                } else if (tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i];
                    i++;
                }
            }

            return leftReverseCount + rightReverseCount + currentReverseCount;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
