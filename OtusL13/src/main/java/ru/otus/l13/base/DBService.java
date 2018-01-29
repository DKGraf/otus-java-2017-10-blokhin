package ru.otus.l13.base;

import ru.otus.l13.base.datasets.UserDataSet;

public interface DBService {
    void save(UserDataSet user);

    UserDataSet load(long id);

    void shutdown();
}
