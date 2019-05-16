package com.blanc.datastructure.linkedlist;

/**
 * 链表的实现
 * @author wangbaoliang
 * @param <E>
 */
public class LinkedList<E> {

    private Node head;

    private int size;

    /**
     * 只有链表才会用到的底层成员内部类,节点,对用户屏蔽底层的实现细节
     * @author wangbaoliang
     */
    private class Node{

        //设计成public是方便让外部类直接使用,因为这个内部类本身是private的,所以不需要担心安全问题
        public E e;

        //保存下一个node
        public Node next;

        /**
         * node节点构造器
         * @param e
         * @param next
         */
        public Node(E e , Node next){
            this.e = e;
            this.next = next;
        }

        /**
         * 没有next节点的构造器
         * @param e
         */
        public Node(E e){
            this(e,null);
        }

        /**
         * 构造器
         */
        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    /**
     * 构造器
     */
    public LinkedList(){
        head = null;
        size = 0;
    }

    //获取链表中元素的个数
    public int getSize(){
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
     * 在链表头添加一个新的元素
     * @param e
     */
    public void addFirst(E e){
        Node node = new Node(e);
        node.next = head;
        head = node;
        size++;
    }

    /**
     * 在某个元素的后面添加一个元素
     */
    public void add(int index , E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed , Illegal index");
        }
        if (index == 0){
            addFirst(e);
        }else {
            Node prev = head;
            for (int i = 0 ; i < index -1 ; i++){
                prev = prev.next;
            }

            Node node = new Node(e);
            node.next = prev.next;
            prev.next = node;

        }
    }

    /**
     * 像链表的最后一个位置添加元素
     * @param e
     */
    public void addLast(E e){
        add(size , e);
    }
}
