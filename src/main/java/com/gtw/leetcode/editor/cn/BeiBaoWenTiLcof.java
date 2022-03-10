package com.gtw.leetcode.editor.cn;

//
// 有一个可装载容量为 W 的背包和 N 个物品，每个物品有价值和种类两种属性。
// 其中第 i 个物品的重量为wt[i] ,价值为val[i]
// 用这个背包装物品，最多能装的价值是多少
//

/**
 * 0-1 背包问题
 *
 * @author gtw
 */
public class BeiBaoWenTiLcof {

    public static void main(String[] args) {
        Solution solution = new BeiBaoWenTiLcof().new Solution();
        // 6
        System.out.println(solution.knapsack(4, 3, new int[]{2, 1, 3}, new int[]{4, 2, 3}));
    }

    class Solution {
        public int knapsack(int W, int N, int[] wt, int[] val) {
            // dp[i][w] 定义：对于第i个物品，当背包容量为w时，能装的最大价值
            int[][] dp = new int[N + 1][W + 1];
            // 初始状态 dp[0][...] = 0, dp[...][0] = 0

            for (int i = 1; i <= N; i++) {
                for (int w = 1; w <= W; w++) {
                    if (w - wt[i - 1] < 0) {
                        // 这种情况不入包
                        dp[i][w] = dp[i - 1][w];
                    } else {
                        // 选择是，在w约束下，把i不装进背包的最大价值，和把i装进背包的最大价值
                        dp[i][w] = Math.max(dp[i - 1][w], val[i - 1] + dp[i - 1][w - wt[i - 1]]);
                    }
                }
            }
            return dp[N][W];
        }
    }

}
