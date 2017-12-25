package ru.otus.l10.base;

import ru.otus.l10.base.datasets.UserDataSet;
import ru.otus.l10.base.datasets.dao.UsersDAO;

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

    public void save(UserDataSet user) {
        try {
            usersDAO.save(user);
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDataSet load(long id) {
        UserDataSet user = null;
        try {
            user = usersDAO.load(id);
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return user;
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
