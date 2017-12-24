package ru.otus.l09.executor;

import ru.otus.l09.base.DBService;
import ru.otus.l09.base.ResultHandler;
import ru.otus.l09.base.dataset.DataSet;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Executor {
    private final Connection connection;
    private DBService dbs;


    public Executor(Connection connection) {
        this.connection = connection;
    }

    public Executor(Connection connection, DBService dbs) {
        this.connection = connection;
        this.dbs = dbs;
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            return handler.handle(result);
        }
    }

    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            return stmt.getUpdateCount();
        }
    }

    public <T extends DataSet> void save(T user, String table) throws IllegalAccessException, SQLException {
        Map<String, String> columns = getColumns(user);
        dbs.addUser(columns, table);
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
        userData = dbs.getUserById(table, id, columnsList);

        for (int i = 0; i < annotatedFields.size(); i++) {
            Field f = annotatedFields.get(i);
            String s = userData.get(i);
            f = FieldSetter.setField(f, s, t);
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
