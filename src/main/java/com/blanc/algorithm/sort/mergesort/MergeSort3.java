package com.blanc.algorithm.sort.mergesort;

/**
 * 归并排序训练3
 *
 * @author wangbaoliang
 */
public class MergeSort3 {

    public static void main(String[] args) {
        int[] array = new int[]{
                3, 8, 5, 9, 2, 6, 1, 4, 12, 74, 34, 978, 45, 23
        };
        mergeSort(array, 0, array.length - 1);
        for (int i = 0 ; i < array.length ; i++){
            System.out.println(array[i]);
        }
    }

    /**
     * 递归函数: 归并排序
     *
     * @param array
     * @param left
     * @param right
     */
    public static void mergeSort(int[] array, int left, int right) {
        //递归终止条件
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    /**
     * 合并两个有序数组
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        for (int p = 0 ; p < temp.length ; p++){
            array[left + p] = temp[p];
        }
    }
}
