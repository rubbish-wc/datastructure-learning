package com.blanc.datastructure.solution;

import com.blanc.datastructure.solution.support.ListNode;

import java.util.List;

/**
 * @author wangbaoliang
 */
public class Solution203_3 {

    /**
     * 采用递归的方式删除链表中的元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode res = removeElements(head.next, val);
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,9,6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode res = new Solution203_3().removeElements(head,6);
        System.out.println(res);
    }


}
