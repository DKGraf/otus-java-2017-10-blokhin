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
import java.util.HashMap;
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


    //Сохраняет пользователя в базу данных. Если в пришедшем классе содержатся
    //поля, отличные от name и age, которые используются в базе, то такие поля иигнорируются
    public <T extends DataSet> void save(T user) throws IllegalAccessException, SQLException {
        Map<String, String> columns = getColumns(user);
        String name = "!User without name!";
        int age = -1;
        for (Map.Entry<String, String> entry :
                columns.entrySet()) {
            if (entry.getKey().equals("name")) {
                name = entry.getValue();
            } else if (entry.getKey().equals("age")) {
                age = Integer.parseInt(entry.getValue());
            }
        }
        dbs.addUser(name, age);
    }

    //Читает пользователя из базы данных. При инстанцировании класса используются поля
    //name и age базы данных. Остальные поля класса никаким образом не инициализируются,
    //что может вызвать проблемы при использовании такого класса.
    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String name = dbs.getUserName(id);
        int age = dbs.getUserAge(id);
        @SuppressWarnings("unchecked") T t = (T) Class.forName(clazz.getCanonicalName()).newInstance();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field f :
                fields) {
            f.setAccessible(true);
            if (f.getName().equals("name")) {
                f.set(t, name);
            } else if (f.getName().equals("age")) {
                f.set(t, age);
            }
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
