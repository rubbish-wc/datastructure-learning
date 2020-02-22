package com.blanc.datastructure.avl;

import com.blanc.datastructure.map.Map;

import java.util.ArrayList;

/**
 * AVL二分搜索树映射
 * 平衡二叉树,左右子树的高度差不超过1,即平衡因子不超过1
 * 在什么时候维护平衡:加入节点后,沿着节点向上维护平衡性
 * @param <K>
 * @param <V>
 */
public class AvlTree<K extends Comparable<K>, V> implements Map<K , V> {

    /**
     * 根节点
     */
    private Node root;

    /**
     * 节点树
     */
    private int size;

    /**
     * 构造函数
     */
    public AvlTree(){
        root = null;
        size = 0;
    }

    /**
     * 节点
     */
    private class Node{

        /**
         * 映射的key
         */
        public K key;

        /**
         * 映射的value
         */
        public V value;

        /**
         * avl重点:这个节点的高度值
         */
        public int height;

        /**
         * 左右孩子
         */
        public Node left,right;

        /**
         * 节点构造函数,初始化高度为1,因为用到构造函数说明在添加节点
         * 而添加一个节点一定是叶子节点,叶子节点的高度一定是定义为1的
         * @param key
         * @param value
         */
        public Node(K key , V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    /**
     * 辅助函数:获取一个节点的高度值
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 获取node节点的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }else {
            return getHeight(node.left) - getHeight(node.right);
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
        //重点,更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //如果平衡因子大于1,说明不是一个平衡二叉树
        if (Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced:" + balanceFactor);
            //此处维护平衡性
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

    /**
     * 辅助函数判断该二叉树是否是一颗二分搜索树
     * 原理:二分搜索树的中序遍历得到的结果是升序的,如果不是,则有问题
     * @return
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for (int i = 1 ; i < keys.size() ; i++){
            if (keys.get(i-1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 辅助函数:判断是不是一个平衡二叉树
     * 原理:每一个节点的左右子树的高度差不能大于1
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 判断以node为根节点的二叉树是否是一棵平衡二叉树
     * @param node
     * @return
     */
    private boolean isBalanced(Node node){
        if (node == null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1){
            return false;
        }else {
            return isBalanced(node.left) && isBalanced(node.right);
        }
    }

    /**
     * 中序遍历一个根节点为node的元素
     * @param node
     * @param keys
     */
    private void inOrder(Node node,ArrayList<K> keys){
        if (node == null){
            return;
        }
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
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
