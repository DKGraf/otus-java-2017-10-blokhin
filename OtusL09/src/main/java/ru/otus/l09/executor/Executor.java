package ru.otus.l09.executor;

import org.postgresql.core.ResultHandler;
import ru.otus.l09.base.DataSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execQuery(String query, ResultHandler handler) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            handler.handle(result);
        }
    }

    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            return stmt.getUpdateCount();
        }
    }

    Connection getConnection() {
        return connection;
    }

    public <T extends DataSet> void save(T user) {

    }

    public <T extends DataSet> T load(long id, Class<T> clazz) {

    }
}
