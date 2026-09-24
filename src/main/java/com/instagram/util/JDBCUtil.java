package com.instagram.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/instagram_db";

    private static final String USERNAME =
            "root";

    private static final String PASSWORD =
            "Mysql@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD
        );
    }
}