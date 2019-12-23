package com.blanc.datastructure.stack;

import java.util.Random;

/**
 * 栈接口
 * 栈是一种后进先出的数据结构
 * List In First Out (LIFO)
 * 在计算机的世界里,栈有着不可思议的应用
 * @author wangbaoliang
 */
public interface Stack<E> {

    /**
     * 入栈
     *
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     *
     * @return
     */
    E pop();

    /**
     * 查看栈顶的元素
     *
     * @return
     */
    E peek();

    /**
     * 获取栈的大小
     *
     * @return
     */
    int getSize();

    /**
     * 判断栈是否为空
     *
     * @return
     */
    boolean isEmpty();


}
