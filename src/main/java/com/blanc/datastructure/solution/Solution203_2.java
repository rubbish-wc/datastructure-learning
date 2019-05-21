package com.blanc.datastructure.solution;

import com.blanc.datastructure.solution.support.ListNode;

/**
 * @author wangbaoliang
 */
public class Solution203_2 {

    /**
     * 删除链表中的所有某个元素(使用虚拟头节点,明显更加的简单了)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head , int val){
        //构建虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null){
            if (prev.next.val == val){
                prev = prev.next.next;
            }else {
                prev = prev.next;
            }
        }
        return head;
    }
}
