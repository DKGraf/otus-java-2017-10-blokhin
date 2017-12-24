package ru.otus.l09.base;

import ru.otus.l09.base.dataset.DataSet;
import ru.otus.l09.base.dataset.UsersDAO;

import java.sql.Connection;
import java.sql.SQLException;

import static ru.otus.l09.base.connection.ConnectionHelper.getConnection;

public class DBServiceImpl implements DBService {
    private final Connection connection;
    private final UsersDAO usersDAO;

    public DBServiceImpl() {
        this.connection = getConnection();
        this.usersDAO = new UsersDAO(connection);
    }

    public <T extends DataSet> void save(T user, String table) throws SQLException, IllegalAccessException {
        usersDAO.save(user, table);
    }

    public <T extends DataSet> T load(long id, Class<T> clazz, String table) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        T t = usersDAO.load(id, clazz, table);
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
