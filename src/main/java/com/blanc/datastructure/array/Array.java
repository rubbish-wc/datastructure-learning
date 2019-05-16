package com.blanc.datastructure.array;

/**
 * 基于java的数组封装的动态数组
 * @author blanc
 */
public class Array<E> {

    /**
     * 容器
     */
    private E[] data;

    /**
     * 这个数组中有多少数据
     */
    private int size;

    /**
     * 构造器:传入的参数是数组的容量
     * @param capacity
     */
    public Array(int capacity){
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * 不穿capacity的情况下,默认是10
     */
    public Array(){
        this(10);
    }

    /**
     * 获取数组中的元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组的容量是多少
     * @return
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向所有元素后添加一个元素
     * @param e
     */
    public void addLast(E e){
        add(size,e);
    }

    /**
     * 向数组的第一位添加元素
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 在指定的位置index插入e
     * @param index
     * @param e
     */
    public void add(int index, E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size");
        }

        //如果已经满了则resize扩容
        if (size == data.length){
            resize(2 * data.length);
        }

        for (int i = size-1 ; i >= index ; i--){
            //所有元素往后移动一个位置
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * resize容量
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0 ; i < size ; i++){
            newData[i] = data[i];
        }
        data = newData;
    }


    /**
     * 通过索引获取相应位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index >=size){
            throw new IllegalArgumentException("Get failed . Index is illegal");
        }
        return data[index];
    }

    /**
     * 获取最后一个元素
     * @return
     */
    public E getLast(){
        return get(size-1);
    }

    /**
     * 获取第一个元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 给特定的索引设定值
     * @param index
     * @param e
     */
    public void set(int index , E e){
        if (index < 0 || index >=size){
            throw new IllegalArgumentException("Set failed , Index is illegal");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0 ; i < size ; i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引,如果不存在元素e,则返回-1
     * @param e
     * @return
     */
    public int find(E e){
        for (int i = 0 ; i < size ; i++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除特定索引的元素,返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove fail , Index is illegal");
        }
        //保留起来用于删除
        E ret = data[index];
        for (int i = index + 1 ; i < size ; i++){
            data[i-1] = data[i];
        }
        size--;
        //loitering objects 本身以及没有用了,但是强引用,垃圾回收暂时回收不了,所以这个手动设null,这个不是memoryleak;迟早也会被add方法替换掉
        data[size] = null;
        if (size == getCapacity() / 4 && getSize() / 2 !=0){
            resize(getCapacity() / 4);
        }
        return ret;
    }

    /**
     * 删除第一个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素
     * @return
     */
    public E removeLast(){
        return remove(size -1);
    }

    /**
     * 从数组中删除元素e
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array: size = %d , capacity = %d\n",size,data.length));
        stringBuilder.append('[');
        for (int i = 0 ; i < size ; i++){
            stringBuilder.append(data[i]);
            if (i != size-1){
                stringBuilder.append(',');
            }else {
                stringBuilder.append(']');
            }
        }
        return stringBuilder.toString();
    }
}
