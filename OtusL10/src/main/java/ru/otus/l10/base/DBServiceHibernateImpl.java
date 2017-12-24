package ru.otus.l10.base;

import ru.otus.l10.base.datasets.DataSet;

import java.sql.SQLException;

public class DBServiceHibernateImpl implements DBService {

    @Override
    public <T extends DataSet> void save(T user, String table) throws SQLException, IllegalAccessException {

    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz, String table) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return null;
    }

    @Override
    public void shutdown() {

    }
}
