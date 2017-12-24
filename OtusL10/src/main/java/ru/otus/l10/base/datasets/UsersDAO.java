package ru.otus.l10.base.datasets;

import ru.otus.l10.base.executor.Executor;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDAO {
    private final Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUser(Map<String, String> columns, String table) throws SQLException {
        try {
            Executor exec = new Executor(connection);
            connection.setAutoCommit(false);
            ru.otus.l09.executor.SqlCreator sqlCreator = new ru.otus.l09.executor.SqlCreator(columns, table);
            String s = sqlCreator.createSQL();
            exec.execUpdate(s);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<String> getUserById(String table, long id, List<String> columnsList) throws SQLException {
        Executor execT = new Executor(connection);
        List<String> userData = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        columnsList.forEach((v) -> sb.append(", ")
                .append(v));
        sb.deleteCharAt(0);

        String select = "select" + sb.toString() + " from " + table + " where id = " + id;
        execT.execQuery(select, result -> {
            result.next();
            int columnsCount = columnsList.size();
            for (int i = 1; i <= columnsCount; i++) {
                userData.add(result.getString(i));
            }
            return result;
        });

        return userData;
    }

    public <T extends DataSet> void save(T user, String table) throws IllegalAccessException, SQLException {
        Map<String, String> columns = getColumns(user);
        addUser(columns, table);
    }

    public <T extends DataSet> T load(long id, Class<T> clazz, String table) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        @SuppressWarnings("unchecked") T t = (T) Class.forName(clazz.getCanonicalName()).newInstance();
        List<String> columnsList = new ArrayList<>();
        List<String> userData = new ArrayList<>();
        List<Field> annotatedFields = new ArrayList<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            Column column = f.getAnnotation(javax.persistence.Column.class);
            if (column != null) {
                columnsList.add(column.name());
                annotatedFields.add(f);
            }
        }
        userData = getUserById(table, id, columnsList);

        for (int i = 0; i < annotatedFields.size(); i++) {
            Field f = annotatedFields.get(i);
            String s = userData.get(i);
            f = ru.otus.l09.executor.FieldSetter.setField(f, s, t);
        }
        return t;
    }

    private Map<String, String> getColumns(Object user) throws IllegalAccessException {
        Class clazz = user.getClass();
        Map<String, String> columns = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            Column column = f.getAnnotation(javax.persistence.Column.class);
            if (column != null) {
                String name = column.name();
                if (name.length() == 0) {
                    name = f.getName().toLowerCase();
                }
                String value = f.get(user).toString();
                columns.put(name, value);
            }
        }
        return columns;
    }
}
