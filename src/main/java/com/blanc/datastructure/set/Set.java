package com.blanc.datastructure.set;

/**
 * 集合接口
 * @param <E>
 */
public interface Set<E> {

    /**
     * 添加一个元素
     * @param e
     */
    void add(E e);

    /**
     * 删除一个元素
     * @param e
     * @return
     */
    E remove(E e);

    /**
     * 判断是否包含
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 获取集合大小
     * @return
     */
    int getSize();
}
