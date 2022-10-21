package info.creidea.database.query;

import info.creidea.repository.Authenticable;

import java.sql.Connection;
import java.sql.SQLException;

public record Authenticator(Connection connection) implements Authenticable {

    @Override
    public boolean 認証(String id, String password) {
        try {
            final var statement = connection.prepareStatement("""
                SELECT COUNT(student_number)
                  FROM passwords
                 WHERE student_number = ?
                   AND password = ?;
            """);
            statement.setString(1, id);
            statement.setString(2, password);
            final var result = statement.executeQuery();
            result.next();
            return result.getInt(1) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
