package com.example.lesson1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //Task 1
    public static boolean isSumInInterval(int a, int b) {
        return (a + b) >= 10 && (a + b) <= 20;
    }

    //Task 2
    public static void checkSign(int n) {
        if(n >= 0) {
            System.out.println(n + " is positive.");
        } else {
            System.out.println(n + " is negative.");
        }
    }

    //Task 3
    public static boolean isNegative(int a) {
        return a < 0;
    }

    //Task 4
    public static void helloUser(String user) {
        System.out.println("Привет, "+user + "!");
    }

    //Task 5
    public static void swapOneAndZero(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 1) {
                arr[i] = 0;
            } else if(arr[i] == 0) {
                arr[i] = 1;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //Task 6
    public static void fillArray() {
        int[] arr = new int[8];
        arr[0] = 2;
        for (int i = 1; i < 8; i++) {
            arr[i] = arr[i-1]+3;
        }
        System.out.println(Arrays.toString(arr));
    }

    //Task 7
    public static void substractElementLessThan6() {
        int [] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < 12; i++) {
            if(arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //Task 8
    public static void changeDiagonalMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i == j) {
                    matrix[i][j] = 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    //Task 9
    public static void findMaxAndMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(min > arr[i]) {
                min = arr[i];
            }
            if(max < arr[i]) {
                max = arr[i];
            }
        }
        System.out.println("Максимальный элемент в массиве равен: " + max);
        System.out.println("Минимальный элемент в массиве равен: " + min);
    }

    //Task 10
    public static void checkLeap(int year) {
        if((year % 4 == 0 && year % 100 !=0) || year % 400 == 0) {
            System.out.println(year + " является високосным.");
        } else {
            System.out.println(year + " не является високосным.");
        }
    }

    //Task 11
    public static boolean checkBalance(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int left = 0;
            int right = 0;
            for (int j = 0; j < arr.length; j++) {
                if(j <= i) {
                    left += arr[j];
                } else {
                    right += arr[j];
                }
            }
            if(left == right) {
                return true;
            }
        }
        return false;
    }

    public static int[] createArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите длину массива: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Введите значения элементов массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("Задание 1:");
        System.out.println(isSumInInterval(10,2));
        System.out.println();

        System.out.println("Задание 2:");
        checkSign(-2);
        System.out.println();

        System.out.println("Задание 3:");
        System.out.println(isNegative(-1));
        System.out.println();

        System.out.println("Задание 4:");
        helloUser("Даня");
        System.out.println();

        System.out.println("Задание 5:");
        int[] arr = {1,0,0,0,1};
        swapOneAndZero(arr);
        System.out.println();

        System.out.println("Задание 6:");
        fillArray();
        System.out.println();

        System.out.println("Задание 7:");
        substractElementLessThan6();
        System.out.println();

        System.out.println("Задание 8:");
        System.out.print("Введите размеры n матрицы n*n: ");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        System.out.println("Введите элементы матрицы: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                 matrix[i][j] = scanner.nextInt();
            }
            System.out.println();
        }
        changeDiagonalMatrix(matrix);

        System.out.println("Задание 9:");
        int[] arr1 = createArray();
        findMaxAndMin(arr1);
        System.out.println();

        System.out.println("Задание 10:");
        int year = scanner.nextInt();
        checkLeap(year);
        System.out.println();

        System.out.println("Задание 11:");
        int[] arr2 = createArray();
        System.out.println(checkBalance(arr2));
    }
}
