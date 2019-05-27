package com.blanc.datastructure.heap;

import java.util.Random;

/**
 * 最大堆测试
 * @author wangbaoliang
 */
public class MapHeadTest {

    public static void main(String[] args) {
        int n = 10000000;

        //在最大堆中存取1000000个随机数
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0 ; i < n ; i++){
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        //构造一个数组,利用最大堆的性质进行降序排序
        int[] arr = new int[n];
        for (int i = 0 ; i < n ; i ++){
            arr[i] = maxHeap.extractMax();
        }

        //对数组进行校验,如果不满足降序,则说明排序有问题
        for (int i = 1 ; i < n ; i++){
            if (arr[i-1] < arr[i]){
                throw new IllegalArgumentException("Error");
            }
        }
    }
}
