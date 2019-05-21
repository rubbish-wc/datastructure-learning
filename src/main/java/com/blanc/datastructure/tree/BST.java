package com.blanc.datastructure.tree;

import sun.tools.tree.InstanceOfExpression;

/**
 * 二分搜索树
 * @author wangbaoliang
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    /**
     * 私有内部类,Node
     */
    private class Node{

        public E e;

        public Node left;

        public Node right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 树根
     */
    private Node root;

    /**
     * 元素的数量
     */
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    /**
     * 判断元素的个数
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 像树中添加一个元素,递归写法
     * @param e
     */
    public void add(E e){
        //如果根节点为null,则直接添加
        if (root == null){
            root = new Node(e);
            size++;
        }else {
            add(root,e);
        }
    }

    /**
     * 私有递归函数,像以node为根的二分搜索树中添加元素,递归算法
     * @param node
     * @param e
     */
    private void add(Node node , E e){
        //1 递归终止的条件 1)相等(二分搜索树要求数据不重复) 2)小于这个根节点则插入到左子树中,并且左子树中没有元素了 3)大于这个根节点则插入到右子树中,并且这个右子树没有元素了
        if (e.equals(node.e)){
            return;
        }else if (e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size++;
            return;
        }else if (e.compareTo(node.e) > 0 && node.right == null){
            node.right = new Node(e);
            size++;
            return;
        }

        //不满足终止条件则继续调用
        if (e.compareTo(node.e) < 0){
            add(node.left,e);
        }else if (e.compareTo(node.e) > 0){
            add(node.right,e);
        }
    }

    /**
     * 判断该二分搜索树中是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        return contains(root,e);
    }

    /**
     * 看以node为根的二分搜索树中是否包含元素e,递归算法
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node , E e){
        //终止情况
        if (node == null){
            return false;
        }
        if (e.compareTo(node.e) == 0){
            return true;
        }else if (e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }else{
            return contains(node.right,e);
        }
    }

    /**
     * 二分搜索树的前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 以root为根节点,进行前序遍历
     * @param node
     */
    private void preOrder(Node node){
        //递归终止条件
        if (node == null){
            return;
        }

        //递归的逻辑
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历 二分搜索数使用中序遍历就是得到的就是排序后的结果
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 以node为根进行中序遍历
     * @param node
     */
    private void inOrder(Node node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }


    /**
     * 后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     * 后序遍历
     * @param node
     */
    private void postOrder(Node node){
        if(node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }



    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    /**
     * 生成BST
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node , int depth , StringBuilder res){
        if (node == null){
        }

    }


}
