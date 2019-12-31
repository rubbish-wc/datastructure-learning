package com.blanc.datastructure.set;

import com.blanc.datastructure.tree.BST;

/**
 * 基于二分搜索树实现的集合
 * 集合的典型应用:客户统计,词汇量统计
 * @param <E>
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    /**
     * 二分搜索树
     */
    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    /**
     * 添加元素,不可添加重复的元素
     *
     * @param e
     */
    @Override
    public void add(E e) {
        bst.add(e);
    }

    /**
     * 删除一个元素
     *
     * @param e
     * @return
     */
    @Override
    public E remove(E e) {
        return bst.remove(e);
    }

    /**
     * 判断是否包含
     *
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    /**
     * 获取集合的大小
     *
     * @return
     */
    @Override
    public int getSize() {
        return bst.size();
    }
}
