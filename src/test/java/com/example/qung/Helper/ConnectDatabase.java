package com.example.qung.Helper;

import java.sql.*;

public class ConnectDatabase {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://103.138.113.158:5432/QLVB";
        String username = "/////";
        String password = "tringhia@12345";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}

