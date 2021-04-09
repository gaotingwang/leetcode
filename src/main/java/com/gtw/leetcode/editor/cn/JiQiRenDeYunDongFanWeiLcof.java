package com.gtw.leetcode.editor.cn;

//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 2： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
// 👍 262 👎 0

import java.util.LinkedList;
import java.util.Queue;

/**
 * 机器人的运动范围
 *
 * @author gtw
 */
public class JiQiRenDeYunDongFanWeiLcof {

    public static void main(String[] args) {
        Solution solution = new JiQiRenDeYunDongFanWeiLcof().new Solution();
        System.out.println(solution.movingCount(1, 2, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 搜索类问题，需要遍历矩阵，矩阵搜索一定需要一个状态记录当前位置是否被访问过
     */
    class Solution {
        public int movingCount(int m, int n, int k) {
            boolean[][] isVisit = new boolean[m][n];
            //return dfsCount(isVisit, 0, 0, k);
            return bfsCount(isVisit, m, n, k);
        }

        /**
         * 法一：递归的DFS(一条路走到黑)，需要考虑状态和子问题的概念
         */
        private int dfsCount(boolean[][] isVisit, int i, int j, int k) {
            if(i < 0 || i >= isVisit.length || j < 0 || j >= isVisit[i].length || (sum(i) + sum(j)) > k || isVisit[i][j]) {
                return 0;
            }
            // 记录访问过的状态
            isVisit[i][j] = true;
            //return 1 + dfsCount(isVisit, i - 1, j, k) + dfsCount(isVisit, i + 1, j, k) + dfsCount(isVisit, i, j-1, k) + dfsCount(isVisit, i, j + 1, k);
            // 优化点：只会向右和向下走
            return 1 + dfsCount(isVisit, i + 1, j, k) + dfsCount(isVisit, i, j + 1, k);
        }

        /**
         * 法二：队列的BFS，按照“平推”的方式向前搜索
         */
        private int bfsCount(boolean[][] isVisit, int i, int j, int k) {
            int res = 0;
            Queue<int[]> queue= new LinkedList<int[]>();
            // 初始化，将初始点 (0, 0) 加入队列 queue
            queue.add(new int[]{0, 0});
            while(!queue.isEmpty()) {
                int[] tmp = queue.poll();
                if (tmp[0] < 0 || tmp[0] >= i || tmp[1] < 0 || tmp[1] >= j || (sum(tmp[0]) + sum(tmp[1])) > k
                    || isVisit[tmp[0]][tmp[1]]) {
                    continue;
                }
                isVisit[tmp[0]][tmp[1]] = true;
                res++;
                // 把当前一层入队，先处理完，再处理下一层
                queue.add(new int[] {tmp[0] + 1, tmp[1]});
                queue.add(new int[] {tmp[0], tmp[1] + 1});
            }
            return res;
        }

        // 位数和
        private int sum(int x) {
            int s = 0;
            while (x != 0) {
                s += x % 10;
                x = x / 10;
            }
            return s;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
