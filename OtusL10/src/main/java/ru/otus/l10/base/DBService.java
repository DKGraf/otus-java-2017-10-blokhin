package ru.otus.l10.base;

import ru.otus.l10.base.datasets.UserDataSet;

public interface DBService {
    void save(UserDataSet user);

    UserDataSet load(long id);

    void shutdown();
}
