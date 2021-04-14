package com.gtw.leetcode.editor.cn;

//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
//
// 
//
// 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/ 
// Related Topics 递归 
// 👍 146 👎 0

/**
 * 数值的整数次方
 *
 * @author gtw
 */
public class ShuZhiDeZhengShuCiFangLcof {

    public static void main(String[] args) {
        Solution solution = new ShuZhiDeZhengShuCiFangLcof().new Solution();
        System.out.println(solution.myPow(2d, -2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double myPow(double x, int n) {
            if (x == 0) {
                return 0;
            }
            // 防止次幂是负数，对幂运算先做转换
            // 防止次幂是最小负数，取反会出现越界，用long来存次幂
            long b = n;
            if (b < 0) {
                x = 1 / x;
                b = -b;
            }

            double res = 1.0;
            // x^n = x^{n/2} \times x^{n/2} = (x^2)^{n/2} (假设n为偶数，n为奇数就多乘一次x)
            while (b > 0) {
                // 为奇数多乘一次x
                if ((b & 1) == 1) {
                    res *= x;
                }
                // 执行 x = x^2
                x *= x;
                // 对n右移1位
                b >>= 1;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
