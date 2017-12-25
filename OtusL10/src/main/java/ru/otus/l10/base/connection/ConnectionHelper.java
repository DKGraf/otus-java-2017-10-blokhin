package ru.otus.l10.base.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Хелпер, для получения подключения к базе.
 * Используется Postgres на localhost'е с
 * базой по имени 'otusl10',
 * пользователем 'dkgraf' и паролем 'dkgraf'.
 * Здравствуй безопасность!
 */

public class ConnectionHelper {
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            String url = "jdbc:postgresql:otusl10";
            return DriverManager.getConnection(url, "dkgraf", "dkgraf");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
