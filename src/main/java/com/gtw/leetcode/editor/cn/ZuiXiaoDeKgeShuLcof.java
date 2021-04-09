package com.gtw.leetcode.editor.cn;

//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法 
// 👍 223 👎 0

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小的k个数
 *
 * @author gtw
 */
public class ZuiXiaoDeKgeShuLcof {

    public static void main(String[] args) {
        Solution solution = new ZuiXiaoDeKgeShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (k >= arr.length) {
                return arr;
            }
            // 法一：借助快排思想，k位之前的数都arr[k]小，k位之后的数都arr[k]大
            // return withQuickSort(arr, k, 0, arr.length - 1);

            // 法二：堆思想
            // Java 的 PriorityQueue 默认是小顶堆，添加 comparator 参数使其变成最大堆
            Queue<Integer> heap = new PriorityQueue<Integer>(
                new Comparator<Integer>() {
                    public int compare(Integer num1, Integer num2) {
                        return num2 - num1;
                    }
                });
            for (int num : arr) {
                if (heap.isEmpty() || heap.size() < k || num < heap.peek()) {
                    heap.add(num);
                }
                if (heap.size() > k) {
                    heap.poll();
                }
            }

            int[] result = new int[k];
            int i = 0;
            for (int e : heap) {
                result[i++] = e;
            }
            return result;
        }

        /**
         * 法一：借助快排思想，k位之前的数都arr[k]小，k位之后的数都arr[k]大
         */
        public int[] withQuickSort(int[] arr, int k, int low, int high) {
            int i = low;
            int j = high;
            int mid = arr[low];
            while (i < j) {
                while (i < j && arr[j] >= mid) {
                    // 从右向左找第一个小于x的数
                    j--;
                }
                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }
                while (i < j && arr[i] <= mid) {
                    // 从左向右找第一个大于x的数
                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            arr[i] = mid;
            if (i > k) {
                return withQuickSort(arr, k, low, i - 1); // 递归调用
            }
            if (i < k) {
                return withQuickSort(arr, k, i + 1, high);
            }
            return Arrays.copyOf(arr, k);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
