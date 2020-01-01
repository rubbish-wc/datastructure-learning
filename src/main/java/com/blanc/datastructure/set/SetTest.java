package com.blanc.datastructure.set;

public class SetTest {

    private static double testSet(Set<String> set , String fileName){
        long startTime = System.nanoTime();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
