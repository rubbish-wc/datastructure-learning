package com.blanc.algorithm.sort.quicksort;

public class QuickSort2 {

    private void quickSort(int[] array, int begin, int end) {
        //递归终止条件,即pivot不能再细分数组
        if (begin >= end) {
            return;
        }
        //pivot操作
        final int pivot = partition(array, begin, end);
        //再quick左和右
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);

    }

    /**
     * 分组pivot
     *
     * @param array
     * @param begin
     * @param end
     * @return
     */
    private int partition(int[] array, int begin, int end) {
        int pivot = end;
        int counter = begin;
        //谨记 counter 其实就是过会的pivot,小于的话counter后移,大于的话下个循环要到counter的右边去
        for (int i = begin; i < end; i++) {
            if (array[i] < pivot) {
                //交换的逻辑一定是上个循环发现大于,counter没动,然后下个周期交换counter和i
                //正常的小于这个逻辑不会有影响,因为counter和i是同一个位置
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        //循环完毕后,交换counter和pivot,并返回counter作为pivot
        int temp = array[counter];
        array[counter] = array[pivot];
        array[pivot] = counter;
        return counter;
    }
}
