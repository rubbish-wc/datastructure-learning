package com.blanc.datastructure.hash;


/**
 * 面试重点:
 * 1:java默认的hashCode是使用对象的地址做计算得到的,所以字段完全一样的两个对象的hashCode结果会不一样
 * 所以当我们使用HashSet和HashMap的时候,要注意重写hashCode,否则可能会得到和自己预期不一样的结果
 * 2:当使用我们自已的hashCode方法的时候,如果出现了hash冲突,我们需要判断这两个冲突的元素到底是不是相等的,所以要重写equals方法
 **/
public class Student {

    private int grade;

    private int cla;

    private String firstName;

    private String lastName;

    public Student(int grade, int cla, String firstName, String lastName) {
        this.grade = grade;
        this.cla = cla;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * hashCode是int,可能返回负值
     * @return
     */
    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cla;
        hash = hash * B + firstName.hashCode();
        hash = hash * B + lastName.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (this == obj){
            return true;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        Student another = (Student)obj;
        return this.grade == another.grade
                && this.cla == another.cla
                && this.firstName.equals(another.firstName)
                && this.lastName.equals(another.lastName);
    }
}
