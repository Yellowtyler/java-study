package com.study.daniil;

public abstract class Stage {
    int length;
    String description;

    public String getDescription() {
        return description;
    }

    public abstract  void overcome(Car c);
}



