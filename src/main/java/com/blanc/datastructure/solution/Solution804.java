package com.blanc.datastructure.solution;

import java.util.TreeSet;

public class Solution804  {

    public int uniqueMorseRepresentations(String[] words){

        //摩斯电码
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        //treeSet基于平衡二叉树实现,tree实现的set
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0 ; i < word.length() ; i++){
                res.append(codes[word.charAt(i) - 'a']);
            }
            //如果两个单词的摩斯码相同,set中就会自动去重
            set.add(res.toString());
        }
        return set.size();
    }
}
