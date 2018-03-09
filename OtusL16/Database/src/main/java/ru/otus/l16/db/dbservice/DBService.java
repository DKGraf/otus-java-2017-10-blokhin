package ru.otus.l16.db.dbservice;

import ru.otus.l16.db.datasets.UserDataSet;

public interface DBService {
    void save(UserDataSet user);

    UserDataSet load(long id);

    void shutdown();
}
