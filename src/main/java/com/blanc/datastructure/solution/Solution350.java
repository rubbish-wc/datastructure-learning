package com.blanc.datastructure.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Solution350 {

    public int[] intersect(int[] nums1, int[] nums2) {

        //key 元素, value 频率
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for (int i : nums1){
            if (treeMap.containsKey(i)){
                treeMap.put(i,treeMap.get(i)+1);
            }else {
                treeMap.put(i,1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i : nums2){
            if (treeMap.containsKey(i)){
                list.add(i);
                treeMap.put(i,treeMap.get(i)-1);
                if (treeMap.get(i) == 0){
                    treeMap.remove(i);
                }
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0 ; i < res.length ; i++){
            res[i] = list.get(i);
        }
        return res;
    }

}
