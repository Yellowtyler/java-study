package com.study;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {
    //Задача 1:
    //многопоточно: 529
    //однопоточно: 78
    //Задача 2:
    //многопоточно: 63
    //однопоточно: 109
    //Следовательно, вариант решения с использованием parallelStream - самый быстрый.
    public static void main(String[] args) {
        //Задание 1
        int[] arr = new int[100000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(0, 100000);
        }

        FindMaxParallel findMaxParallelClass = new FindMaxParallel(arr);
        long parallelTimeTask1 = System.currentTimeMillis();
        int result = ForkJoinPool.commonPool().invoke(findMaxParallelClass);
        System.out.println("Время выполнения задачи 1 многопоточно составляет: " + (System.currentTimeMillis() - parallelTimeTask1));
        System.out.println(result);

        int max = Integer.MIN_VALUE;
        long consistentTimeTask1 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            if(max < arr[i]) {
                max = arr[i];
            }
        }
        System.out.println("Время выполнения задачи 1 однопоточно составляет: " + (System.currentTimeMillis() - consistentTimeTask1));
        System.out.println(max);

        //Задание 2
        long parallelTimeTask2 = System.currentTimeMillis();
        int resPar = IntStream.of(arr).parallel().max().getAsInt();
        System.out.println("Время выполнения задачи 2 многопоточно составляет: " + (System.currentTimeMillis() - parallelTimeTask2));
        System.out.println(resPar);

        long consistentTimeTask2 = System.currentTimeMillis();
        int resCon = IntStream.of(arr).max().getAsInt();
        System.out.println("Время выполнения задачи 2 однопоточно составляет: " + (System.currentTimeMillis() - consistentTimeTask2));
        System.out.println(resCon);
    }
}
