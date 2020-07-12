package com.study;

public class AssertClass {
    static <T> void assertEquals(T expected,  T actual) {
            if(expected.equals(actual)) {
                System.out.println("Test passed!");
            } else {
                System.out.println("Test hasn't passed! Expected: " + expected + "\n" + "Actual: " + actual);
            }
    }
    static void assertEquals(int expected, int actual) {
        if(expected == actual) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test hasn't passed! Expected: " + expected + "\n" + "Actual: " + actual);
        }
    }
}
