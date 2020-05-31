package com.blanc.datastructure.heapreview;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 最大堆
 *
 * @author wangbaoliang
 */
public class MaxHeap<E extends Comparable<E>> {

    /**
     * 存储元素
     */
    private ArrayList<E> data;

    /**
     * 构造器
     *
     * @param capacity 堆的容量
     */
    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    /**
     * 构造器
     */
    public MaxHeap() {
        data = new ArrayList<>();
    }

    /**
     * heapify使数组堆化
     * 从最后一个非叶子节点开始,往下siftDown
     * 堆化的思路:
     * 1 直接遍历全部数组,然后一个一个add,结果是nlogn
     * 2 从最后一个非叶子节点开始,往下siftDown o(n),推导比较复杂,有印象即可
     *
     * @param es 数组
     */
    public MaxHeap(E[] es) {
        //先放置到堆中
        data = new ArrayList<>(es.length);
        data.addAll(Arrays.asList(es));
        //求最后一个非叶子节点
        int start = parent(es.length - 1);
        while (start >= 0){
            siftDown(start);
            start--;
        }
    }

    /**
     * 返回堆中元素的个数
     *
     * @return
     */
    public int size() {
        return data.size();
    }

    /**
     * 返回一个布尔值,表示堆中是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的父亲节点的索引
     *
     * @param index 索引
     * @return
     */
    private int parent(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("invalid index");
        }
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent node");
        }
        return (index - 1) / 2;
    }

    /**
     * 获取一个索引的左孩子索引
     *
     * @param index 索引
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 获取一个索引的右孩子节点
     *
     * @param index 索引
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 像堆中添加一个元素
     * siftUp: 上浮
     *
     * @param e 要添加的元素
     */
    public void add(E e) {
        //添加元素,然后siftUp上浮
        data.add(e);
        siftUp(data.size() - 1);
    }

    /**
     * 看下堆中的最大的元素
     *
     * @return e
     */
    public E peek() {
        if (data.size() == 0) {
            throw new IllegalArgumentException("heap is empty");
        }
        return data.get(0);
    }

    /**
     * 取出最大的元素并插入一个新的元素
     *
     * @param e 新插入的元素
     * @return 最大e
     */
    public E replace(E e) {
        //取出最大的元素
        E tem = peek();
        data.set(0, e);
        siftDown(0);
        return tem;
    }

    /**
     * 提取出最大的元素
     *
     * @return e 最大的元素
     */
    public E extractMax() {
        //最大的元素在0索引位置上(根据堆的定义)
        E result = data.get(0);
        //将最后一个元素放置到堆顶并删除
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);
        siftDown(0);
        return result;
    }

    /**
     * 上浮一个元素
     *
     * @param index 上浮索引
     */
    private void siftUp(int index) {
        //当添加的元素比父亲元素还要大,要做交换或者已经交换到了根节点就停下来
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            //交换父子元素
            E tem = data.get(index);
            data.set(index, data.get(parent(index)));
            data.set(parent(index), tem);
            //将index指向父亲节点,继续下一次循环
            index = parent(index);
        }
    }

    /**
     * 下沉根元素
     *
     * @param index 索引
     */
    private void siftDown(int index) {
        //如果index的左孩子还没有越界,说明index节点一定有左孩子
        while (leftChild(index) < data.size()) {
            //j保存左右孩子中比较大的那个索引
            int j = leftChild(index);
            //如果有右节点的话,比较并取出左右节点中比较大的那个
            if (j + 1 < data.size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(index);
            }
            //如果index 小于 data(j),交换index和j,data(j)是左右孩子中的最大值,反之则说明siftDown完成,跳出循环
            if (data.get(index).compareTo(data.get(j)) < 0) {
                E tem = data.get(j);
                data.set(j, data.get(index));
                data.set(index, tem);
                index = j;
            } else {
                break;
            }
        }
    }


}
