package com.study.daniil;

import com.study.daniil.annotations.DbColumn;
import com.study.daniil.annotations.DbId;
import com.study.daniil.annotations.DbTable;

@DbTable(name="student")
public class Student {
    @DbId
    private Long id;
    @DbColumn
    private String name;
    @DbColumn
    private Integer score;

    public Student(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
