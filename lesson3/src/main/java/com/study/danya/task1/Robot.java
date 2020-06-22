package com.study.danya.task1;

public class Robot implements Movement {
    private String name;
    private String model;

    public Robot(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public void run(boolean check) {
        if(check) {
            System.out.println(name + " успешно пробежал.");
        } else {
            System.out.println(name + " не пробежал.");
        }
    }

    public void jump(boolean check) {
        if(check) {
            System.out.println(name + " успешно перепрыгнул.");
        } else {
            System.out.println(name + " не перепрыгнул.");
        }
    }
}
