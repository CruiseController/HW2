package tables;

import java.io.IOException;
import java.sql.SQLException;

public interface ITable {
    void createTable() throws SQLException, IOException;
}
