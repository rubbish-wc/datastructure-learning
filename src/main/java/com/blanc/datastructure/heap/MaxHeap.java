package com.blanc.datastructure.heap;

import com.blanc.datastructure.array.Array;

/**
 * 最大堆实现(动态数据实现 从索引0开始)
 * @author wangbaoliang
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    /**
     * 获取堆中元素的数量
     * @return
     */
    public int size(){
        return data.getSize();
    }

    /**
     * 判断堆是否为空
     * @return
     */
    public boolean isEmpty(){
        return data.getSize() == 0;
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的父亲节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index-1)/2;
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的左孩子的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的右孩子的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return index * 2 + 2;
    }


    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e){
        //先向数组尾添加元素
        data.addLast(e);
        //只是添加了还不够,还需要满足堆的性质,所以这个做个函数去处理
        siftUp(data.getSize() - 1);

    }

    /**
     * 向堆中添加元素
     * @param k
     */
    private void siftUp(int k){
        //如果不是根节点并且这个元素大于父亲节点的元素,说明需要继续上浮,即替换和父亲元素
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            //交换父节点的元素和自己
            data.swap(k,parent(k));
            //更新k为父节点,下次循环继续比较
            k = parent(k);
        }
    }

    /**
     * 取出堆中的最大元素
     * @return E
     */
    public E extractMax(){
        //获取堆顶元素(最大值)
        E ret = findMax();
        //将数组的最后一位元素和堆顶(第一个元素,最大值)交换,交换完后删除最后一个元素
        data.swap(0,data.getSize()-1);
        data.removeLast();
        //为了保持堆的属性,用siftDown处理一下(从堆顶开始)
        siftDown(0);
        return ret;
    }

    /**
     * 保持堆的属性,将堆顶的元素(不满足最大的性质进行下沉到正确的位置)
     * @return
     */
    private void siftDown(int k){
        //最极端的情况,左孩子都已经数组越界了,说明一定是没有孩子了(左孩子索引一定是比右孩子小的)
        while (leftChild(k) >= data.getSize()){
            //先保存左孩子的索引
            int j = leftChild(k);
            //如果有右孩子,且右孩子比左孩子要大
            if ((j + 1) < data.getSize() && data.get(j+1).compareTo(data.get(j)) > 0){
                //data[j]是leftChild 和 rightChild 中的最大值
                j = rightChild(k);
            }
            //如果k比可能的孩子还要小,说明要交换位置,并且继续循环
            if (data.get(k).compareTo(data.get(j)) >= 0){
                break;
            }else {
                //交换k和j的元素
                data.swap(k,j);
                //更新索引位置进行下一次的循环
                k = j;
            }
        }
    }


    /**
     * 看一下堆中的最大值
     * @return
     */
    public E findMax(){
        if (data.getSize() == 0){
            throw new IllegalArgumentException("can't find max when the heap is empty");
        }
        return data.get(0);
    }
}
