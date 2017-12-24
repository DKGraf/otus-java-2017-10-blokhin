package ru.otus.l09.base;

import ru.otus.l09.executor.Executor;
import ru.otus.l09.executor.SqlBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
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
            SqlBuilder sqlBuilder = new SqlBuilder(columns, table);
            String s = sqlBuilder.createSQL();
            System.out.println(s);
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

    public Map<String, String> getUserById(String table, long id, List<String> columnsList) throws SQLException {
        Executor execT = new Executor(connection);
        Map<String, String> user = new HashMap<>();



        StringBuilder sb = new StringBuilder();
        columnsList.forEach(sb.append(", ")::append);
        sb.deleteCharAt(0);

        String select = "select" + sb.toString() + " from " + table + " where id = " + id;
        System.out.println(select);
        execT.execQuery(select, result -> {
            System.out.println(result);
            return result;
        });
        return user;
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
