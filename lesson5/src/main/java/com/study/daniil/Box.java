package com.study.daniil;

import java.util.ArrayList;
import java.util.Objects;

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
        if(boxList.isEmpty()) {
            return 0.0f;
        }
        return boxList.get(0).getWeight()*boxList.size();
    }

    public void fillOtherBox(Box<T> otherBox) {
        if(this.equals(otherBox)) {
            return;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box<?> box = (Box<?>) o;
        return Objects.equals(boxList, box.boxList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxList);
    }
}
