package com.study;

import com.study.annotations.AfterSuite;
import com.study.annotations.BeforeSuite;
import com.study.annotations.Test;

public class SumTest {
    private int a = 0;
    private int b = 0;

    @BeforeSuite
    void init() {
        a = 5;
        b = 10;
        System.out.println("init");
    }

    @Test(priority = 10)
    public void shouldEquals15Test() {
        int expected = 15;
        int actual = a + b;
        AssertClass.assertEquals(expected, actual);
    }

    @Test(priority = 5)
    public void shouldEquals20Test() {
        int expected = 20;
        int actual = a + b + 5;
        AssertClass.assertEquals(expected, actual);
    }

    @AfterSuite
    void finish() {
        a = 0;
        b = 0;
        System.out.println("finish");
    }
}
