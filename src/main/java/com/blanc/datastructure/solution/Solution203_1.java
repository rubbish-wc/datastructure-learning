package com.blanc.datastructure.solution;

import com.blanc.datastructure.solution.support.ListNode;

/**
 * 203 删除链表中的一个元素
 *
 * @author wangbaoliang
 */
public class Solution203_1 {

    /**
     * 删除链表中的元素,不使用虚拟头节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        //排除掉所有头结点就要删除,并且下一个还是头节点的情况
        if (head != null && head.val == val) {
            head = head.next;
        }
        //如果一个都不剩了,不用后面的判断逻辑,直接处理掉
        if (head == null) {
            return head;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }
}
