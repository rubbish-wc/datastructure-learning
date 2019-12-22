package com.blanc.datastructure.stack;

import java.util.Random;

/**
 * 比较数组栈和链表栈
 */
public class MainTest {

    /**
     * 比较
     * @param stack
     * @param operatorNumerCount
     * @return
     */
    private static Double compareLinkedAndArrayStack(Stack<Integer> stack , int operatorNumerCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0 ; i < operatorNumerCount ; i++){
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0 ; i < operatorNumerCount ; i++){
            stack.pop();
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    /**
     * 对比出来,链表栈实际上去数组栈要快上一个量级,但是这个比较是不靠谱的
     * 对于数据栈而言,时不时的就要去扩容一下,挺费时的,但是数据量越多,扩容的次数就越来越少
     * 而对于linkedListStack,要不断的重新new一个node
     * 所以到了一个数量级,linkedListStack的耗时就会远高于ArrayStack
     * 比如10w级别,2比1快,但是到了百万,千万级别,linkedListStack就会慢的多的多的多
     * 所以总结: 这两个stack没啥吊区别,100000000太恐怖了,arrayStack 40s搞定,linkedListStack直接歇逼了
     * @param args
     */
    public static void main(String[] args) {
        int i = 10000000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        Double time1 = compareLinkedAndArrayStack(arrayStack, i);
        System.out.println(time1);
        System.out.println("===================");
        Double time2 = compareLinkedAndArrayStack(linkedListStack,i);
        System.out.println(time2);
    }
}
