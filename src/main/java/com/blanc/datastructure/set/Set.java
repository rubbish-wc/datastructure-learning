package com.blanc.datastructure.set;

/**
 * 集合接口
 * 承载元素的容器,特点集合中每个元素只能出现一次,可以帮助我们去重,二分搜索树就是天然的适合实现集合的底层数据结构
 * @param <E>
 */
public interface Set<E> {

    /**
     * 添加一个元素
     * (不能添加重复的元素)
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
