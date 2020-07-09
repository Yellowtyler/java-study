package com.study.daniil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {
    public static final int COMPETITORS_COUNT = 4;
    private List<Stage> stages;
    public List<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void begin() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Car[] cars = new Car[COMPETITORS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, 20 + (int) (Math.random() * 10));
            System.out.println(cars[i].getName() + " - " + cars[i].getSpeed());
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
    }
}
