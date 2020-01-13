package com.blanc.datastructure.trie;

import java.util.TreeMap;

/**
 * Trie 字典树,前缀树的实现
 * 多叉树,微软的通讯录问题
 * 查询每一个条目的时间复杂度,和字典中一共有多少条目无关,时间复杂度为O(w),w为查询单词的长度!,对于绝大多数的单词,长度都是小于10的
 * trie: 比如每一个节点(有能力)有26个指向下个节点的指针,a-z26个英文字母,不考虑大小写,不考虑特殊字符,阳春版本
 * 其实应该是每个节点有若干个指向下个节点的指针
 * Trie不是泛型类,假设每一个存储的元素都是character大小的字符.比如如果是汉语就不行,因为单个汉字有的大于character
 * @author wangbaoliang
 */
public class Trie {

    /**
     * 整个trie的根节点
     */
    private Node root;

    /**
     * 总共存储的单词的数量
     */
    private int size;

    /**
     * 定义trie的节点
     */
    private class Node {

        /**
         * 标识当前的节点是否是某个单子的结尾(因为一些单词是另一些单词的前缀,所以通过叶子节点判断是否是单词不靠谱)
         * 如果这个是true,说明到这个节点是个单词
         */
        public boolean isWord;

        /**
         * 映射:支持的所有字符中下一个字符以及下一个字符所在的node
         */
        public TreeMap<Character, Node> next;

        /**
         * 构造函数
         * @param isWord
         */
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        /**
         * 构造函数:默认不是单词
         */
        public Node() {
            this(false);
        }
    }

    /**
     * 构造函数
     */
    public Trie() {
        root = new Node();
        size = 0;
    }

    /**
     * 获取大小
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 向trie中添加一个新的单词
     * 非递归写法
     * @param word
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询一个单词是否在trie中
     * 如果发现了next的map中没有这个字符,就说明不包含,或者是到最后一个字符,但是isWord是false,都说明不包含
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            } else {
                cur = cur.next.get(c);
            }
        }
        return cur.isWord;
    }

    /**
     * 查询在trie中是否有单词以prefix为前缀
     *
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            } else {
                cur = cur.next.get(c);
            }
        }
        return true;
    }
}
