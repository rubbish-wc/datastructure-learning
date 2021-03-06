package com.blanc.datastructure.avl;

import com.blanc.datastructure.map.Map;

import java.util.ArrayList;

/**
 * AVL二分搜索树映射
 * 最为经典的平衡二叉树(Adelson-Velsky 和 Landis 两个人的名字)
 * 最早的自平衡二分搜索树结构
 * 什么是平衡二叉树?
 * 平衡二叉树,对于任意一个节点,左右子树的高度差不超过1,即平衡因子不超过1(平衡因子:指一个节点的左右子树的高度差,|左height-右height\),这个平衡二叉树看起来可能不这么平衡
 * 平衡二叉树的高度和节点数量之间的关系也是O(logN)的
 * 满二叉树(除了叶子节点,所有的其他节点都有两个子树)一定是一个平衡二叉树
 * 完全二叉树:(个人理解) 除了最深的一层外,其余层都铺满,且最后一层按照从左到右不间断排列开.完全二叉树是平衡二叉树,因为整个树的高度查不会大于1,我认为最无聊的线段树也是一个平衡二叉树
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
         * 标注节点的高度
         * avl重点:这个节点的高度值
         * 这个高度不是整体来看的,以每个叶子节点为开始标注高度1,一个非叶子节点的高度是他孩子节点中高度最高的节点的高度+1
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
        //如果一个node本身是null的话,它的高度算为0
        if (node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 获取node节点的平衡因子
     * 终于知道这个平衡因子为什么不直接取绝对值了,因为要用来给左右旋转的依据做准备
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
     * AVL树的删除
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 辅助函数:删除AVL中以Node为根的树中的一个节点
     * @param node 根节点
     * @param key 要删除的K
     * @return
     */
    private Node remove(Node node, K key){
        //递归终止条件,到底了,说明没得删了
        if (node == null){
            return null;
        }
        //删除以后,我们需要返回的新的ret的节点
        Node retNode;
        //如果要删除的元素比根节点要小,则往左子树中删除
        if (node.key.compareTo(key) > 0){
            node.left = remove(node.left,key);
            retNode = node;
        }else if (node.key.compareTo(key) < 0){
            node.right = remove(node.right,key);
            retNode = node;
        }else {//这个节点就是要删除的节点的话
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }
            //如果左右子树都不为空的情况,找到比待删除节点大的最小的节点,用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            //因为remove操作也要维护自平衡,所以这里和二分搜索树的removeMin不同,要使用有自平衡的删除方式
            successor.right = remove(node.right,successor.key);
            successor.left = node.left;
            //断掉
            node.left = node.right = null;
            retNode = successor;
        }
        //边界情况,如果删了的本身就是一个叶子节点,那么返回的可能是个null,后面会空指针
        if (retNode == null){
            return null;
        }
        //关键,要判断维护ret节点的平衡
        //1 计算retNode的高度值,左右子树的最大高度 + 1;
        retNode.height = Math.max(getHeight(retNode.left),getHeight(retNode.right)) + 1;
        //2 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        //3 开始旋转
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            return rightRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            //1 先对x进行左旋转,你要在脑海中想象出来这个场景,不行就画图
            retNode.left = leftRotate(node.left);
            return rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(node.right);
            return leftRotate(retNode);
        }
        return retNode;
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
            //如果相等了,更新value值
            node.value = value;
        }
        //重点,更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //重点,计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //如果平衡因子大于1,说明不是一个平衡二叉树
        if (Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced:" + balanceFactor);
            //重点就在于这个地方怎么维护平衡性,判断是否需要维护平衡的条件至关重要
            //1:LL 如果此node的平衡因子大于1(左侧不平衡),且左子树的平衡因子>=0
            if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
                return rightRotate(node);
            }
            //2:LR
            if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
                //2.1 对左孩子进行一次左旋转变成LL
                node.left = leftRotate(node.left);
                //2.2 对当前节点右旋转
                return leftRotate(node);
            }
            //2:RR 如果此node的平衡因子小于-1(右侧不平衡),且右子树的平衡因子<=0
            if (balanceFactor < -1 && getBalanceFactor(node.left) <= 0){
                return leftRotate(node);
            }
            //4:RL
            if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
                //对右孩子先进行一次右旋转变成RR
                node.right = leftRotate(node.right);
                //对当前节点进行左旋转
                return  leftRotate(node);
            }

        }
        return node;
    }

    /**
     * 右旋转
     * @param y 不满足平衡性的节点
     * @return 旋转后满足了平衡性的新的节点
     *                y                              x
     *              /  \                           /   \
     *             x   T4         向右旋转         z     y
     *            / \       --------------->    / \    / \
     *           z  T3                         T1  T2 T3 T4
     *          / \
     *        T1  T2
     */
    private Node rightRotate(Node y){
        //按图取后面要用到的节点
        Node x = y.left;
        Node T3 = x.right;
        //向右旋转过程
        x.right = y ;
        y.left = T3;
        //更新height值,先更新y,再更新x,因为旋转后,x的高度值依赖y的高度值
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 左旋转
     * @param y
     * @return
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
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
