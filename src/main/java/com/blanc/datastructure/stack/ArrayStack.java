package com.blanc.datastructure.stack;

import com.blanc.datastructure.array.Array;

/**
 * 栈的实现类
 *
 * @param <E>
 * @author wangbaoliang
 */
public class ArrayStack<E> implements Stack<E
    /**
     * 动态数组
     */
    private Array<E> array;

    /**
     * 构造函数(传capacity)
     *
     * @param capacity
     */
    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    /**
     * 构造函数(默认capacity)
     */
    public ArrayStack() {
        array = new Array<>();
    }

    /**
     * 入栈
     *
     * @param e
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /**
     * 出栈
     *
     * @return
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 看栈顶的元素
     *
     * @return
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    /**
     * 获取size大小
     *
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 获取容积
     *
     * @return
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 重写toString
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack: ");
        stringBuilder.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            stringBuilder.append(array.get(i));
            if (i != array.getSize() - 1) {
                stringBuilder.append(',');
            } else {
                stringBuilder.append("] top");
            }
        }
        return stringBuilder.toString();
    }
}
