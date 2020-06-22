package com.study.danya;

import com.study.danya.task2.Barrier;
import com.study.danya.task2.Person;
import com.study.danya.task2.Track;
import com.study.danya.task2.Wall;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task3 {
    static Scanner scanner = new Scanner(System.in);

    public static void fillPersonList(List<Person> personList) {
        System.out.print("Введите количество участников: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Введите ограничение на бег участника " + i + ": ");
            int lengthLimit = scanner.nextInt();
            System.out.print("Введите ограничение на прыжок участника " + i + ": ");
            int heightLimit = scanner.nextInt();
            Person person = new Person(i, lengthLimit, heightLimit);
            personList.add(person);
            System.out.println("*********");
        }
    }

    public static void fillBarrierList(List<Barrier> barriers) {
        System.out.print("Введите количество препятствий: ");
        int l = scanner.nextInt();
        for (int i = 0; i < l; i++) {
            System.out.print("Препятствие "+ i +" стена или дорожка? (0/1): ");
            int choice = scanner.nextInt();
            while(choice !=0 && choice !=1) {
                System.out.println();
                System.out.println("Препятствие может быть либо стеной (значение 0) либо дорожкой (значение 1).");
                System.out.println("Введите значение еще раз: ");
                System.out.print("Препятствие "+ i +" стена или дорожка? (0/1): ");
                choice = scanner.nextInt();
            }
            switch (choice) {
                case 0 -> {
                    System.out.print("Введите высоту стены: ");
                    int height = scanner.nextInt();
                    Barrier barrier = new Wall(height);
                    barriers.add(barrier);
                }
                case 1 -> {
                    System.out.print("Введите длину дорожки: ");
                    int length = scanner.nextInt();
                    Barrier barrier1 = new Track(length);
                    barriers.add(barrier1);
                }
            }
            System.out.println("*********");
        }
    }

    public static void race(List<Person> personList, List<Barrier> barriers) {
        System.out.println("*********");
        System.out.println("Испытание началось!");
        for (int i = 0; i < barriers.size(); i++) {
            for (int j = 0; j < personList.size(); j++) {
                if(barriers.get(i) instanceof Wall) {
                    if(barriers.get(i).isPassing(personList.get(j).getHeightLimit())) {
                        System.out.println("Участник " + personList.get(j).getId() + " смог перелезть через стену " + i);
                    } else {
                        System.out.println("Участник " + personList.get(j).getId() + " не смог перелезть через стену " + i);
                        System.out.println("Он выбывает из гонки.");
                        personList.remove(j);
                        j--;
                    }
                } else {
                    if(barriers.get(i).isPassing(personList.get(j).getLengthLimit())) {
                        System.out.println("Участник " + personList.get(j).getId() + " смог пробежать по дорожке " + i);
                    } else {
                        System.out.println("Участник " + personList.get(j).getId() + " не смог пробежать по дорожке " + i);
                        System.out.println("Он выбывает из гонки.");
                        personList.remove(j);
                        j--;
                    }
                }
                System.out.println("********");
            }
        }
        System.out.println("Cписок участников, которые смогли успешно прошли испытания:");

        for (int i = 0; i < personList.size(); i++) {
            System.out.println("участник " + personList.get(i).getId());
        }
    }

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        fillPersonList(personList);

        List<Barrier> barriers = new ArrayList<>();
        fillBarrierList(barriers);

        race(personList, barriers);
    }
}
