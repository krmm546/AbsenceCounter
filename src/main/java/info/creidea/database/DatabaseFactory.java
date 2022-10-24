package info.creidea.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseFactory {
    private static final String  URL = "jdbc:mysql://db:3306/absence";
    private static final String USER = "jdbc_username";
    private static final String PASSWORD = "jdbc_password";
    public static Connection create() throws SQLException, InterruptedException {
        System.out.println("Docker Database Pausing...");
        Thread.sleep(10000);
        System.out.println("Connecting....");
        final var connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected....");
        return connection;
    }
}
