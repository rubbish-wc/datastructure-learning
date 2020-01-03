package com.blanc.datastructure.solution;

import java.util.ArrayList;
import java.util.TreeSet;

public class Solution349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums1) {
            treeSet.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2){
            if (treeSet.contains(num)){
                list.add(num);
                treeSet.remove(num);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0 ; i < list.size() ; i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
