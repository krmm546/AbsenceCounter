package info.creidea.database.seed;

import java.sql.Connection;
import java.sql.SQLException;

public interface Seeder {
    void seed(Connection connection) throws SQLException;
}
