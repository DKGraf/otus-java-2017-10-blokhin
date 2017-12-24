package ru.otus.l10.base;

import ru.otus.l10.base.datasets.DataSet;

import java.sql.SQLException;

public interface DBService {
    <T extends DataSet> void save(T user, String table) throws SQLException, IllegalAccessException;

    <T extends DataSet> T load(long id, Class<T> clazz, String table) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    void shutdown();
}