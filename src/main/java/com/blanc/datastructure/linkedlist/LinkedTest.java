package com.blanc.datastructure.linkedlist;

public class LinkedTest {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0 ; i < 5 ; i++){
            linkedList.addFirst(i);
        }
        linkedList.addFirst(2);
        linkedList.removeElement(2);
        System.out.println(linkedList);
    }
}
