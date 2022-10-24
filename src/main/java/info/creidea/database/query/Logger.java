package info.creidea.database.query;

import info.creidea.repository.Loggable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public record Logger(Connection connection) implements Loggable {
    @Override
    public void log(Optional<String> studentNumber, String path, String ip) throws SQLException {
        final var statement = connection.prepareStatement("""
            INSERT INTO access_logs (
                student_number, path, ip
            ) VALUES (
                ?, ?, ?
            );
        """);
        System.out.println(path + " " + studentNumber.orElse("NULL") + " " + ip);
        statement.setString(1, studentNumber.orElse(null));
        statement.setString(2, path);
        statement.setString(3, ip);
        statement.execute();
    }
}
