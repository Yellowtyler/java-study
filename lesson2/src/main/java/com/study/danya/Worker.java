package com.study.danya;

public class Worker {

    private String name;
    private String email;
    private int age;
    private String rank;

    public Worker(String name, String email, int age, String rank) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.rank = rank;
    }

    public void printInformation() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Сотрудник{" +
                "имя='" + name + '\'' +
                ", email='" + email + '\'' +
                ", возраст=" + age +
                ", должность='" + rank + '\'' +
                '}';
    }
}
