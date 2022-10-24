package info.creidea.database.seed;

import java.sql.Connection;
import java.sql.SQLException;

public record SampleSeeder() implements Seeder {

    private static final String create =
            """
                INSERT INTO absences VALUES
                ('guest', '2022-28-15-0060', '2022-10-10', 9, 1),
                ('guest', '2022-28-15-0062', '2022-10-10', 16, 0),
                ('guest', '2022-28-15-0067', '2022-10-10', 7, 2),
                ('guest', '2022-28-15-0068', '2022-10-10', 12, 1),
                ('guest', '2022-28-15-0069', '2022-10-10', 5, 0),
                ('guest', '2022-28-15-0072', '2022-10-10', 3, 4),
                ('guest', '2022-28-15-0074', '2022-10-10', 3, 3),
                ('guest', '2022-28-15-0075', '2022-10-10', 4, 2),
                ('guest', '2022-28-15-0076', '2022-10-10', 2, 1),
                ('guest', '2022-28-15-0077', '2022-10-10', 0, 0);
            """;

    private static final String revert = "DELETE FROM absences WHERE student_number = 'guest';";

    @Override
    public void seed(Connection connection) throws SQLException {
        createGuest(connection);
        connection.createStatement().execute(revert);
        connection.createStatement().execute(create);
    }

    private void createGuest(Connection connection) throws SQLException {
        final var result = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM students WHERE number = 'guest';");
        result.next();
        if (result.getInt(1) == 1) return;

        connection.createStatement().execute("INSERT INTO students VALUES ('guest')");
        connection.createStatement().execute("INSERT INTO passwords VALUES ('guest', 'password')");
    }
}
