package ru.otus.l10.base;

import ru.otus.l10.base.datasets.DataSet;

public class DBServiceHibernateImpl implements DBService {

    public <T extends DataSet> void save(T user) {

    }

    public <T extends DataSet> T load(long id, Class<T> clazz) {
        return null;
    }
}
