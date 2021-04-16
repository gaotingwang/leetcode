package com.gtw.leetcode.editor.cn;

//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 链表 
// 👍 135 👎 0

/**
 * 从尾到头打印链表
 *
 * @author gtw
 */
public class CongWeiDaoTouDaYinLianBiaoLcof {

    public static void main(String[] args) {
        Solution solution = new CongWeiDaoTouDaYinLianBiaoLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int[] reversePrint(ListNode head) {
            // 法一：采用栈的思想
            // 即先进后出，也可以考虑递归思想
            int size = 0;
            ListNode first = head;
            while (head != null) {
                size++;
                head = head.next;
            }
            int[] nums = new int[size];
            recursion(head, nums, size);
            return nums;
        }

        public void recursion(ListNode node, int[] nums, int index) {
            if (node == null) {
                return;
            }
            nums[--index] = node.val;
            recursion(node.next, nums, index);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
