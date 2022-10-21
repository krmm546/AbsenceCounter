package info.creidea.database;

import info.creidea.client.SubjectsFetchAble;
import info.creidea.domain.AuthUser;
import info.creidea.domain.PersonalSubject;
import info.creidea.repository.SubjectAbsenceFetchAble;

import java.sql.Connection;
import java.sql.SQLException;

public record SubjectAbsenceFetcher(Connection connection, SubjectsFetchAble subjectsFetcher) implements SubjectAbsenceFetchAble  {

    @Override
    public PersonalSubject fetch(AuthUser user, String 科目ID) {
        記録 record;
        try {
            record = fetchFromDB(user, 科目ID);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        final var subject = subjectsFetcher.fetch(4, 2, 科目ID);
        return new PersonalSubject(
                科目ID,
                subject.科目名(),
                record.欠課時数,
                subject.最大欠課時数(),
                record.遅刻回数
        );
    }

    private 記録 fetchFromDB(AuthUser user, String 科目ID) throws SQLException {
        final var statement = connection.prepareStatement("""
            SELECT
                SUM(absence) + FLOOR(SUM(late) / 3) AS 'absence',
                SUM(late) mod 3 AS 'late'
              FROM absences
             WHERE student_number = ?
               AND subject_id = ?;
        """);
        statement.setString(1, user.学籍番号());
        statement.setString(2, 科目ID);
        final var result = statement.executeQuery();
        result.next();
        final var absence = result.getInt("absence");
        final var late = result.getInt("late");
        return new 記録(absence, late);
    }

    private record 記録(int 欠課時数, int 遅刻回数) {}
}
