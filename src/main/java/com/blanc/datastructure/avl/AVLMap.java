package com.blanc.datastructure.avl;

import com.blanc.datastructure.map.Map;

/**
 * 基于AVL实现的map
 * @param <K>
 * @param <V>
 */
public class AVLMap<K extends Comparable<K>,V> implements Map<K,V> {

    private AvlTree<K,V> avl;

    public AVLMap() {
        avl = new AvlTree<>();
    }

    @Override
    public void add(K key, V value) {
        avl.add(key,value);
    }

    @Override
    public V remove(K key) {
        return avl.remove(key);
    }

    @Override
    public boolean contains(K k) {
        return avl.contains(k);
    }

    @Override
    public V get(K key) {
        return avl.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        avl.set(key, newValue);
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
