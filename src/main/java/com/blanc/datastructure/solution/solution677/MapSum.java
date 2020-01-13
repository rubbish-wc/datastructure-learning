package com.blanc.datastructure.solution.solution677;

import java.util.TreeMap;

class MapSum {

    private Node root;

    private class Node{

        public int value;

        public TreeMap<Character,Node> next;

        public Node(int value){
            this.value = value;
            this.next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }
    
    public void insert(String key, int val) {
        Node current = root;
        for (int i = 0 ; i < key.length() ; i++){
            char c = key.charAt(i);
            if (current.next.get(c) == null){
                current.next.put(c,new Node());
            }
            current = current.next.get(c);
        }
        current.value = val;
    }
    
    public int sum(String prefix) {
        //找到前缀的位置,再开始递归
        Node current = root;
        for (int i = 0 ; i < prefix.length() ; i++){
            char c = prefix.charAt(i);
            if (current.next.get(c) == null){
                return 0;
            }
            current = current.next.get(c);
        }
        //当前的current就是prefix的位置,从此处开始递归
        return sum(current);
    }

    /**
     * 对node节点下所有的节点的value求和,递归函数
     * @param node
     * @return
     */
    private int sum(Node node){
        int res = node.value;
        //递推公式
        for (char c : node.next.keySet()){
            res += sum(node.next.get(c));
        }
        return res;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */