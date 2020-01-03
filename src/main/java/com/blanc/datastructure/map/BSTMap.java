package com.blanc.datastructure.map;

import java.security.Key;

/**
 * 基于二分搜索树实现的Map
 * @param <K>
 * @param <V>
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K , V> {

    private Node root;

    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }


    private class Node{

        public K key;

        public V value;

        public Node left,right;

        public Node(K key , V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    /**
     * 向根为root的子树中添加元素
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node , K key , V value){
        //如果node == null,说明已经到底了
        if (node == null){
            size++;
            return new Node(key,value);
        }
        if (key.compareTo(node.key) < 0 ){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else {
            node.value = value;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if (node != null){
            root = remove(root,key);
            return node.value;
        }else {
            return null;
        }
    }

    /**
     * 删除以node为根节点的二分搜索树中的key
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node , K key){
        //如果递归到底,肯定是没有找到
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            return node;
        }else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            return node;
        }else {
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + "doesn't exist");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回以node为根的二分搜索树种的key的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node,K key){
        //如果遍历到尾了还没有,说明就是没有
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) > 0){
            return getNode(node.right,key);
        }else if (key.compareTo(node.key) < 0){
            return getNode(node.left,key);
        }else {
            return node;
        }
    }

    /**
     * 返回以node为根的二分搜索树中的最小的节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if (node.left != null){
            return minimum(node.left);
        }else {
            return node;
        }
    }

    /**
     * 返回以node为根节点的二分搜索树中的最大的节点
     * @param node
     * @return
     */
    private Node maximum(Node node){
        //终止条件,说明这个就是最大的节点了
        if (node.right == null){
            return node;
        }else {
            return maximum(node.right);
        }
    }


    private Node removeMin(Node node){
        if (node.left == null){
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

}
