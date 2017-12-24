package ru.otus.l10.base;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T handle(ResultSet result) throws SQLException;
}