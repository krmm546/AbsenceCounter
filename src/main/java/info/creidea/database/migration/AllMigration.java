package info.creidea.database.migration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class AllMigration implements Migration {

    private static final ArrayList<Migration> MIGRATIONS = new ArrayList<>(Arrays.asList(
            new StudentMigration(),
            new PasswordMigration(),
            new AbsenceMigration()
    ));

    @Override
    public void prepare(Connection connection) throws SQLException {
        for (final var migration: MIGRATIONS) {
            migration.prepare(connection);
        }
    }

    @Override
    public void revert(Connection connection) throws SQLException {
        for (int i = MIGRATIONS.size() - 1; i >= 0; i--) {
            MIGRATIONS.get(i).revert(connection);
        }
    }
}

