package com.study.daniil.exceptions;

public class MyArrayDataException extends Exception{
    private String str;
    private int i;
    private int j;
    public MyArrayDataException(String message, String str, int i, int j) {
        super(message);
        this.str = str;
        this.i = i;
        this.j = j;
    }

    public String getStr() {
        return str;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
