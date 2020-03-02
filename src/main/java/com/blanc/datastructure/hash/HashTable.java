package com.blanc.datastructure.hash;

import com.blanc.datastructure.unionfind.UF;

import java.util.Map;
import java.util.TreeMap;

/**
 * hash表
 * 和搜索树不同,hashtable的K不需要比较,所以不需要extendsComparable,但是需要有hashCode方法,因为Object已经有了hashCode方法,所以不用写了
 * 可以看成就是一个treeMap的数组
 */
public class HashTable<K, V> {

    /**
     * treeMap数据
     */
    private TreeMap<K, V>[] hashtable;

    /**
     * hashTable的长度,选择一个合适的素数
     */
    private int M;

    /**
     * 元素的个数
     */
    private int size;

    /**
     * 初始化长度的构造函数
     * @param M
     */
    public HashTable(int M){
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0 ; i < M ; i++){
            hashtable[i] = new TreeMap<>();
        }
    }

    /**
     * 无参构造函数
     */
    public HashTable(){
        this(97);
    }

    /**
     * 辅助函数:hash函数
     * @param key
     * @return
     */
    private int hash(K key){
        //先取绝对值,然后对M取模转换成索引
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * 返回大小
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 添加一个元素
     * @param key
     * @param value
     */
    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        //判断这个位置是不是已经有了这个key
        if (map.containsKey(key)){
            map.put(key, value);
        }else {
            map.put(key, value);
            size++;
        }
    }

    /**
     * 删除一个元素
     * @param key
     * @return
     */
    public V remove(K key){
        TreeMap<K, V> kvTreeMap = hashtable[hash(key)];
        V ret = null;
        if (kvTreeMap.containsKey(key)){
            ret = kvTreeMap.remove(key);
            size--;
        }
        return ret;
    }

    /**
     * 更新一个值
     * @param key
     * @param value
     */
    public void set(K key, V value){
        TreeMap<K, V> kvTreeMap = hashtable[hash(key)];
        if (!kvTreeMap.containsKey(key)){
            throw new IllegalArgumentException("键不存在");
        }
        kvTreeMap.put(key, value);
    }

    /**
     * 是否存在某一个键
     * @param key
     * @return
     */
    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    /**
     * 根据key获取其元素
     * @param key
     * @return
     */
    public V get(K key){
        return hashtable[hash(key)].get(key);
    }
}
