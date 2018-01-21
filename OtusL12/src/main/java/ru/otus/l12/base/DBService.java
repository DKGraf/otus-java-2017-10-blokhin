package ru.otus.l12.base;

import ru.otus.l12.base.datasets.UserDataSet;

public interface DBService {
    void save(UserDataSet user);

    UserDataSet load(long id);

    void shutdown();
}
