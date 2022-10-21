package info.creidea.database.migration;

import java.sql.Connection;
import java.sql.SQLException;

public class PasswordMigration implements Migration {
    @Override
    public void prepare(Connection connection) throws SQLException {
        final var statement = connection.createStatement();
        statement.execute("""
        CREATE TABLE IF NOT EXISTS passwords (
            student_number VARCHAR(255) NOT NULL PRIMARY KEY ,
            password VARCHAR(255) NOT NULL,
            FOREIGN KEY fk_student_number(student_number) REFERENCES students(number)
        );
        """);
    }

    @Override
    public void revert(Connection connection) throws SQLException {
        final var statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS passwords");
    }
}
