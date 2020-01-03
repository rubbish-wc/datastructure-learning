package com.blanc.datastructure.queue;

import com.blanc.datastructure.heap.MaxHeap;

/**
 * 基于最大堆实现的优先队列
 * 有点队列的经典问题,100w个元素中如何选出前100名
 * 抽象出来就是 在N个元素中选出前M个元素,其中M是<<<N的
 * 排序? NlogN 可以接受,但是多排了,因为M<<<N的
 * 使用优先队列 NlogM 使用优先队列,维护当前看到的前M个元素,使用最小堆
 *
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
