package info.creidea.database.query;

import info.creidea.domain.AuthUser;
import info.creidea.domain.PersonalSubject;
import info.creidea.domain.SubjectInfo;
import info.creidea.repository.AbsenceFetchAble;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record AbsenceFetcher(Connection connection) implements AbsenceFetchAble {

    @Override
    public List<PersonalSubject> fetch(List<SubjectInfo> subjects, AuthUser user) {
        try {
            final var statement = connection.prepareStatement("""
                SELECT
                    subject_id,
                    SUM(absence) + FLOOR(SUM(late) / 3) AS 'absence',
                    SUM(late) mod 3 AS 'late'
                  FROM absences
                 WHERE student_number = ?
                 GROUP BY subject_id;
            """);
            statement.setString(1, user.学籍番号());
            final var records = convert(statement.executeQuery());
            return merge(records, subjects);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<PersonalSubject> merge(Map<String, 記録> records, List<SubjectInfo> subjects) {
        return subjects.stream()
                .map((subject) -> {
                    final var record = records.getOrDefault(subject.科目ID(), 記録.DEFAULT);
                    return new PersonalSubject(
                            subject.科目ID(),
                            subject.科目名(),
                            record.欠課時数(),
                            subject.最大欠課時数(),
                            record.遅刻回数()
                    );
                })
                .toList();
    }

    private Map<String, 記録> convert(ResultSet result) throws SQLException {
        final var subjects = new HashMap<String, 記録>();
        while (result.next()) {
            final var 科目ID = result.getString("subject_id");
            final var 欠課時数 = result.getInt("absence");
            final var 遅刻回数 = result.getInt("late");
            subjects.put(科目ID, new 記録(欠課時数, 遅刻回数));
        }
        return subjects;
    }

    private record 記録(int 欠課時数, int 遅刻回数) {
        static final 記録 DEFAULT = new 記録(0, 0);
    }
}
