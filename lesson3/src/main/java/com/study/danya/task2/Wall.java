package com.study.danya.task2;

public class Wall extends Barrier {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public boolean isPassing(int limitPerson) {
        return limitPerson >= this.height;
    }
}
