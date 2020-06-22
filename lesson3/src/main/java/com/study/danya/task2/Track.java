package com.study.danya.task2;

public class Track extends Barrier {
   private int length;

    public Track(int length) {
        this.length = length;
    }

    public boolean isPassing(int limitPerson) {
        return limitPerson >= this.length;
   }
}
