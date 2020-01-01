package com.blanc.datastructure.map;

/**
 * 映射,称之为字典也可以  单词->释意  比如python,映射就称之为dict(字典)
 * 存储(键,值)数据对的数据结构(key,value)
 * @param <K>
 * @param <V>
 * 根据键(KEY),寻找值(VALUE)
 * 非常容易的使用链表或者二分搜索树实现
 */
public interface Map<K,V> {

    /**
     * 添加一个元素
     * @param key
     * @param value
     */
    void add(K key , V value);

    /**
     * 删除一个元素
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 是否包含
     * @param k
     * @return
     */
    boolean contains(K k);

    /**
     * 获取一个key的值
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 设置一个key的值(更新)
     * @param key
     * @param value
     */
    void set(K key , V value);

    /**
     * 获取数量
     * @return
     */
    int getSize();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();
}
