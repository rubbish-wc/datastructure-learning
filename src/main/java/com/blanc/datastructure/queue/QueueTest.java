package com.blanc.datastructure.queue;

import java.util.Random;

/**
 * 比较循环队列和数组队列
 */
public class QueueTest {

    private static double testQueue(Queue<Integer> queue,int op){
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0 ; i < op ; i++){
            Integer nextInt = random.nextInt(Integer.MAX_VALUE);
            queue.enqueue(nextInt);
        }
        for (int i = 0 ; i < op ; i++){
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        //10w个操作数
        int opcount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double v = testQueue(arrayQueue, opcount);
        System.out.println(v);

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double v1 = testQueue(loopQueue, opcount);
        System.out.println(v1);
    }
}
