package info.creidea.database.query;

import info.creidea.domain.AuthUser;
import info.creidea.repository.AbsenceRegisterAble;

import java.sql.Connection;
import java.sql.SQLException;

public record AbsenceRegister(Connection connection) implements AbsenceRegisterAble {
    @Override
    public void register(AuthUser user, String subjectID, String date, int absence, boolean late) {
        try {
            final var statement = connection.prepareStatement("""
                INSERT INTO absences VALUES (?, ?, ?, ?, ?);
            """);
            statement.setString(1, user.学籍番号());
            statement.setString(2, subjectID);
            statement.setString(3, date);
            statement.setInt(4, absence);
            statement.setInt(5, late ? 1 : 0);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
