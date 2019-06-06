package com.blanc.datastructure.tree;

import java.util.TreeMap;

/**
 * 字典树的实现
 * @author wangbaoliang
 */
public class Trie {

    private Node root;

    private int size;

    private class Node{

        public boolean isWord;

        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    /**
     * 像trie中添加一个新的单词
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for (int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询一个单词是否在trie中
     * @param word
     * @return
     */
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }else {
                cur = cur.next.get(c);
            }
        }
        return cur.isWord;
    }
}
