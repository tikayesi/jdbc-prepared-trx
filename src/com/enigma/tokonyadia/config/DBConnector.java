package com.enigma.tokonyadia.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class
DBConnector {
    private final String url = "jdbc:postgresql://localhost/enigmat_shop";
    private final String user = "postgres";
    private final String password = "P@ssw0rd";

    public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the posgreSQL server successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
