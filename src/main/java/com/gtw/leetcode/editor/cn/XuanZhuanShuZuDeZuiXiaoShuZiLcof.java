package com.gtw.leetcode.editor.cn;

//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2
//] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。 
//
// 示例 1： 
//
// 输入：[3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 输入：[2,2,2,0,1]
//输出：0
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sor
//ted-array-ii/ 
// Related Topics 二分查找 
// 👍 288 👎 0

/**
 * 旋转数组的最小数字
 *
 * @author gtw
 */
public class XuanZhuanShuZuDeZuiXiaoShuZiLcof {

    public static void main(String[] args) {
        Solution solution = new XuanZhuanShuZuDeZuiXiaoShuZiLcof().new Solution();
        System.out.println(solution.minArray(new int[]{2,2,2,0,1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 左排序数组, 旋转点, 右排序数组
         */
        public int minArray(int[] numbers) {
            int low = 0, high = numbers.length - 1;
            while (low < high) {
                int mid = (low + high) / 2;
                // 因为 high 初始值肯定在右排序数组中, 所以用mid跟high的数进行比较
                // 在数组完整递增情况下，low初始值无法确定在哪个排序数组中
                if(numbers[mid] < numbers[high]) {
                    high = mid;
                } else if (numbers[mid] > numbers[high]) {
                    low = mid + 1;
                }else {
                    // 中间有数和最右侧数相等，舍弃最右侧数
                    high--;
                }
            }
            return numbers[low];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
