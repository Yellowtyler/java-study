package com.study.danya;

import java.util.List;

public class Group {

    private String name;
    private List<Worker> workerList;

    public Group(String name, List<Worker> workerList) {
        this.name = name;
        this.workerList = workerList;
    }

    public void removeAll() {
        workerList.removeAll(workerList);
        System.out.println("Все сотрудники успешно удалены из группы!");
    }

    public void addNewWorker(Worker worker) {
        if(workerList.size() < 10) {
            workerList.add(worker);
            System.out.println("Сотрудник успешно добавлен!");
        } else {
            System.out.println("Число сотрудников не должно превышать 10!");
        }
    }

    public void removeWorker(int i) {
        try {
            workerList.remove(i);
            System.out.println("Сотрудник успешно удален!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printGroupInformation() {
        for (Worker worker : workerList) {
            worker.printInformation();
        }
    }
}
