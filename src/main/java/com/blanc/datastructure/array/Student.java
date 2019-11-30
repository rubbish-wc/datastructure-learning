package com.blanc.datastructure.array;

public class Student {

    private String name;

    private int score;

    public Student(String name, int studentScore) {
        this.name = name;
        this.score = studentScore;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s,score: %d)", name, score);
    }

    public static void main(String[] args) {
        Array<Student> array = new Array<>();
        array.addLast(new Student("alice", 100));
        array.addLast(new Student("wbl", 120));
        array.addLast(new Student("sb", 80));
        System.out.println(array);
    }
}
