package com.blanc.datastructure.queue;


/**
 * 基于链表实现的队列(这个底层的链表需要维护tail元素方便队尾用更低的时间复杂度处理,所以要重写)
 * 队首是head,用于dequeue,队尾是tail,用于enqueue,队尾入,队首出
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E>{

    private Node head,tail;

    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * 入队操作:注意边界问题(写链表要注意边界问题)
     * @param e
     */
    @Override
    public void enqueue(E e) {
        //说明队列是空
        if (tail == null){
            tail = new Node(e);
            head = tail;
        }else {
            tail.next = new Node(e);
            tail = tail.next;
        }
    }

    /**
     * 出队操作
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new RuntimeException("can't dequeue from an empty queue");
        }else {
            Node retNode = head;
            head = head.next;
            //断开原链表
            retNode.next = null;
            //如果链表就一个元素,则这种情况下,需要特殊处理一下tail,否在tail还在retNode上,有问题的
            if (head == null){
                tail = null;
            }
            size--;
            return retNode.e;
        }
    }

    /**
     * 取队首
     * @return
     */
    @Override
    public E getFront() {
        //为什么为空要抛异常,因为直接返回null的话,上层容易空指针,且从语义的角度上来说,空的队列也不该有队首的概念的
        if (isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }else {
            return head.e;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue: front : ");
        Node current = head;
        while (current != null){
            stringBuilder.append(current + "->");
        }
        stringBuilder.append("NULL tail");
        return stringBuilder.toString();
    }

    /**
     * 队列的大小
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node{
        public E e;

        public Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

}
