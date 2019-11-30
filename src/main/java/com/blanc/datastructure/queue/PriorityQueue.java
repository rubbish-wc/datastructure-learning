package com.blanc.datastructure.queue;

import com.blanc.datastructure.heap.MaxHeap;

/**
 * 基于最大堆实现的优先队列
 *
 * @param <E>
 * @author wangbaoliang
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    /**
     * 入队操作
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    /**
     * 出队操作
     *
     * @return
     */
    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    /**
     * 看看队首的元素
     *
     * @return
     */
    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    /**
     * 获取优先队列的size
     *
     * @return
     */
    @Override
    public int getSize() {
        return maxHeap.size();
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
