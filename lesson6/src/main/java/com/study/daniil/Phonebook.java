package com.study.daniil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phonebook {
    private Map<String, String> phoneBookMap;

    public Map<String, String> getPhoneBookMap() {
        return phoneBookMap;
    }

    public Phonebook() {
        phoneBookMap = new HashMap<>();
    }

    public void add(String phone, String surname) {
        if(phoneBookMap.containsKey(phone)) {
           System.out.println("Ошибка! Этот номер уже существует!");
           return;
        }
        Pattern patternForCheckSurname = Pattern.compile("\\+[0-9]+|[0-9]+|.*\\d.*");
        Matcher matcherSurname = patternForCheckSurname.matcher(surname);
        if(matcherSurname.matches()) {
            System.out.println("Ошибка! Второй параметр является фамилией. Например: Иванов, Егоров.");
            return;
        }
        Pattern patternForCheckPhone = Pattern.compile("\\+[0-9]+");
        Matcher matcherPhone = patternForCheckPhone.matcher(phone);
        if(!matcherPhone.matches()) {
            System.out.println("Ошибка! Первый параметр должен быть номер телефона с + в начале. Например: +5333644");
            return;
        }
        phoneBookMap.put(phone, surname);
    }

    public List<String> getPhonesBySurname(String surname) {
        if(!phoneBookMap.containsValue(surname)) {
            System.out.println("Номера телефонов с такой фамилией не существует!");
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry: phoneBookMap.entrySet()) {
            if(entry.getValue().equals(surname)) {
                list.add(entry.getKey());
            }
        }
        System.out.println("Номера с фамилией " + surname +": " + list);
        return list;
    }
}
