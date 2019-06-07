package com.blanc.datastructure.tree;

/**
 * 线段树:基于静态数组实现
 * @author wangbaoliang
 * @param <E>
 */
public class SegmentTree<E> {

    /**
     * 静态数组,用于存放数据
     */
    private E[] data;

    /**
     * 用于存储树结构(满二叉树)
     */
    private E[] tree;

    /**
     * 构造器
     * @param arr
     */
    public SegmentTree(E[] arr){
        data = (E[]) new Object[arr.length];
        for (int i = 0 ; i < arr.length ; i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];

    }

    /**
     * 获取相应索引的元素
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("illegal index number");
        }
        return data[index];
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return 2*index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return 2*index + 2;
    }

    /**
     * 在treeIndex的位置创建表示[l...r]的线段树,分成两段 l -> mid 和( mid + 1 ) -> r
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex , int l , int r){
        //终结条件:说明已经到了最末端
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }
        //如果没有到最末端,继续构建左孩子为根和右孩子为根的线段树
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        //求左右孩子中间值,int mid = (l + r) / 2; 为了防止整型相加溢出,采用以下的写法
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChild, l , mid);
        buildSegmentTree(rightChild, mid + 1 ,r);


    }
}
