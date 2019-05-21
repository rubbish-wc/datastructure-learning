package com.blanc.datastructure.solution;

/**
 * 使用递归的方式求和
 * @author wangbaoliang
 */
public class Sum {

    /**
     * 使用递归的方式给数组求
     * 和
     * @param arr
     * @return
     */
    public static int sum(int[] arr){
        return sum(arr,0);
    }

    /**
     * 递归函数,计算arr[l,n)这个区间内所有数字的和
     * @param arr
     * @param l
     * @return
     */
    private static int sum(int[]arr , int l){
        if (l == arr.length){
            return 0;
        }else {
            return arr[l] + sum(arr,l+1);
        }
    }
}
