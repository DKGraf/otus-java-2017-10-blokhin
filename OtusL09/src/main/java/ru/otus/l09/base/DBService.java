package ru.otus.l09.base;

import ru.otus.l09.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

import static ru.otus.l09.connection.ConnectionHelper.getConnection;

/**
 * Типы полей немного изменены по сравнению с заданием,
 * если говорить точнее, то они адаптированы к реалиям
 * PostgreSQL.
 */

public class DBService implements AutoCloseable {
    private static final String CREATE_TABLE_USERS = "create table if not exists users (" +
            "id bigserial not null primary key, " +
            "name varchar(255), " +
            "age integer)";
    private static final String INSERT_INTO_USERS = "insert into users (name, age) values ('%s', %s)";
    private static final String DELETE_USERS = "drop table users";
    private static final String SELECT_USERS_NAME = "select name from users where id=%s";
    private static final String SELECT_USERS_AGE = "select age from users where id=%s";
    private final Connection connection;


    public DBService() {
        connection = getConnection();
    }

    public void addUser(String name, int age) throws SQLException {
        try {
            Executor exec = new Executor(connection);
            connection.setAutoCommit(false);
            exec.execUpdate(String.format(INSERT_INTO_USERS, name, age));
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void deleteTables() throws SQLException {
        Executor exec = new Executor(connection);
        exec.execUpdate(DELETE_USERS);
    }

    public void prepareTables() throws SQLException {
        Executor exec = new Executor(connection);
        exec.execUpdate(CREATE_TABLE_USERS);
    }

    public String getUserName(long id) throws SQLException {
        Executor execT = new Executor(connection);

        return execT.execQuery(String.format(SELECT_USERS_NAME, id), result -> {
            result.next();
            return result.getString("name");
        });
    }

    public int getUserAge(long id) throws SQLException {
        Executor execT = new Executor(connection);

        return execT.execQuery(String.format(SELECT_USERS_AGE, id), result -> {
            result.next();
            return result.getInt("age");
        });
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public String getMetaData() {
        try {
            return "Connected to: " + connection.getMetaData().getURL() + "\n" +
                    "DB name: " + connection.getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + connection.getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + connection.getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
