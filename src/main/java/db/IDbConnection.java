package db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDbConnection {

    void executeQuery(String sqlQuery) throws SQLException, IOException;
    ResultSet executeQueryWithAnswer(String query) throws SQLException, IOException;
    public void close() throws SQLException;
}
