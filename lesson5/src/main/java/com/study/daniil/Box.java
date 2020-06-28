package com.study.daniil;

import java.util.ArrayList;

public class Box<T extends Fruit> implements Comparable<Box> {
    private ArrayList<T> boxList;

    public Box(ArrayList<T> boxList) {
        this.boxList = boxList;
    }
    public Box() {
        boxList = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        boxList.add(fruit);
    }

    public ArrayList<T> getBoxList() {
        return boxList;
    }

    public float getWeight() {
        return boxList.get(0).getWeight()*boxList.size();
    }

    public void fillOtherBox(Box<T> otherBox) {
        otherBox.getBoxList().addAll(boxList);
        boxList.removeAll(boxList);
    }

    @Override
    public int compareTo(Box box) {
        if(Math.abs(this.getWeight() - box.getWeight()) < 0.0001f) {
            return 0;
        } else if ((this.getWeight() - box.getWeight()) > 0.0001f) {
            return 1;
        } else {
            return -1;
        }
    }
}
