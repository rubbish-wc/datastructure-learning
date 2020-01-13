package com.blanc.datastructure.solution.sulotion211;

import java.util.TreeMap;

class WordDictionary {

    private Node root;

    public WordDictionary(){
        root = new Node();
    }

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

    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node current = root;
        for (int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            //如果当前节点没有word对应位置的字符,则添加进去
            if (current.next.get(c) == null){
                current.next.put(c,new Node());
            }
            current = current.next.get(c);
        }
        if (!current.isWord){
            current.isWord = true;
        }
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root,word,0);
    }

    private boolean match(Node node , String word , int index){
        //终止条件
        if (index == word.length()){
            return node.isWord;
        }
        //递推公示
        char c = word.charAt(index);
        if (c != '.'){
            if (node.next.get(c) == null){
                return false;
            }else {
                return match(node.next.get(c),word,index+1);
            }
        }else {
            for (Character nextChar : node.next.keySet()){
                if (match(node.next.get(nextChar),word,index+1)){
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */