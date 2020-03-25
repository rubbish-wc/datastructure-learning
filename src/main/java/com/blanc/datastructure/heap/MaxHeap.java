package com.blanc.datastructure.heap;

import com.blanc.datastructure.array.Array;

/**
 * 最大堆实现(动态数据实现 从索引0开始)
 * 重点在于搞清楚父子节点之间的索引关系
 * 堆是完全二叉树(即一层一层的从左往右去排列),这样子可以在不浪费数组容量的情况下用数组去存储这个堆
 * 为什么堆是完全二叉树? 因为存放在数组中,是往数组尾部添加的 ,这么分析对么
 * 最大堆的每一个父亲节点总是大于他的孩子节点
 * 重要的辅助函数: 搞清楚左孩子和右孩子和父节点的索引的关系
 * 父亲节点从1 开始 计算比较方便 直接 /2 就行了 如果从0开始计算, 则关系是 (i-1)/2  直接手画一下就非常清楚了
 * 例: 如何求100w中的数字的最大值,或者从大到小排序? 用最大堆即可,不断地extractMax即可,堆排序大概就是这样
 * 完全二叉树和普通的二叉树不一样,是不会退化成链表的,所以时间复杂度很稳定,都是logn
 * @param <E>
 * @author wangbaoliang
 */
public class MaxHeap<E extends Comparable<E>> {

    /**
     * 最大堆使用数组来存储,这里我们使用我们之前已经实现的动态数据
     */
    private Array<E> data;

    /**
     * 构造函数
     * @param capacity
     */
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * 构造函数
     */
    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 获取堆中元素的数量
     *
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 判断堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.getSize() == 0;
    }

    /**
     * 辅助函数:返回完全二叉树的数组表示中,一个索引所表示的元素的父亲节点的索引
     * 第一个元素从0开始,楞吗,直接从1开始不是更方便用来计算么
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        //求父节点的索引公式
        return (index - 1) / 2;
    }

    /**
     * 辅助函数: 返回完全二叉树的数组表示中,一个索引所表示的元素的左孩子的索引
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 辅助函数: 返回完全二叉树的数组表示中,一个索引所表示的元素的右孩子的索引
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }


    /**
     * 向堆中添加元素
     *
     * @param e
     */
    public void add(E e) {
        //先向数组尾添加元素
        data.addLast(e);
        //只是添加了还不够,还需要满足堆的性质,所以这个做个函数去处理,叫做上浮
        siftUp(data.getSize() - 1);

    }

    /**
     * 向堆中添加元素
     * 需要做siftup的元素的索引,数组的最后一个元素
     * @param k
     */
    private void siftUp(int k) {
        //如果不是根节点并且这个元素大于父亲节点的元素,说明需要继续上浮,即替换和父亲元素
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            //交换父节点的元素和自己
            data.swap(k, parent(k));
            //更新k为父节点,下次循环继续比较
            k = parent(k);
        }
    }

    /**
     * 取出堆中的最大元素
     *
     * @return E
     */
    public E extractMax() {
        //获取堆顶元素(最大值)
        E ret = findMax();
        //将数组的最后一位元素和堆顶(第一个元素,最大值)交换,交换完后删除最后一个元素
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        //为了保持堆的属性,用siftDown处理一下(从堆顶开始)
        siftDown(0);
        return ret;
    }

    /**
     * 保持堆的属性,将堆顶的元素(不满足最大的性质进行下沉到正确的位置)
     * 从索引0开始
     * @return
     */
    private void siftDown(int k) {
        //最极端的情况,左孩子都已经数组越界了,说明一定是没有孩子了(左孩子索引一定是比右孩子小的)
        while (leftChild(k) < data.getSize()) {
            //先保存左孩子的索引
            int j = leftChild(k);
            //如果有右孩子,且右孩子比左孩子要大
            if ((j + 1) < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                //data[j]是leftChild 和 rightChild 中的最大值
                j = rightChild(k);
            }
            //如果k比可能的孩子还要小,说明要交换位置,并且继续循环
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            } else {
                //交换k和j的元素
                data.swap(k, j);
                //更新索引位置进行下一次的循环
                k = j;
            }
        }
    }


    /**
     * 看一下堆中的最大值
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("can't find max when the heap is empty");
        }
        return data.get(0);
    }

    /**
     * 取出堆中的最大元素,并替换成元素e,logn级别
     * 注意不是替换任意元素,而是最大的元素
     * @param e
     * @return
     */
    public E replace(E e) {
        //保存最大元素
        E ret = findMax();
        //将最大元素设置成要替换的元素e,再进行siftDown
        data.set(0, e);
        //维护堆的性质
        siftDown(0);
        return ret;
    }

    /**
     * 构造函数,将数组自动转换成一最大堆
     * heapify:将任意的数组转换成一个堆的形状,比如最大堆
     * 从倒数第一个非叶子节点开始,siftdown就行了,why?
     * 关键在于如何定位倒数第一个非叶子节点
     * 怎么做,可以找到最后一个索引,然后求他的父亲节点,这个父亲节点就是整个堆的倒数第一个非叶子节点
     * 将n个元素逐个插入到一个空堆中,算法复杂度是O(nlogn)
     * heaplify的过程,算法复杂度是O(n),课程中没有进行解释,比较复杂,自己有个印象就行了
     * @param arr
     */
    public MaxHeap(E[] arr) {
        //先转换成动态数组
        data = new Array<>(arr);
        //进行heapify,从最后一个非叶子节点开始,便利到堆顶进行heapify操作
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

}
