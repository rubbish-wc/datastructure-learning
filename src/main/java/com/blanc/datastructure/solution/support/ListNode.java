package com.blanc.datastructure.solution.support;

/**
 * @author wangbaoliang
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr){
        if (arr == null || arr.length == 0){
            throw new IllegalArgumentException("arr cant be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 0 ; i < arr.length ; i++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }
}
