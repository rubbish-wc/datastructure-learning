package com.blanc.algorithm.sort.mergesort;

import javafx.beans.binding.When;

/**
 * 归并排序
 *
 * @author wangbaoliang
 */
public class MergeSort {

    public static void main(String[] args) {

    }

    /**
     * @param array 要排序的数组
     * @param left  要排序部分的左边界
     * @param right 要排序部分的右边界
     */
    private void mergeSort(int[] array, int left, int right) {
        //递归终止条件
        if (left >= right) {
            return;
        }
        //拆分处索引
        int mid = (left + right) >> 1;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

    }

    /**
     * 归并的核心算法,三个循环
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private void merge(int[] array, int left, int mid, int right) {
        //临时数组用于存储最终结果
        int[] temp = new int[right - left + 1];
        //i 左数组的起始, j右数组的起始, k:临时数组的起始
        int i = left, j = mid + 1, k = 0;
        //当两个数组都没有完全放完的情况下
        while (i <= mid && j <= right) {
            temp[k++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        //当右边数组先放完了
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        //当右边数组全放完了
        while (j <= mid) {
            temp[k++] = array[j++];
        }
        //将temp数组拷贝到原数组上
        System.arraycopy(temp, 0, array, 0, temp.length);
    }

}
