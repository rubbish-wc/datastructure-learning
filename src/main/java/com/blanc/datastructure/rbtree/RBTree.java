package com.blanc.datastructure.rbtree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 红黑树
 * 1 每个节点是红色的或者黑色的
 * 2 根节点是黑色的
 * 3 每一个叶子节点(最后的空节点)是黑色的
 * 4 如果一个节点是红色的,那么他的孩子节点都是黑色的
 * 5 (***)从任意一个节点到任意一个叶子节点,经过的黑色节点都是一样的(***)
 */
public class RBTree<K extends Comparable<K>, V> {

    /**
     * 红色节点
     */
    private static final boolean RED = true;

    /**
     * 黑色节点
     */
    private static final boolean BLACK = false;

    /**
     * 树根
     */
    private Node root;

    /**
     * 元素的数量
     */
    private int size;

    /**
     * 构造函数
     * 跟节点为null,size是0
     */
    public RBTree() {
        root = null;
        size = 0;
    }

    /**
     * 私有内部类,Node
     * 包含一个元素,两个孩子
     */
    private class Node {

        /**
         * 节点key
         */
        public K key;

        /**
         * 节点V
         */
        public V value;

        /**
         * 引用保存左节点和右节点
         */
        public Node left, right;

        /**
         * 红黑树核心
         * true: 红色
         * false: 黑色
         */
        public boolean color;

        /**
         * 节点构造函数
         * @param key
         * @param value
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            //新节点默认红色
            color = RED;
        }
    }

    /**
     * 辅助函数:判断是否是红色节点
     * @param node
     * @return
     */
    private boolean isRed(Node node){
        //空节点是黑色的
        if (node == null){
            return BLACK;
        }
        return node.color;
    }

    /**
     * 判断元素的个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 红黑树的左旋转
     * @param node
     * @return
     */
    private Node leftRotate(Node node){
        Node x = node.right;
        //左旋转
        node.right = x.left;
        x.left = node;

        //维持下节点颜色
        x.color = node.color;
        //表示现在是融合而来的2-3树的3节点
        node.color = RED;
        return x;
    }

    /**
     * 右旋转
     * @param node
     * @return
     */
    private Node rightRotate(Node node){
        //右旋转
        Node x = node.left;
        Node T1 = x.left;
        node.left = T1;
        x.right = node;
        //维护颜色
        x.color = node.color;
        //左右两个节点红色表示是融合的
        node.color = RED;
        return x;
    }

    /**
     * 对一node为根的红黑树进行颜色翻转
     * 要满足一情况,向3节点的元素中添加元素且融合的位置在右边
     * @param node
     */
    private void flipColors(Node node){
        node.color = RED;
        //因为已经解融合了,所以一定是2节点,所以是黑色的
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 添加一个元素
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        add(root,key,value);
        //保持根节点为BLACK的性质
        root.color = BLACK;
    }

    /**
     * 私有递归函数,像以node为根的红黑树树中添加元素,递归算法
     * 返回插入新节点后二分搜索树的根
     * @param node
     */
    private Node add(Node node, K key, V value) {
        //1 递归终止的条件 1)相等(二分搜索树要求数据不重复) 2)小于这个根节点则插入到左子树中,并且左子树中没有元素了 3)大于这个根节点则插入到右子树中,并且这个右子树没有元素了
        if (node == null){
            size++;
            //默认插入红色的节点
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0 ){
            node.left = add(node.left,key,value);
        } else if (key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }
        //重点开始,红黑树性质的维护
        //1 黑
        if (isRed(node.right) && !isRed(node.left)){

        }
        return node;
    }


}
