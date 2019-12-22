package com.blanc.datastructure.linkedlist;

/**
 * 链表栈测试类
 * @author wangbaoliang
 */
public class LinkedListStackTest {

    public static void main(String[] args) {
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        for (int i = 0 ; i < 7 ; i++){
            linkedListStack.push(i);
        }
        System.out.println(linkedListStack);
        linkedListStack.pop();
        System.out.println(linkedListStack);
    }



}
