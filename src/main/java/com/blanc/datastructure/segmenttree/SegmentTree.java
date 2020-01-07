package com.blanc.datastructure.segmenttree;

/**
 * 最经典的线段树问题:区间染色
 * 有一面墙,长度为n,每次选一段墙染色(可以覆盖),m次操作后,我们可以看见多少中颜色?m次操作后我们可以在区间[i,j]内看到多少中颜色?
 * 另一类经典问题:区间查询
 * 查询一个区间[i,j]的最大值,最小值,或者区间数字和,实质:基于区间的统计查询
 * 线段树不是完全二叉树
 * 线段树是平衡二叉树
 * 完全二叉树是平衡二叉树
 * 线段树虽然不是完全二叉树,但是依旧可以用数组表示,可以看成是一个满二叉树,只是最后一层很多节点要看成null
 * 如果区间有n个元素,数组表示需要有多少个节点
 * @param <E>
 */
public class SegmentTree<E> {

    /**
     * 存储元素
     */
    private E[] data;

    /**
     * 存储线段树
     */
    private E[] tree;

    /**
     * 构造函数
     * @param arr
     */
    public SegmentTree(E[] arr) {
        //初始化数据
        data = (E[])new Object[arr.length];
        for (int i = 0 ; i < arr.length ; i++){
            data[i] = arr[i];
        }
        //初始化线段树
        tree = (E[]) new Object[arr.length * 4];
        /** 线段树的组装过程*/

    }

    /**
     * 获取一个元素
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("invalid index");
        }
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    /**
     * 辅助函数:返回一个index的左孩子的index
     * @param index
     * @return
     */
    private int leftChild(int index){
        return 2 * index + 1;
    }

    /**
     * 辅助函数:返回一个index的右孩子的index
     * @param index
     * @return
     */
    private int rightChild(int index){
        return 2 * index + 2;
    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r ){
        //终止条件,左边界==右边界,没得分了,递归终止
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
    }

}
