package com.study.daniil;


import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    //Задание 1
    public static <T> void swapTwoElements(T[] arr, int a, int b) {
        try {
            T temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        } catch (ArrayIndexOutOfBoundsException exp) {
            System.out.println("Не удалось поменять местами элементы. " + exp.getMessage());
        }
    }
    //Задание 2
    public static <T> ArrayList<T> toArrayList(T[] arr){
        ArrayList<T> list = new ArrayList<>(Arrays.asList(arr));
        return list;
    }

    public static void main(String[] args) {
        System.out.println("Проверка первого задания:");
        Integer[] ints = {0, 1, 2, 3, 4};
        swapTwoElements(ints, 0, 2);
        System.out.println(Arrays.toString(ints));

        String[] strings ={"ab","jf","a","g"};
        swapTwoElements(strings, 0, 3);
        System.out.println(Arrays.toString(strings));

        System.out.println("Проверка второго задания:");
        ArrayList<Integer> listInts = toArrayList(ints);
        System.out.println(listInts.toString());
        ArrayList<String> listStr = toArrayList(strings);
        System.out.println(listStr.toString());

        System.out.println("Проверка третьего задания:");
        Box<Apple> appleBox = new Box<Apple>();
        for (int i = 0; i < 5; i++) {
            appleBox.getBoxList().add(new Apple(1.0f));
        }

        System.out.println("Вес первой коробки c яблоками: " + appleBox.getWeight());

        Box<Apple> appleBox1 = new Box<Apple>();
        for (int i = 0; i < 4; i++) {
            appleBox1.getBoxList().add(new Apple(1.0f));
        }
        System.out.println("Cравнение первой коробки с яблоками со второй:" + appleBox.compareTo(appleBox1));

        Box<Orange> orangeBox = new Box<Orange>();
        for (int i = 0; i < 4; i++) {
            orangeBox.getBoxList().add(new Orange(1.5f));
        }
        System.out.println("Вес коробки с апельсинами: " + orangeBox.getWeight());

        System.out.println("Cравнение первой коробки с яблокам и коробки с апельсинами:" +appleBox.compareTo(orangeBox));

        System.out.println("Пересып яблок из первой коробки во вторую: ");
        appleBox.fillOtherBox(appleBox1);
        System.out.println("Количество фруктов в первой после пересыпа: " + appleBox.getBoxList().size());
        System.out.println("Количество фруктов во второй после пересыпа: " +appleBox1.getBoxList().size());
    }
}
