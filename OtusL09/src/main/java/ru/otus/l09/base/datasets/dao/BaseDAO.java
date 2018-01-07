package ru.otus.l09.base.datasets.dao;

import ru.otus.l09.base.executor.Executor;
import ru.otus.l09.base.executor.SqlCreator;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseDAO {
    protected Connection connection;

    public <T> void save(T t) throws IllegalAccessException, SQLException {
        Map<String, String> columns = getColumns(t);
        Table tableName = t.getClass().getAnnotation(javax.persistence.Table.class);
        String table = tableName.name();
        addToDB(columns, table);
    }

    public abstract <T> T load(long id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;

    private void addToDB(Map<String, String> columns, String table) throws SQLException {
        try {
            connection.setAutoCommit(false);
            SqlCreator sqlCreator = new SqlCreator(columns, table);
            String s = sqlCreator.createSQL();
            Executor exec = new Executor(connection);
            exec.execUpdate(s);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private long saveOTO(Object obj) throws IllegalAccessException, SQLException {
        Map<String, String> columns = getColumns(obj);
        Table tableName = obj.getClass().getAnnotation(javax.persistence.Table.class);
        String table = tableName.name();
        long id = -1;
        try {
            connection.setAutoCommit(false);
            SqlCreator sqlCreator = new SqlCreator(columns, table);
            String s = sqlCreator.createSQL();
            PreparedStatement stm = connection.prepareStatement(s);
            ResultSet result = stm.executeQuery();
            result.next();
            id = result.getLong(1);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

        return id;
    }

    private Map<String, String> getColumns(Object obj) throws IllegalAccessException, SQLException {
        Class clazz = obj.getClass();
        Map<String, String> columns = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field f :
                fields) {
            f.setAccessible(true);
            Column column = f.getAnnotation(javax.persistence.Column.class);
            OneToOne oto = f.getAnnotation(javax.persistence.OneToOne.class);
            if (column != null) {
                String name = column.name();
                if (name.length() == 0) {
                    name = f.getName().toLowerCase();
                }
                String value = f.get(obj).toString();
                columns.put(name, value);
            } else if (oto != null) {
                String name = f.getName().toLowerCase();
                String idName = name + "_id";
                Object fieldValue = f.get(obj);
                long id = saveOTO(fieldValue);
                columns.put(idName, String.valueOf(id));
            }
        }

        return columns;
    }

    protected List<String> getById(String table, long id, Map<Field, String> fieldsAndColumns) throws SQLException {
        List<String> data = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        fieldsAndColumns.forEach((k, v) -> sb.append(", ")
                .append(v));
        sb.deleteCharAt(0);

        String select = "select" + sb.toString() + " from " + table + " where id = ?";
        PreparedStatement stm = connection.prepareStatement(select);
        stm.setLong(1, id);
        ResultSet result =  stm.executeQuery();
        result.next();
        int columnsCount = fieldsAndColumns.size();
        for (int i = 1; i <= columnsCount; i++) {
            data.add(result.getString(i));
        }

        return data;
    }

    protected Map<Field, String> getFieldsAndColumns(Field[] fields) {
        Map<Field, String> fieldsAndColumns = new HashMap<>();
        for (Field f :
                fields) {
            f.setAccessible(true);
            Column column = f.getAnnotation(javax.persistence.Column.class);
            OneToOne oto = f.getAnnotation(javax.persistence.OneToOne.class);
            if (column != null) {
                fieldsAndColumns.put(f, column.name());
            } else if (oto != null) {
                String name = f.getName().toLowerCase();
                String idName = name + "_id";
                fieldsAndColumns.put(f, idName);
            }
        }
        return fieldsAndColumns;
    }
}
