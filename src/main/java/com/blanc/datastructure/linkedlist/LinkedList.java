package com.blanc.datastructure.linkedlist;

/**
 * 链表的实现
 * 真正的动态数据结构,最简单的动态数据结构,更深入的理解指针,更深入的理解递归,辅助组成其他的数据结构
 * 数据存储在*节点(Node)*中
 * 优点:真正的动态结构,不需要处理固定容量的问题  确定:丧失了随机访问的能力(没有直接通过索引去访问元素的功能)
 * @param <E>
 * @author wangbaoliang
 */
public class LinkedList<E> {

    /**
     * 链表头节点(升级为虚拟头结点),有了虚拟头节点,insert方法更加的方便了
     */
    private Node dummyHead;

    /**
     * 链表中的数量size
     */
    private int size;

    /**
     * 只有链表才会用到的底层成员内部类,节点,对用户屏蔽底层的实现细节
     * @author wangbaoliang
     */
    private class Node {

        /**
         * 节点中保存的元素
         */
        public E e;

        /**
         * 下一个节点
         */
        public Node next;

        /**
         * node节点构造器
         *
         * @param e
         * @param next
         */
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        /**
         * 没有next节点的构造器
         *
         * @param e
         */
        public Node(E e) {
            this(e, null);
        }

        /**
         * 构造器
         */
        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 构造器
     */
    public LinkedList() {
        //初始化链表的时候,就已经有个节点了(虚拟头节点),但是不计入size中
        dummyHead = new Node(null,null);
        size = 0;
    }

    /**
     * 获取链表中元素的个数
     *
     * @return
     */
    public int getSize() {
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
     * 在链表的'index'(0->based)位置添加新的元素e,在链表中不是很常用,练习用
     * 因为当我们选择链表的时候,通常我们就不使用索引了
     * 重点在于找到前驱节点
     * @param index
     * @param e
     */
    public void insert(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed , Illegal index");
        }
        //如果在链表头添加一个元素,(因为head没有prev前驱节点),所以这里特殊处理就好
        //特殊处理不怎么优雅,可以通过虚拟头结点,解决这个问题
        Node prev = dummyHead;
        //prev就是index的前一个,所以从head开始,next(index-1)次就行了
        //使用了dummyhead之后,要从dummy开始,next(index)次就可以
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);
        size++;
    }

    /**
     * 在链表头添加一个新的元素
     * 用图示的方法会更简单
     * @param e
     */
    public void addFirst(E e) {
        //head指向了新创建的节点,且新的node中指向了原来的head
        insert(0,e);
    }

    /**
     * 像链表的最后一个位置添加元素
     *
     * @param e
     */
    public void addLast(E e) {
        insert(size, e);
    }

    /**
     * 获取链表第index个元素,在链表中不是一个很常用的操作,练习用
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("get failed , illegal index");
        }
        Node current = dummyHead.next;
        for (int i = 0 ; i < index ; i++){
            current = current.next;
        }
        return current.e;
    }

    /**
     * 获取链表第一个元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获取链表最后一个元素
     * @return
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 修改链表的index位置上的元素
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("set failed , illegal index");
        }
        //获取第0号位置上的元素开始遍历
        Node current = dummyHead.next;
        for (int i = 0 ; i < index ; i++){
            current = current.next;
        }
        current.e = e;
    }
}
