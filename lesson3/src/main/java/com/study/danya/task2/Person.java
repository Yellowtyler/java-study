package com.study.danya.task2;

public class Person {
    private int id;
    private int lengthLimit;
    private int heightLimit;

    public Person(int id, int lengthLimit, int heightLimit) {
        this.id = id;
        this.lengthLimit = lengthLimit;
        this.heightLimit = heightLimit;
    }

    public int getId() {
        return id;
    }

    public int getLengthLimit() {
        return lengthLimit;
    }

    public int getHeightLimit() {
        return heightLimit;
    }
}
