package com.blanc.datastructure.heap;

import com.blanc.datastructure.queue.PriorityQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * 关于M个元素的TOP K问题  K<<<M
 *
 */
public class Solution_347{

    private class Freq implements Comparable<Freq>{

        /**
         * 元素
         */
        private int e;

        /**
         * 出现的频次
         */
        private int freq;

        /**
         * 定义频次小的元素优先级反而高
         * @param antoher
         * @return
         */
        @Override
        public int compareTo(Freq antoher) {
            //如果当前的元素的频次小于another返回1,这样优先级最高的元素是频次最小的元素
            if (this.freq < antoher.freq){
                return 1;
            }else if (this.freq > antoher.freq){
                return -1;
            }else {
                return 0;
            }
        }

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }
    }

    /**
     * NlogK的时间复杂度
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums){
            if (map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
        for (int key : map.keySet()){
            //如果还没有存够k个元素
            if (priorityQueue.getSize() < k){
                priorityQueue.enqueue(new Freq(key, map.get(key)));
            }else if (map.get(k) > priorityQueue.getFront().freq){//如果频次比优先队列中最小的还要小
                priorityQueue.dequeue();
                priorityQueue.enqueue(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            res.add(priorityQueue.dequeue().e);
        }
        return res;
    }
}
