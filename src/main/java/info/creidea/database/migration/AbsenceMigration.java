package info.creidea.database.migration;

import java.sql.Connection;
import java.sql.SQLException;

public class AbsenceMigration implements Migration{
    @Override
    public void prepare(Connection connection) throws SQLException {
        final var statement = connection.createStatement();
        statement.execute("""
        CREATE TABLE IF NOT EXISTS absences (
            student_number VARCHAR(255) NOT NULL,
            subject_id VARCHAR(255) NOT NULL ,
            date DATE NOT NULL ,
            absence TINYINT NOT NULL ,
            late TINYINT NOT NULL ,
            PRIMARY KEY (student_number, subject_id, date),
            FOREIGN KEY fk_student_number(student_number) REFERENCES students(number)
        );
        """);
    }

    @Override
    public void revert(Connection connection) throws SQLException {
        final var statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS absences");
    }
}