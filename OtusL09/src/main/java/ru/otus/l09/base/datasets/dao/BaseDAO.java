package ru.otus.l09.base.datasets.dao;

import ru.otus.l09.base.executor.FieldSetter;
import ru.otus.l09.base.executor.SqlCreator;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
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

    public <T> void save(T t) throws IllegalAccessException, SQLException, ClassNotFoundException {
        Map<String, String> columns = getColumns(t);
        Table tableName = t.getClass().getAnnotation(javax.persistence.Table.class);
        String table = tableName.name();
        long id = saveToDB(columns, table);
        for (Map.Entry<String, String> entry :
                columns.entrySet()) {
            if (entry.getKey().endsWith("_otm")) {
                saveOTM(t, id);
            }
        }
    }

    public abstract <T> T load(long id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;

    public <T> T load(long id, Class clazz) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        @SuppressWarnings("unchecked") T t = (T) Class.forName(clazz.getCanonicalName()).newInstance();
        List<String> userData;
        Table tableName = (Table) clazz.getAnnotation(Table.class);
        Field[] fields = clazz.getDeclaredFields();
        String table = tableName.name();
        Map<Field, String> fieldsAndColumns = getFieldsAndColumns(fields);
        userData = getById(table, id, fieldsAndColumns);

        int index = 0;
        for (Map.Entry<Field, String> entry :
                fieldsAndColumns.entrySet()) {
            String value = userData.get(index);
            FieldSetter.setField(entry.getKey(), value, t);
            index++;
        }

        return t;
    }

    private long saveToDB(Map<String, String> columns, String table) throws SQLException {
        return addToDB(columns, table);
    }

    private long saveOTOtoDB(Object obj) throws IllegalAccessException, SQLException {
        Map<String, String> columns = getColumns(obj);
        Table tableName = obj.getClass().getAnnotation(javax.persistence.Table.class);
        String table = tableName.name();
        return addToDB(columns, table);
    }

    private long addToDB(Map<String, String> columns, String table) throws SQLException {
        long id = -1;
        try {
            connection.setAutoCommit(false);
            String sql = SqlCreator.createSQL(columns, table);
            PreparedStatement stm = connection.prepareStatement(sql);
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

    private <T> void saveOTM(T t, long id) throws IllegalAccessException, SQLException, ClassNotFoundException {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            OneToMany otm = f.getAnnotation(javax.persistence.OneToMany.class);
            if (otm != null) {
                Object fieldValue = f.get(t);
                Type type = f.getGenericType();
                String fullType = type.getTypeName();
                String typeName = fullType.substring(fullType.indexOf("<") + 1, fullType.length() - 1);
                Class clazz = Class.forName(typeName);
                Table table = (Table) clazz.getAnnotation(Table.class);
                String tableName = table.name();

                for (Object obj :
                        (ArrayList) fieldValue) {
                    Map<String, String> columns = getColumns(obj);
                    String idName = otm.mappedBy() + "_id";
                    String sql = SqlCreator.createSQLforOTM(columns, tableName, id, idName);
                    System.out.println(sql);
                    try {
                        connection.setAutoCommit(false);
                        PreparedStatement stm = connection.prepareStatement(sql);
                        stm.executeQuery();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                    } finally {
                        connection.setAutoCommit(true);
                    }
                }

            }
        }
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
            OneToMany otm = f.getAnnotation(javax.persistence.OneToMany.class);
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
                long id = saveOTOtoDB(fieldValue);
                columns.put(idName, String.valueOf(id));
            } else if (otm != null) {
                String name = f.getName().toLowerCase() + "_otm";
                String fieldName = f.getName();
                columns.put(name, fieldName);
            }
        }

        return columns;
    }

    protected List<String> getById(String table, long id, Map<Field, String> fieldsAndColumns) throws SQLException {
        List<String> data = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Field, String> entry :
                fieldsAndColumns.entrySet()) {
            String value = entry.getValue();
            sb.append(", ");
            if (!value.endsWith("_mto")) {
                sb.append(value);
            }
        }
        sb.deleteCharAt(0);

        String select = "select" + sb.toString() + " from " + table + " where id = ?";
        PreparedStatement stm = connection.prepareStatement(select);
        stm.setLong(1, id);
        ResultSet result = stm.executeQuery();
        result.next();
        int columnsCount = fieldsAndColumns.size();
        for (int i = 1; i <= columnsCount; i++) {
            data.add(result.getString(i));
        }

        return data;
    }

    protected List<String> getByIdMTO(String table, long id, Map<Field, String> fieldsAndColumns) {
        List<String> data = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        fieldsAndColumns.forEach((k, v) -> sb.append(", ")
                .append(v));
        sb.deleteCharAt(0);

        String select = "select" + sb.toString() + " from " + table + " where id = ?";
        return null;
    }

    protected Map<Field, String> getFieldsAndColumns(Field[] fields) {
        Map<Field, String> fieldsAndColumns = new HashMap<>();
        for (Field f :
                fields) {
            f.setAccessible(true);
            Column column = f.getAnnotation(javax.persistence.Column.class);
            OneToOne oto = f.getAnnotation(javax.persistence.OneToOne.class);
            ManyToOne mto = f.getAnnotation(javax.persistence.ManyToOne.class);
            if (column != null) {
                fieldsAndColumns.put(f, column.name());
            } else if (oto != null) {
                String name = f.getName().toLowerCase();
                String idName = name + "_id";
                fieldsAndColumns.put(f, idName);
            } else if (mto != null) {
                String name = f.getName().toLowerCase();
                String idName = name + "_mto";
                fieldsAndColumns.put(f, idName);
            }
        }
        return fieldsAndColumns;
    }
}
