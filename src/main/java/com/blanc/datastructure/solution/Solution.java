package com.blanc.datastructure.solution;

import java.util.Random;

public class Solution {
    private int[] originNums;

    private int[] originCopyNums;

    private int[] newNums;

    private Random random;

    public Solution(int[] nums) {
        this.originNums = nums;
        this.originCopyNums = nums;
        //new出新的numbs
        this.newNums = new int[originNums.length];
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return originNums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int length = newNums.length;
        int leftLength = length;
        for ( int i = 0 ; i < length ; i++){
            int randomIndex = random.nextInt(leftLength--);
            int originCopyNum = originCopyNums[randomIndex];
        }
        return newNums;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution solution = new Solution(nums);

        solution.shuffle();
        System.out.println(solution.newNums);

        solution.reset();

        solution.shuffle();

        System.out.println(solution.newNums);


    }
}
