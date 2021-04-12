package com.gtw.leetcode.editor.cn;

//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 示例 1： 
//
// 输入：n = 2
//输出：2
// 
//
// 示例 2： 
//
// 输入：n = 7
//输出：21
// 
//
// 示例 3： 
//
// 输入：n = 0
//输出：1 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/ 
//
// 
// Related Topics 递归 
// 👍 148 👎 0

/**
 * 青蛙跳台阶问题
 *
 * @author gtw
 */
public class QingWaTiaoTaiJieWenTiLcof {

    public static void main(String[] args) {
        Solution solution = new QingWaTiaoTaiJieWenTiLcof().new Solution();
        System.out.println(solution.numWays(0));
        System.out.println(solution.numWays(10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numWays(int n) {
            int[] count = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                if (i == 0) {
                    count[i] = 1;
                    continue;
                }
                if (i < 3) {
                    count[i] = i;
                    continue;
                }
                count[i] = (count[i - 1] + count[i - 2]) % 1000000007;
            }

            return count[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
