package info.creidea.database.migration;

import java.sql.Connection;
import java.sql.SQLException;

public interface Migration {
    void prepare(Connection connection) throws SQLException;

    void revert(Connection connection) throws SQLException;
}
