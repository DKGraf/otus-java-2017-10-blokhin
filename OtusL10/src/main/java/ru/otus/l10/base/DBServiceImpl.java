package ru.otus.l10.base;

import ru.otus.l10.base.datasets.DataSet;
import ru.otus.l10.base.datasets.UsersDAO;

import java.sql.Connection;
import java.sql.SQLException;

import static ru.otus.l10.base.connection.ConnectionHelper.getConnection;

public class DBServiceImpl implements DBService {
    private final Connection connection;
    private final UsersDAO usersDAO;

    DBServiceImpl() {
        this.connection = getConnection();
        this.usersDAO = new UsersDAO(connection);
    }

    public <T extends DataSet> void save(T user) {
        try {
            usersDAO.save(user);
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) {
        T t = null;
        try {
            t = usersDAO.load(id, clazz);
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void shutdown() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
