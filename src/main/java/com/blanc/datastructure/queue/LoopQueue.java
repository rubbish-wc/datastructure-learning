package com.blanc.datastructure.queue;


/**
 * 循环队列: 核心在于 判断队列为空 tail == front , 判断队列满了 (tail + 1) % data.length == front , 浪费一个capacity
 * 因为真的满了的情况下 tail == front 了 ,与队列为空的判断条件相同了
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    private int front,tail;

    private int size;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    /**
     * 入队操作
     * @param e
     */
    @Override
    public void enqueue(E e) {
        //如果队列满了,扩容
        if ((tail + 1) % data.length == front){
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail+1) % data.length;
        size++;
    }


    /**
     * 出队操作
     * @return
     */
    @Override
    public E dequeue() {
        if (front == tail){
            throw new IllegalArgumentException("can not dequeue from an empty queue");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity()/4 && getCapacity()/2 != 0){
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity(){
        return data.length - 1;
    }

    /**
     * 扩容方法
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity + 1];
        for (int i = 0 ; i < size ; i++){
            newData[i] = data[(front+i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Queue: size = %d , capacity = %d\n"),size,getCapacity());
        stringBuilder.append("front [");
        for (int i = front ; i != tail ; i = (i+1) % data.length){
            stringBuilder.append(data[i]);
            if ((i+1) % data.length != tail){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }
}
