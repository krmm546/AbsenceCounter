package info.creidea.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseFactory {
    private static final String  URL = "jdbc:mysql://localhost:8889/absence";
    private static final String USER = "jdbc_username";
    private static final String PASSWORD = "jdbc_password";

    public static Connection create() throws SQLException {
        System.out.println("Connected....");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
