package com.blanc.datastructure.tree;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import com.sun.tools.internal.xjc.generator.bean.field.NoExtendedContentField;
import sun.security.provider.certpath.SunCertPathBuilder;

import javax.sound.sampled.ReverbType;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

        /**
         * 节点内容
         */
        public E e;

        /**
         * 引用保存左节点
         */
        public Node left;

        /**
         * 引用保存右节点
         */
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
     * @param node 根节点
     * @param e 元素
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
     * 非递归的方式完成前序遍历(使用栈)
     * 二分搜索树的非递归实现,只能借助栈,比递归实现复杂的多
     * @param node
     */
    private void preOrderNR(Node node){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二分搜索树的层序遍历(广度优先遍历)
     */
    public void levelOrder(){
        //借用链表实现的队列去实现
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //获取当前要出队的元素
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    /**
     * 寻找二分搜索树的最小元素
     * @return
     */
    public E minimum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }

    /**
     * 返回以node为节点的二分搜索树的最小值所在的节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        //终止条件 (如果没有左节点了,说明这个node就是最小的)
        if (node.left == null){
            return node;
        }else {
            return minimum(node.left);
        }
    }

    /**
     * 寻找二分搜索树中最大的元素
     * @return
     */
    public E maximum(){
        if (size == 0){
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    /**
     * 寻找以node为root的二分搜索树的最大的元素
     * @param node
     * @return
     */
    private Node maximum(Node node){
        //终止条件,如果没有右边节点了,说明这个节点就是最大的
        if (node.right == null){
            return node;
        }else {
            return maximum(node.right);
        }
    }

    /**
     * 删除二分搜索树中的最小元素,并返回
     * @return
     */
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点,返回删除节点后新的二分搜索树的根(因为相当于删除的'根'元素让右子树来接)
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        //终止条件,没有左孩子了,说明这个就是要删除的节点
        if (node.left == null){
            //保存下右子树,即使右子树为null,也可以当做一种通用的右子树来处理
            Node rightNode = node.right;
            //断开右子树
            node.right = null;
            //减掉删除的元素
            size--;
            //返回右子树的根节点
            return rightNode;
        }
        //返回的是删除掉节点后的新的树
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除二分搜索树中的最大元素
     * @return E
     */
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除以node为根节点的最大的元素
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        //终止条件,如果没有右孩子了,说明是最大的节点,说明这个node就是要删除了,保留左子树并返回给这个节点的父亲
        if (node.right == null){
            Node leftChild = node.left;
            node.left = null;
            size--;
            return leftChild;
        }
        //接一下左子树
        node.left = removeMax(node.right);
        //返回已经做了删除操作的树
        return node;
    }

    /**
     * 在删除掉指定的元素e
     * @param e
     */
    public void remove(E e){
        root = remove(root,e);
    }

    /**
     * 删除以node为根的二分搜索树中值为e的节点,递归算法(最复杂的一种操作)
     * 返回删除后的新的二分搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node , E e){
        //如果遍历到最后都没有找到,直接返回null
        if (node == null){
            return null;
        }
        //如果小于节点,则去左子树中查找
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left , e);
            return node;
        }
        //如果大于节点,则去右子树中查找
        else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right , e);
            return node;
        }
        //如果和当前节点相同了,说明要删除node这个节点(核心逻辑)
        else {
            //如果没有左子树,则直接返回右子树接一下就行了,**(重点)进来的是node,返回的是node.right或者node.left
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //如果没有右子树
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //如果左右孩子都有的情况下,使用hibbard删除法,选择比要删除的节点大的最小的节点进行替代
            else {
                //获取后继的元素,即比根节点大的最小的元素
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
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
