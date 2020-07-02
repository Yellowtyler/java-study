package com.study;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static ArrayList<Integer> arrayList1 = new ArrayList<>();
    static ArrayList<Integer> arrayList2 = new ArrayList<>();
    static ArrayList<Integer> arrayList3 = new ArrayList<>();
    static  ArrayList<Integer> arrayList4 = new ArrayList<>();
    static  LinkedList<Integer> linkedList1 = new LinkedList<>();
    static LinkedList<Integer> linkedList2 = new LinkedList<>();
    static  LinkedList<Integer> linkedList3 = new LinkedList<>();
    static  LinkedList<Integer> linkedList4 = new LinkedList<>();
    static long[] timeArrayListArr = new long[4];
    static long[] timeLinkedListArr = new long[4];

    public static void createList() {
        for (int i = 0; i < 10; i++) {
            arrayList1.add(i);
            linkedList1.add(i);
        }
        for (int i = 0; i < 100; i++) {
            arrayList2.add(i);
            linkedList2.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            arrayList3.add(i);
            linkedList3.add(i);
        }
        for (int i = 0; i < 100000; i++) {
            arrayList4.add(i);
            linkedList4.add(i);
        }
    }

    //           10 100 10000 100000
    //ArrayList  2   0    1     1
    //LinkedList 4   4    116  1865
    public static void test1() {
        timeArrayListArr[0] = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            arrayList1.get(4);
        }
        System.out.println("Time of method get() ArrayList size 10: " + (System.currentTimeMillis() - timeArrayListArr[0]));
        timeLinkedListArr[0] = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            linkedList1.get(4);
        }
        System.out.println("Time of method get() LinkedList size 10: " + (System.currentTimeMillis() - timeLinkedListArr[0]));

        timeArrayListArr[1] = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            arrayList2.get(49);
        }
        System.out.println("Time of method get() ArrayList size 100: " + (System.currentTimeMillis() - timeArrayListArr[1]));
        timeLinkedListArr[1] = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            linkedList2.get(49);
        }
        System.out.println("Time of method get() LinkedList size 100: " + (System.currentTimeMillis() - timeLinkedListArr[1]));

        timeArrayListArr[2] = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            arrayList3.get(4999);
        }
        System.out.println("Time of method get() ArrayList size 10000: " + (System.currentTimeMillis() - timeArrayListArr[2]));
        timeLinkedListArr[2] = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            linkedList3.get(4999);
        }
        System.out.println("Time of method get() LinkedList size 10000: " + (System.currentTimeMillis() - timeLinkedListArr[2]));

        timeArrayListArr[3] = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            arrayList4.get(49999);
        }
        System.out.println("Time of method get() ArrayList size 100000: " + (System.currentTimeMillis() - timeArrayListArr[3]));
        timeLinkedListArr[3] = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            linkedList4.get(49999);
        }
        System.out.println("Time of method get() LinkedList size 100000: " + (System.currentTimeMillis() - timeLinkedListArr[3]));

    }

    //            100 10000 100000
    //ArrayList    0    4     316
    //LinkedList   0    42    4059
    public static void test2() {
        timeArrayListArr[1] = System.currentTimeMillis();
        for (int i = 0; i < arrayList2.size(); i++) {
            arrayList2.remove(arrayList2.size()/2-1);
        }
        System.out.println("Time of method remove() ArrayList size 100: " + (System.currentTimeMillis() - timeArrayListArr[1]));
        timeLinkedListArr[1] = System.currentTimeMillis();
        for (int i = 0; i <linkedList2.size(); i++) {
            linkedList2.remove(linkedList2.size()/2-1);
        }
        System.out.println("Time  of method remove() LinkedList size 100: " + (System.currentTimeMillis() - timeLinkedListArr[1]));

        timeArrayListArr[2] = System.currentTimeMillis();
        for (int i = 0; i < arrayList3.size(); i++) {
            arrayList3.remove(arrayList3.size()/2-1);
        }
        System.out.println("Time of method remove() ArrayList size 10000: " + (System.currentTimeMillis() - timeArrayListArr[2]));
        timeLinkedListArr[2] = System.currentTimeMillis();
        for (int i = 0; i < linkedList3.size(); i++) {
            linkedList3.remove(linkedList3.size()/2-1);
        }
        System.out.println("Time of method remove() LinkedList size 10000: " + (System.currentTimeMillis() - timeLinkedListArr[2]));

        timeArrayListArr[3] = System.currentTimeMillis();
        for (int i = 0; i < arrayList4.size(); i++) {
            arrayList4.remove(arrayList4.size()/2-1);
        }
        System.out.println("Time of method remove() ArrayList size 100000: " + (System.currentTimeMillis() - timeArrayListArr[3]));
        timeLinkedListArr[3] = System.currentTimeMillis();
        for (int i = 0; i < linkedList4.size(); i++) {
            linkedList4.remove(linkedList4.size()/2-1);
        }
        System.out.println("Time of method remove() LinkedList size 100000: " + (System.currentTimeMillis() - timeLinkedListArr[3]));
    }

    public static void main(String[] args) {
        createList();
        test1();
        test2();

//        Time List: 13817
//        Time Map: 17
        ArrayList<MyEntry> arrayList = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < 50000; i++) {
            arrayList.add(new MyEntry(i, random.nextInt()));
            map.put(i, random.nextInt());
        }
        long timeList = System.currentTimeMillis();
        int randomKey;
        for (int i = 0; i < 100000; i++) {
            randomKey = ThreadLocalRandom.current().nextInt(0, 49999);
            for (int j = 0; j < arrayList.size(); j++) {
                 if(arrayList.get(j).getKey() == randomKey) {
                     break;
                 }
            }
        }
        System.out.println("Time List: " + (System.currentTimeMillis() - timeList));
        long timeMap = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            randomKey = ThreadLocalRandom.current().nextInt(0, 49999);
            map.get(randomKey);
        }
        System.out.println("Time Map: " + (System.currentTimeMillis() - timeMap));
    }
}
