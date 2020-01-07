package com.blanc.datastructure.solution;

import com.blanc.datastructure.queue.PriorityQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Solution347 {

    /**
     * 可以理解为:最大优先级堆,看怎么定义排序了
     */
    private class Freq implements Comparable<Freq>{
        public int e,freq;

        public Freq(int e , int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            //越小优先级越高
            if (this.freq < another.freq){
                return 1;
            }else if (this.freq > another.freq){
                return -1;
            }else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        //统计每个数字出现的次数
        for (int num : nums) {
            if (treeMap.containsKey(num)){
                treeMap.put(num,treeMap.get(num) + 1);
            }else {
                treeMap.put(num,1);
            }
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
        //遍历映射中所有的key,别傻傻的用iterator了
        for (int key: treeMap.keySet()){
            //如果优先队列没有满,则直接放入其中
            if (priorityQueue.getSize() < k){
                priorityQueue.enqueue(new Freq(key,treeMap.get(key)));
            }
            //如果优先队列满了
            else {
                //且这个有点队列中的最小值比这个key的值还要小
                if (treeMap.get(key) > priorityQueue.getFront().freq) {
                    //获取这个优先队列中的最小值(优先级最高,即堆顶,队首),并且移除
                    priorityQueue.dequeue();
                    priorityQueue.enqueue(new Freq(key, treeMap.get(key)));
                }else {
                    continue;
                }
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            res.add(priorityQueue.dequeue().e);
        }
        return res;
    }
}
