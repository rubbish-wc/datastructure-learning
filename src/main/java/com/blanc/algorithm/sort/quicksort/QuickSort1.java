package com.blanc.algorithm.sort.quicksort;

public class QuickSort1 {

    public static void main(String[] args) {

    }

    public void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        final int pivot = partition(arr, begin, end);
        quickSort(arr, begin, pivot - 1);
        quickSort(arr, pivot + 1, end);
    }

    /**
     * 返回pivot , 并且左侧元素都 < pivot ,右侧元素都大于 pivot
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    private int partition(int[] arr, int begin, int end) {
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (arr[i] < arr[pivot]) {
                int temp = arr[i];
                arr[i] = arr[counter];
                arr[counter] = temp;
                counter++;
            }
        }
        int temp = arr[counter];
        arr[counter] = arr[pivot];
        arr[pivot] = temp;
        return counter;
    }
}
