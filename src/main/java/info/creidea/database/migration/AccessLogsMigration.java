package info.creidea.database.migration;

import java.sql.Connection;
import java.sql.SQLException;

public class AccessLogsMigration implements Migration {
    @Override
    public void prepare(Connection connection) throws SQLException {
        final var statement = connection.createStatement();
        statement.execute("""
        CREATE TABLE IF NOT EXISTS access_logs (
            id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
            student_number VARCHAR(255),
            path VARCHAR(255) NOT NULL ,
            ip VARCHAR(255) NOT NULL ,
            dt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
            FOREIGN KEY fk_student_number(student_number) REFERENCES students(number)
        );
        """);
    }

    @Override
    public void revert(Connection connection) throws SQLException {
        final var statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS access_logs");
    }
}
