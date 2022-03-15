package com.gtw.leetcode.editor.cn;

//给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重
//叠 。 
//
// 
//
// 示例 1: 
//
// 
//输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
//输出: 1
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: intervals = [ [1,2], [1,2], [1,2] ]
//输出: 2
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: intervals = [ [1,2], [2,3] ]
//输出: 0
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= intervals.length <= 105 
// intervals[i].length == 2 
// -5 * 104 <= starti < endi <= 5 * 104 
// 
// Related Topics 贪心 数组 动态规划 排序 
// 👍 628 👎 0

import java.util.Arrays;
import java.util.Comparator;

/**
 * 无重叠区间
 * @author gtw
 */
public class NonOverlappingIntervals {

    public static void main(String[] args) {
        Solution solution = new NonOverlappingIntervals().new Solution();
        System.out.println(solution.eraseOverlapIntervals(new int[][] {{1, 3}, {2, 3}, {3, 4}, {1, 3}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            int n = intervals.length;
            return n - intervalSchedule(intervals);
        }

        /**
         * 判断不重复区间
         * 贪心算法，每一步都做出一个局部最优的选择，最终的结果就是全局最优
         * 区间调度类问题，主要就是对需要调度的区间，先进行排序（按开始或结束点排序，视情况而定）,然后判断不相交的区间
         * 每次选择可选区间中开始最早的那个？但是可能存在某些区间开始很早，但是很长，会错误地错过了一些短的区间。
         * 或者每次选择可选区间中最短的那个？或者选择出现冲突最少的那个区间？这些方案都能很容易举出反例，不是正确的方案。
         * 本题选择结束时间最早的
         */
        private int intervalSchedule(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            // 按 end 升序排序
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[1] - b[1];
                }
            });
            // 至少有一个区间不相交
            int count = 1;
            // 排序后，第一个区间就是 x
            int xEnd = intervals[0][1];
            for (int[] interval : intervals) {
                int start = interval[0];
                if (start >= xEnd) {
                    // 找到下一个选择的区间了
                    count++;
                    xEnd = interval[1];
                }
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
