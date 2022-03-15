package com.gtw.leetcode.editor.cn;

//你将会获得一系列视频片段，这些片段来自于一项持续时长为 time 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。 
//
// 使用数组 clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于 starti 并于 endi
// 结束。 
//
// 甚至可以对这些片段自由地再剪辑： 
//
// 
// 例如，片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。 
// 
//
// 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1
// 。 
//
// 
//
// 示例 1： 
//
// 
//输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
//输出：3
//解释：
//选中 [0,2], [8,10], [1,9] 这三个片段。
//然后，按下面的方案重制比赛片段：
//将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
//现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
// 
//
// 示例 2： 
//
// 
//输入：clips = [[0,1],[1,2]], time = 5
//输出：-1
//解释：
//无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
// 
//
// 示例 3： 
//
// 
//输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],
//[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
//输出：3
//解释： 
//选取片段 [0,4], [4,7] 和 [6,9] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= clips.length <= 100 
// 0 <= starti <= endi <= 100 
// 1 <= time <= 100 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 264 👎 0

import java.util.Arrays;

/**
 * 视频拼接
 * @author gtw
 */
public class VideoStitching {

    public static void main(String[] args) {
        Solution solution = new VideoStitching().new Solution();
        System.out.println(solution.videoStitching(new int[][] {{0, 2}, {4, 8}}, 5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 区间问题肯定是按照区间的起点或者终点进行排序（至于到底如何排序，这个就要因题而异）
         */
        public int videoStitching(int[][] clips, int time) {
            if (time == 0) {
                return 0;
            }
            // 按起点升序排列，起点相同的降序排列
            Arrays.sort(clips, (a, b) -> {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            });

            int result = 0;
            int i = 0;
            int currentEnd = 0, nextEnd = 0;;
            while (i < clips.length && clips[i][0] <= currentEnd) {
                while (i < clips.length && clips[i][0] <= currentEnd) {
                    nextEnd = Math.max(nextEnd, clips[i][1]);
                    i++;
                }
                result++;
                currentEnd = nextEnd;
                if(currentEnd >= time) {
                    return result;
                }
            }

            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
