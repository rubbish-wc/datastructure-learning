package com.blanc.datastructure.array;

/**
 * 数组测试类
 * @author blanc
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for(int i = 0 ; i < arr.length; i ++){
            arr[i] = i;
        }

        int[] scores = new int[]{1,2,3};
        for (int i : scores){
            System.out.println(i);
        }
        scores[1] = 10;
        for (int i : scores){
            System.out.println(i);
        }
    }

}
