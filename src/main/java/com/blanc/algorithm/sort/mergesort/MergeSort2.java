package com.blanc.algorithm.sort.mergesort;

public class MergeSort2 {

    public void mergeSort(int[] array, int left, int right) {
        //递归终止条件
        if (left >= right) {
            return;
        }
        //计算中间值用于左右拆分归并
        int mid = (right - left) >> 1;
        //先归并左侧
        mergeSort(array, left, mid);
        //再归并右侧
        mergeSort(array, mid + 1, right);
        //合并左侧和右侧
        merge(array, left, mid, right);
    }

    /**
     * 合并
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private void merge(int[] array, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        //新开数组辅助归并排序
        int[] temp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            temp[k++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        System.arraycopy(temp, 0, array, 0, temp.length);
    }
}
