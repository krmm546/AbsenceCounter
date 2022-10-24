package info.creidea.repository;

import java.sql.SQLException;
import java.util.Optional;

public interface Loggable {
    void log(Optional<String> studentNumber, String path, String ip) throws SQLException;
}
