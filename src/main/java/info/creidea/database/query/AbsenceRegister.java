package info.creidea.database.query;

import info.creidea.domain.AuthUser;
import info.creidea.repository.AbsenceRegisterAble;

import java.sql.Connection;
import java.sql.SQLException;

public record AbsenceRegister(Connection connection) implements AbsenceRegisterAble {
    @Override
    public void register(AuthUser user, String subjectID, String date, int absence, boolean late) {
        try {
            if (isAlreadyExist(user, subjectID, date)) {
                update(user, subjectID, date, absence, late);
            } else {
                create(user, subjectID, date, absence, late);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void create(AuthUser user, String subjectID, String date, int absence, boolean late) throws SQLException {
        final var statement = connection.prepareStatement("""
            INSERT INTO absences VALUES (?, ?, ?, ?, ?);
        """);
        statement.setString(1, user.学籍番号());
        statement.setString(2, subjectID);
        statement.setString(3, date);
        statement.setInt(4, absence);
        statement.setInt(5, late ? 1 : 0);
        statement.execute();
    }

    private void update(AuthUser user, String subjectID, String date, int absence, boolean late) throws SQLException {
        final var statement = connection.prepareStatement("""
            UPDATE absences
               SET absence = ?, late = ?
             WHERE student_number = ?
               AND subject_id = ?
               AND date = ?;
        """);
        statement.setInt(1, absence);
        statement.setInt(2, late ? 1 : 0);
        statement.setString(3, user.学籍番号());
        statement.setString(4, subjectID);
        statement.setString(5, date);
        statement.execute();
    }


    private boolean isAlreadyExist(AuthUser user, String subjectID, String date) throws SQLException {
        final var statement = connection.prepareStatement("""
            SELECT COUNT(student_number)
              FROM absences
             WHERE student_number = ?
               AND subject_id = ?
               AND date = ?;
        """);
        statement.setString(1, user.学籍番号());
        statement.setString(2, subjectID);
        statement.setString(3, date);
        final var result = statement.executeQuery();
        result.next();
        return result.getInt(1) == 1;
    }
}
