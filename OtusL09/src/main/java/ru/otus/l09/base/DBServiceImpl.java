package ru.otus.l09.base;

import ru.otus.l09.base.datasets.UserDataSet;
import ru.otus.l09.base.datasets.dao.UserDataSetDAO;

import java.sql.Connection;
import java.sql.SQLException;

import static ru.otus.l09.base.connection.ConnectionHelper.getConnection;

public class DBServiceImpl implements DBService {
    private final Connection connection;
    private final UserDataSetDAO userDataSetDAO;

    DBServiceImpl() {
        this.connection = getConnection();
        this.userDataSetDAO = new UserDataSetDAO(connection);
    }

    public void save(UserDataSet user) {
        try {
            userDataSetDAO.save(user);
        } catch (IllegalAccessException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UserDataSet load(long id, Class clazz) {
        UserDataSet user = null;
        try {
            user = userDataSetDAO.load(id, clazz);
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
