package ru.otus.l09.base;

import ru.otus.l09.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

import static ru.otus.l09.connection.ConnectionHelper.getConnection;


public class DBService implements AutoCloseable {
    private static final String CREATE_TABLE_USER = "create table if not exists user (id bigint(20)" +
            " NOT NULL auto_increment, name varchar(255), age int(3), primary key (id))";
    private static final String INSERT_INTO_USER = "insert into user (name) values(?)";
    private static final String INSERT_USER = "insert into user (name) values ('%s')";
    private static final String DELETE_USER = "drop table user";
    private static final String SELECT_USER = "select name from user where id=%s";
    private final Connection connection;


    public DBService() {
        connection = getConnection();
    }

    public void deleteTables() throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execUpdate(DELETE_USER);
        System.out.println("Table dropped");
    }

    public void prepareTables() throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execUpdate(CREATE_TABLE_USER);
        System.out.println("Table created");
    }

    public String getUserName(int id) throws SQLException {
        Executor execT = new Executor(getConnection());

        return execT.execQuery(String.format(SELECT_USER, id), result -> {
            result.next();
            return result.getString("name");
        });
    }

    public void addUsers(String... names) throws SQLException {
        try {
            Executor exec = new Executor(getConnection());
            getConnection().setAutoCommit(false);
            exec.execUpdate(INSERT_INTO_USER, statement -> {
                for (String name : names) {
                    statement.setString(1, name);
                    statement.execute();
                }
            });
            getConnection().commit();
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            getConnection().setAutoCommit(true);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
