package com.study.daniil;

import com.study.daniil.annotations.DbColumn;
import com.study.daniil.annotations.DbId;
import com.study.daniil.annotations.DbTable;
import com.study.daniil.exception.AnnotationException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReflectionRepository<T> {
    final private Statement statement;
    final private Connection connect;
    final private Class<T> tClass;
    final private String tableName;

    public ReflectionRepository(String driver, String dataBase, Class<T> cls) throws AnnotationException, ClassNotFoundException, SQLException {
        this.tClass = cls;
        if(!tClass.isAnnotationPresent(DbTable.class)) {
            throw new AnnotationException("Annotation Dbtable isn't represented in class " + tClass.toString());
        }
        Class.forName(driver);
        this.connect = DriverManager.getConnection(dataBase);
        this.statement = connect.createStatement();
        this.tableName = (tClass.getAnnotation(DbTable.class)).name();
    }

    public void save(T object) throws Exception {
        ArrayList<Field> fields = (ArrayList<Field>) Arrays.stream(tClass.getDeclaredFields()).filter(f -> f.isAnnotationPresent(DbColumn.class)).collect(Collectors.toList());
        fields.stream().forEach(f -> f.setAccessible(true));

        StringBuilder queryBilder = new StringBuilder("INSERT INTO " + tableName + " (");
        String fieldsNameInsert = String.join(", ", fields.stream().map(f -> f.getName()).collect(Collectors.toList()));
        queryBilder.append(fieldsNameInsert + ") ");
        queryBilder.append("VALUES (");
        for (Field field : fields) {
            if(field.getType() == String.class) {
                queryBilder.append("\"" + field.get(object).toString()+ "\"" + ", ");
            } else {
                queryBilder.append(field.get(object).toString() + ", ");
            }
        }
        queryBilder.delete(queryBilder.length()-2, queryBilder.length());
        queryBilder.append(");");
        statement.executeUpdate(queryBilder.toString());
        ResultSet rs= statement.getGeneratedKeys();
        if (rs.next()) {
            System.out.println("Запись с id " + rs.getLong(1) + " успешно было добавлено");
        }
    }

    public void delete(Long id) throws SQLException {
        Field idField = Arrays.stream(tClass.getDeclaredFields()).filter(f -> f.isAnnotationPresent(DbId.class)).findAny().orElseThrow();
        idField.setAccessible(true);
        PreparedStatement ps = connect.prepareStatement("DELETE FROM " + tableName + " WHERE " + idField.getName() + " = ?");
        ps.setLong(1, id);
        System.out.println(ps.executeUpdate() + " запись была удалена.");
    }

    public void deleteAll() throws SQLException {
        System.out.println(statement.executeUpdate("DELETE FROM " + tableName + ";") + " записей было удалено.");
    }

    public ResultSet findById(Long id) throws SQLException {
        Field idField = Arrays.stream(tClass.getDeclaredFields()).filter(f -> f.isAnnotationPresent(DbId.class)).findAny().orElseThrow();
        idField.setAccessible(true);
        PreparedStatement ps = connect.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idField.getName() + " = ?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public ResultSet findAll() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
        return rs;
    }

    public void createTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS student (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "score INTEGER);");
        System.out.println("Таблица была создана.");
    }

    public void disconnect() throws SQLException {
        if(statement != null) {
            statement.close();
        }
        if(connect != null) {
            connect.close();
        }
    }
}
