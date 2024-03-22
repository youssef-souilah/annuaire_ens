package com.ens.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/annuaire_ens";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "0";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
         connection= DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}

