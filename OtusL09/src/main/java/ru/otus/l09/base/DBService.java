package ru.otus.l09.base;

import ru.otus.l09.base.datasets.UserDataSet;

public interface DBService {
    void save(UserDataSet user);

    UserDataSet load(long id, Class clazz);

    void shutdown();
}
