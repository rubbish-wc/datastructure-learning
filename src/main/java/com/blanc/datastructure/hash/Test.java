package com.blanc.datastructure.hash;

import java.util.HashMap;
import java.util.HashSet;


public class Test {

    public static void main(String[] args) {
        Student student = new Student(3,2,"王","保良");
        System.out.println(student.hashCode());
        HashSet<Student> students = new HashSet<>();
        students.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);

        Student student2 = new Student(3,2,"王","保良");
        System.out.println(student2.hashCode());
    }
}
