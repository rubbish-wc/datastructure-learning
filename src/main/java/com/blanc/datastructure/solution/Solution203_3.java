package com.blanc.datastructure.solution;

import com.blanc.datastructure.solution.support.ListNode;

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


}
