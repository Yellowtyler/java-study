package com.study;

import com.study.annotations.AfterSuite;
import com.study.annotations.BeforeSuite;
import com.study.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestRunner {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        start(SumTest.class);
    }

    static private void start(Class c) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object object = c.getDeclaredConstructor().newInstance();
        Method[] methods = c.getDeclaredMethods();
        if(checkIfSingle(methods, c) && checkNegativePriority(methods, c)) {
            Method beforeSuite = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(BeforeSuite.class)).findAny().orElseThrow();
            beforeSuite.invoke(object);

            ArrayList<Method> testMethods = (ArrayList<Method>) Arrays.stream(methods)
                    .filter(m -> m.isAnnotationPresent(Test.class))
                    .sorted((m1, m2) -> compare(m1.getAnnotation(Test.class).priority(), m2.getAnnotation(Test.class).priority()))
                    .collect(Collectors.toList());

            for (Method method: testMethods) {
                System.out.println(method.getName()+ ": ");
                method.invoke(object);
                System.out.println();
            }
            Method afterSuite = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(AfterSuite.class)).findAny().orElseThrow();
            afterSuite.invoke(object);
        }
    }


    static boolean checkIfSingle(Method[] methods, Class c) {
        boolean isBeforeSuiteSingle = false;
        boolean isAfterSuiteSingle = false;
        for(Method method : methods) {
            if(method.isAnnotationPresent(BeforeSuite.class) && !isBeforeSuiteSingle) {
                isBeforeSuiteSingle = true;
            }
            else if (method.isAnnotationPresent(BeforeSuite.class) && isBeforeSuiteSingle) {
                throw new RuntimeException("Error in class " + c.getName() + "! There must be only one method with Annotation BeforeSuite!");
            }
            if(method.isAnnotationPresent(AfterSuite.class) && !isAfterSuiteSingle) {
                isBeforeSuiteSingle = true;
            }
            else if (method.isAnnotationPresent(AfterSuite.class) && isAfterSuiteSingle) {
                throw new RuntimeException("Error in class " + c.getName() + "! There must be only one method with Annotation AfterSuite!");
            }
        }
        return true;
    }

    static boolean checkNegativePriority(Method[] methods, Class c) {
        for(Method method : methods) {
            if(method.isAnnotationPresent(Test.class)) {
                if(method.getAnnotation(Test.class).priority() < 1 || method.getAnnotation(Test.class).priority() > 10) {
                    throw new RuntimeException("Error in method " + method.getName() + "in class " + c.getName() + "Parameter priority can be only less than 10 or more than 1. ");
                }
            }
        }
        return true;
    }

    static int compare(int a, int b) {
        if(a > b) {
            return -1;
        } else if(a < b) {
            return 1;
        } else {
            return 0;
        }
    }
}
