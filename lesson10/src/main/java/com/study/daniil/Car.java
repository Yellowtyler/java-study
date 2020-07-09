package com.study.daniil;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static boolean isStart = true;
    private static int finishedCars = 0;
    private static CountDownLatch cdl = new CountDownLatch(Race.COMPETITORS_COUNT);
    private static final Object lock1 = new Object();

    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    private synchronized void setFalseStarted() {
        isStart = false;
    }
    private synchronized void incFinishedCars() {
        finishedCars++;
    }
    @Override
    public synchronized void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdl.countDown();
            cdl.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (lock1) {
            if (isStart) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                setFalseStarted();
            }
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).overcome(this);
        }

        incFinishedCars();
        if(finishedCars == 1) {
            System.out.println(this.name + " - WIN");
        }
        else if(finishedCars == Race.COMPETITORS_COUNT) {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
    }
}

