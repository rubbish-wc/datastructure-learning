package com.blanc.algorithm.sort;

import java.util.zip.Inflater;

/**
 * 快速排序练习
 *
 * @author wangbaoliang
 */
public class QuickSort {

    public static void main(String[] args) {

    }

    /**
     * 外层
     *
     * @param arr   要排序的数组
     * @param begin 起始位置
     * @param end   结束位置
     */
    public static void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }

    /**
     * partition排序
     *
     * @param a
     * @param begin
     * @param end
     * @return
     */
    static int partition(int[] a, int begin, int end) {
        //标杆
        int pivot = end;
        //小于标杆的数量
        int counter = begin;
        for (int i = begin; i < end; i++) {
            //如果比标杆小
            if (a[i] < a[pivot]) {
                int temp = a[counter];
                a[counter] = a[i];
                a[i] = temp;
                counter++;
            }
        }
        int temp = a[counter];
        a[counter] = a[pivot];
        a[pivot] = temp;
        return counter;
    }
}
