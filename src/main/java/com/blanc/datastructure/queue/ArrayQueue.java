package com.blanc.datastructure.queue;

import com.blanc.datastructure.array.Array;

/**
 * 队列实现
 *
 * @param <E>
 * @author wangbaoliang
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    /**
     * 构造器
     *
     * @param capacity
     */
    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    /**
     * 构造器
     */
    public ArrayQueue() {
        array = new Array<>();
    }

    /**
     * 入队
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队
     *
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队首元素
     *
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    /**
     * 获取队列的size
     *
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 获取容量
     *
     * @return
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue: ");
        stringBuilder.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            stringBuilder.append(array.get(i));
            if (i != array.getSize() - 1) {
                stringBuilder.append(",");
            } else {
                stringBuilder.append("] tail");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue();
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue(i);
            System.out.println(arrayQueue);

            if (i % 3 == 2) {
                arrayQueue.dequeue();
                System.out.println(arrayQueue);
            }
        }
    }
}
