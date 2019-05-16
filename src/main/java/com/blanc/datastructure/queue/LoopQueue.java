package com.blanc.datastructure.queue;

import com.blanc.datastructure.array.Array;

import java.util.Arrays;

/**
 * 循环队列实现(相比普通的队列,出队的时间复杂度为o(1),而非o(n))
 * @author wangbaoliang
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    /**
     * 队首所在的索引
     */
    private int front;

    /**
     * 队尾所在的索引,如果要入队新的元素所在的位置
     */
    private int tail;

    /**
     * 队列的大小
     */
    private int size;

    /**
     * 构造器
     * @param capacity
     */
    public LoopQueue(int capacity){
       data = (E[]) new Object[capacity + 1];
       front = 0;
       tail = 0;
       size = 0;
    }

    /**
     * 构造函数
     */
    public LoopQueue(){
        this(10);
    }

    /**
     * 获取容量
     * @return
     */
    public int getCapacity(){
        return data.length - 1;
    }


    /**
     * 入队
     * @param e
     */
    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front){
            resize(getCapacity() * 2);
        }
        //入队存放一个数据
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot deque from a empty queue");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        //如果只用了4分之1 并且 缩容后的结果不应该为0
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return ret;
    }


    /**
     * 扩容
     * @param newCapacity
     */
    private void resize(int newCapacity){

        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0 ; i < size ; i ++){
            newData[i] = data[(i+front) % data.length];
        }
        data = newData;
        front = 0 ;

    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("queue is empty");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == size;
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Que:size = %d ,capacity = %d \n",size,data.length));
        stringBuilder.append("front [");
        for (int i = 0 ; i < size ; i++){
            stringBuilder.append(data[i]);
            if (i != size - 1){
                stringBuilder.append(",");
            }else {
                stringBuilder.append("]");
            }
        }
        return stringBuilder.toString();
    }
}
