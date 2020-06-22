package com.study.danya.task1;

public class Human implements Movement {
    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
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
