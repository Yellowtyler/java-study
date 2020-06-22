package com.study.danya.task1;

public class Cat implements Movement {
    private String name;
    private String type;

    public Cat(String name, String type) {
        this.name = name;
        this.type = type;
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
