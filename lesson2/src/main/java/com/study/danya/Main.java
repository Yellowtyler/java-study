package com.study.danya;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество сотрудников в группе (не должно превышать 10): ");
        int n = scanner.nextInt();
        if(n > 10) {
            while(n > 10) {
                System.out.println("Ошибка! Число не должно превышать 10! Введите его еще раз");
                n = scanner.nextInt();
            }
        }
        List<Worker> workers = new ArrayList<>();
        System.out.println("Введите информацию о сотрудниках: ");
        for (int i = 0; i < n; i++) {
            System.out.print("Введите имя: ");
            String name = scanner.next();

            System.out.print("Введите email: ");
            String email = scanner.next();

            System.out.print("Введите возраст: ");
            int age = scanner.nextInt();

            System.out.print("Введите должность: ");
            String rank = scanner.next();

            System.out.println();
            Worker worker = new Worker(name, email, age, rank);
            workers.add(worker);
        }
        System.out.print("Введите название группы: ");
        String name = scanner.next();
        Group group = new Group(name, workers);
        System.out.println("Вывод информации сотрудников группы: ");
        group.printGroupInformation();
        System.out.println();

        Worker ivan = new Worker("ivan", "ivan@mail.ru", 26, "Junior");
        group.addNewWorker(ivan);
        group.printGroupInformation();

        System.out.println();

        group.removeWorker(0);
        group.printGroupInformation();
        System.out.println();

        group.removeAll();
        group.printGroupInformation();
    }
}
