package com.blanc.datastructure.avl;

import com.blanc.datastructure.set.Set;

/**
 * 基于AVL实现的Set
 * @param <E>
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {

    private AvlTree<E, Object> avlTree;

    public AVLSet() {
        this.avlTree = new AvlTree<>();
    }

    /**
     * 用不到value,直接给null
     * @param e
     */
    @Override
    public void add(E e) {
        avlTree.add(e, null);
    }

    @Override
    public E remove(E e) {
        if (avlTree.contains(e)){
            remove(e);
            return e;
        }else {
            return null;
        }
    }

    @Override
    public boolean contains(E e) {
        return avlTree.contains(e);
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }

    @Override
    public int getSize() {
        return avlTree.getSize();
    }
}
