package com.blanc.datastructure.set;

import com.blanc.datastructure.tree.BST;

/**
 * 基于二分搜索树实现的集合
 * @param <E>
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    /**
     * 二分搜索树
     */
    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public E remove(E e) {
        return bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public int getSize() {
        return bst.size();
    }
}
