package com.blanc.datastructure.map;

/**
 * 基于链表实现的映射
 * @param <K>
 * @param <V>
 */
public class LinkedListMap<K,V> implements Map<K,V> {

    private Node dummyHead;

    private int size;

    /**
     * 构造函数
     */
    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 节点
     */
    private class Node{

        public K key;

        public V value;

        public Node next;

        public Node(K key , V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        //如果没有这个key,则直接在链表头添加元素
        if (node == null){
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else {
            //如果已经存在了,则可以抛异常或者更新,这里我们选择更新
            node.value = value;
        }
    }

    /**
     * 删除一个元素
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null){
            if (prev.next.key.equals(key)){
                break;
            }else {
                prev = prev.next;
            }
        }
        //如果遍历到结尾了,说明没有要删的
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K k) {
        return getNode(k) != null;
    }

    /**
     * 如果为null,说明这个key不存在,或者存在,但是value为null
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * 更新指定的key的值
     * @param key
     * @param value
     */
    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        //如果这个值不存在,抛出异常
        if (node == null){
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 辅助用:根据一个key,返回这个key所在的节点
     * @param key
     * @return
     */
    private Node getNode(K key){
        Node current = dummyHead.next;
        while (current != null){
            if (current.key.equals(key)){
                return current;
            }
        }
        return null;
    }

}
