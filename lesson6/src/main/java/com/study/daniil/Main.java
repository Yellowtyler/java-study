package com.study.daniil;

import java.util.*;

public class Main {
    static public void task1(String[] arr) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                int count = map.get(s);
                count++;
                map.put(s, count);
            }
        }
        List<String> uniqueElements = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            if(entry.getValue() == 1) {
                uniqueElements.add(entry.getKey());
            }
        }
        System.out.println("Список с уникальными словами: " + uniqueElements);
        System.out.println("*********");
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        String[] strs = {"Apple","Bear","Bear","Cat","Apple","Dog"};
        task1(strs);

        System.out.println("*********");
        System.out.println("Второе задание:");
        Phonebook phonebook = new Phonebook();
        phonebook.add("+32235523","Иванов");
        phonebook.add("+31111111","Иванов");
        phonebook.add("+38563443","Иванов");
        phonebook.add("+31341444","Новиков");
        phonebook.add("+24141535","Щедров");

        phonebook.add("+32235523","Алексеев"); //не удастся добавить, так как такой номер уже существует.
        phonebook.add("52352532","Алексеев"); //не удатся, так как нету +
        phonebook.add("Алексеев","+32235523"); //не удастся, так как порядок параметров перепутан
        phonebook.add("+313371337","56434643"); //не удастся, второй параметр не фамилия
        phonebook.add("+313371337","Щедров2"); //не удастся, второй параметр содержит цифру

        System.out.println(phonebook.getPhoneBookMap());

        List<String> listIvanov = phonebook.getPhonesByName("Иванов");

        List<String> shedrov = phonebook.getPhonesByName("Щедров");

    }
}
