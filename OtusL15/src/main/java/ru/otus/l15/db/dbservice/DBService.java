package ru.otus.l15.db.dbservice;

import ru.otus.l15.db.datasets.UserDataSet;

public interface DBService {
    void save(UserDataSet user);

    UserDataSet load(long id);

    void shutdown();
}
