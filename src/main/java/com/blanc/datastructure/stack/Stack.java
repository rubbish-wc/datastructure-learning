package com.blanc.datastructure.stack;

/**
 * 栈接口
 * @author wangbaoliang
 */
public interface Stack<E> {

    /**
     * 入栈
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 查看栈顶的元素
     * @return
     */
    E peek();

    /**
     * 获取栈的大小
     * @return
     */
    int getSize();

    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();


}
