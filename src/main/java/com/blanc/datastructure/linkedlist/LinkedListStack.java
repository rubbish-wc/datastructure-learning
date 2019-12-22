package com.blanc.datastructure.linkedlist;

import com.blanc.datastructure.stack.Stack;

/**
 * 使用链表实现的栈
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E> {

    /**
     * 内部维护的链表
     */
    private LinkedList<E> linkedList;

    /**
     * 构造函数
     */
    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    /**
     * 压榨
     * @param e
     */
    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    /**
     * 出栈
     * @return
     */
    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    /**
     * 查看栈顶元素
     * @return
     */
    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    /**
     * 获取大小
     * @return
     */
    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    /**
     * 判断是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack : top ");
        stringBuilder.append(linkedList);
        return stringBuilder.toString();
    }
}
