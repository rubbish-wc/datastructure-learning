package com.blanc.datastructure.set;

import com.blanc.datastructure.linkedlist.LinkedList;

/**
 * 基于链表实现的集合
 * @author wangbaoliang
 */
public class LinkedListSet<E> implements Set<E>{

    /**
     * 私有链表,用于存储数据
     */
    private LinkedList<E> linkedList;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (linkedList.contains(e)){
            return;
        }else {
            //在链表头添加一个元素,时间复杂度是o(1)
            linkedList.addFirst(e);
        }
    }

    @Override
    public E remove(E e) {
        linkedList.removeElement(e);
        return e;
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }
}
