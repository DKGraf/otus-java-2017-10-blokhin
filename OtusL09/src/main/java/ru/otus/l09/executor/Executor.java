package ru.otus.l09.executor;

import ru.otus.l09.base.DBService;
import ru.otus.l09.base.ResultHandler;
import ru.otus.l09.base.dataset.DataSet;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public <T extends DataSet> void save(T user) throws IllegalAccessException, SQLException {
        Field[] fields = user.getClass().getDeclaredFields();
        String name = null;
        int age = 0;
        for (Field f :
                fields) {
            f.setAccessible(true);
            String fieldName = f.getName();
            if ("name".equals(fieldName)) {
                name = f.get(user).toString();
            } else if ("age".equals(fieldName)) {
                age = Integer.parseInt(f.get(user).toString());
            }
        }
        dbs.addUser(name, age);
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String name = dbs.getUserName(id);
        int age = dbs.getUserAge(id);
        return clazz.getConstructor(long.class, String.class, int.class).newInstance(id, name, age);
    }
}
