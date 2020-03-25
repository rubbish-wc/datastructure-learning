package com.blanc.datastructure.queue;

import com.blanc.datastructure.heap.MaxHeap;

/**
 * 普通队列: 先进先出,后进后出
 * 优先队列: 出队顺序和入队顺序无关;和优先级相关
 * 为什么使用优先队列:  操作系统中: 动态选择优先级最高的任务执行(任务处理中心) LOL防御塔攻击(优先哪个英雄?或者小兵)
 * 优先队列的底层选择:
 *     普通线程结构                                入队O(1)      出队O(n) 要扫描出最大值  n次入队操作就是O(n^2)
 *     顺序线性结构(一直从小/大 或者大/小排序)        入队O(n)      出队O(1) 已经排序了,取最大值直接取
 *     堆                                         入队O(logN)   出队O(logn) 最差情况下O(logn)
 *
 * 基于最大堆实现的优先队列
 * 优先队列的经典问题,100w个元素中如何选出前100名(即TOP k 问题)
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
