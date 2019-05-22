package com.blanc.datastructure.set;

import com.blanc.datastructure.tree.BST;

/**
 * 基于二分搜索树实现的Set集合
 * @param <E>
 */
public class BSTSet<E extends Comparable<E>> implements Set<E>{

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    /**
     * 添加元素
     * @param e
     */
    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
