package com.study;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

   //task 1
   public static String findStrWithLengthMore5(String str) {
       return Arrays.stream(str.split(" "))
               .filter(s->s.length() > 5)
               .collect(Collectors.joining(" "));
   }

   //task 2
   public static List<String> findUnique(String[][] arr) {
       return Arrays.stream(arr)
               .flatMap(Arrays::stream)
               .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
               .entrySet()
               .stream()
               .filter(x->x.getValue()==1)
               .map(x->x.getKey()).collect(Collectors.toList());
   }

   //task 3
    public static int getSumEvenNums(int[] nums) {
       return Arrays.stream(nums).filter(num -> num%2 == 0).sum();
    }

    //task 4
    public static int getSumStrLength(String[] strings) {
       return Arrays.stream(strings).mapToInt(String::length).sum();
    }

    //task 5
    public static List<String> getFirstThreeWords(String [] arr) {
       return  Arrays.stream(arr).limit(3).sorted().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String strings = "abcd d fasdafs sfaf adsaga fffff";
        System.out.println(findStrWithLengthMore5(strings));

        String[][] arr = {{"a","v","a","c","d"},
                          {"b","c","t","d","e"},
                          {"e","a","b","c","d"},
                          {"a","a","a","z","a"},
                          {"a","b","c","d","e"}};
        System.out.println(findUnique(arr));

        int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12};
        System.out.println(getSumEvenNums(nums));

        String[] strings1 = {"abd","sfaf","eee"};
        System.out.println(getSumStrLength(strings1));

        String[] strings2 = {"c","a","b","e","g"};
        System.out.println(getFirstThreeWords(strings2));
    }
}
