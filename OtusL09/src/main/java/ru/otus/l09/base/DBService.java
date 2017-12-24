package ru.otus.l09.base;

import ru.otus.l09.executor.Executor;
import ru.otus.l09.executor.SqlCreator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.otus.l09.connection.ConnectionHelper.getConnection;

/**
 * Типы полей немного изменены по сравнению с заданием,
 * если говорить точнее, то они адаптированы к реалиям
 * PostgreSQL.
 */

public class DBService implements AutoCloseable {
    private static final String CREATE_TABLE_USERS = "create table if not exists users (" +
            "id bigserial not null primary key, " +
            "name varchar, " +
            "age integer)";
    private static final String CREATE_TABLE_ANOTHER_USERS = "create table if not exists ausers (" +
            "id bigserial not null primary key, " +
            "name varchar, " +
            "age integer, " +
            "address varchar, " +
            "phone varchar)";
    private static final String DELETE_USERS = "drop table users";
    private static final String DELETE_ANOTHER_USERS = "drop table ausers";
    private final Connection connection;


    public DBService() {
        connection = getConnection();
    }

    public void addUser(Map<String, String> columns, String table) throws SQLException {
        try {
            Executor exec = new Executor(connection);
            connection.setAutoCommit(false);
            SqlCreator sqlCreator = new SqlCreator(columns, table);
            String s = sqlCreator.createSQL();
            exec.execUpdate(s);
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
        exec.execUpdate(DELETE_ANOTHER_USERS);
    }

    public void prepareTables() throws SQLException {
        Executor exec = new Executor(connection);
        exec.execUpdate(CREATE_TABLE_USERS);
        exec.execUpdate(CREATE_TABLE_ANOTHER_USERS);
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
