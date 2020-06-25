package com.study.daniil;

import com.study.daniil.exceptions.MyArrayDataException;
import com.study.daniil.exceptions.MyArraySizeException;

import java.util.List;

public class Main {

    public static boolean checkSize(String[][] arr) {
        if(arr.length != 4) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].length != 4) {
                return false;
            }
        }
        return true;
    }

    private static int sumElements(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if(!checkSize(arr)) {
            throw new MyArraySizeException("Длина массива должна быть 4x4!");
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    int elemInt = Integer.parseInt(arr[i][j]);
                    sum += elemInt;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Невозможно конвертировать значение", arr[i][j], i, j);
                }
            }
        }
        return sum;
    }

    public static void sumElementsImpl(String[][] arr) {
        try {
            System.out.println(sumElements(arr));
        } catch (MyArraySizeException sizeException) {
            System.out.println("Длина массива должна быть 4x4!");
        } catch (MyArrayDataException dataException) {
            System.out.println("Значение arr[" + dataException.getI() + "][" + dataException.getJ() + "]"
                    + " = '" + dataException.getStr() + "' не является числом!");
        }
    }

    public static void main(String[] args) {
        //обработка возможных исключений
        String[][] arr = {{"1","2","3","3"},{"2","4","4"},{"1","2","2"},{"3","4","5"}}; //неправильный размер массива
        String[][] arr1 = {{"1","2","3","a"},{"2","4","4","5"},{"1","2","2","3"},{"3","4","5","4"}}; // arr[0][3] = "a" не является числом!
        String[][] arr2 = {{"1","2","3","3"},{"2","4","4","5"},{"1","2","2","3"},{"3","4","5","4"}}; //ошибок нет, должен вывести 48

        sumElementsImpl(arr);
        sumElementsImpl(arr1);
        sumElementsImpl(arr2);
    }
}
