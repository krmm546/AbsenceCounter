package info.creidea.database.migration;

import java.sql.Connection;
import java.sql.SQLException;

public class StudentMigration implements Migration {
    @Override
    public void prepare(Connection connection) throws SQLException {
        final var statement = connection.createStatement();
        statement.execute("""
        CREATE TABLE IF NOT EXISTS students (
            number VARCHAR(255) NOT NULL PRIMARY KEY
        );
        """);
    }

    @Override
    public void revert(Connection connection) throws SQLException {
        final var statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS students");
    }
}
