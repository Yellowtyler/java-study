package com.study.daniil;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            ReflectionRepository<Student> repository = new ReflectionRepository<>("org.sqlite.JDBC", "jdbc:sqlite:main.db", Student.class);

            repository.createTable();
            repository.save(new Student(1L,"ivan",3));
            repository.save(new Student(2L,"igor",5));
            repository.save(new Student(3L,"sergey",4));

            ResultSet all = repository.findAll();
            while(all.next()) {
                System.out.println(all.getLong(1) + " | " + all.getString(2) + " | " + all.getInt(3));
            }
            System.out.println("*************");
            ResultSet student2 = repository.findById(2L);
            while(student2.next()) {
                System.out.println(student2.getLong(1) + " | " + student2.getString(2) + " | " + student2.getInt(3));
            }
            repository.delete(2L);

            repository.deleteAll();
            ResultSet all1 = repository.findAll();
            while(all1.next()) {
                System.out.println(all1.getLong(1) + " | " + all1.getString(2) + " | " + all1.getInt(3));
            }
            repository.disconnect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
