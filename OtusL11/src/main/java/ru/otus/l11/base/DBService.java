package ru.otus.l11.base;

import ru.otus.l11.base.datasets.UserDataSet;

public interface DBService {
    void save(UserDataSet user);

    UserDataSet load(long id);

    void shutdown();
}
