package com.blanc.datastructure.set;

/**
 * 集合接口
 * @param <E>
 */
public interface Set<E> {

    /**
     * 添加元素
     * @param e
     */
    void add(E e);

    /**
     * 删除元素
     * @param e
     */
    void remove(E e);

    /**
     * 判断是否包含元素
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 获取集合的大小
     * @return
     */
    int getSize();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();
}
