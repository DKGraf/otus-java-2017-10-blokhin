package ru.otus.l09.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());

            String url = "jdbc:postgresql:otusl09";

            return DriverManager.getConnection(url, "dkgraf", "dkgraf");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
