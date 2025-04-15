package db;

import properties.FilePropertiesReader;

import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class MySqlConnector implements IDbConnection {

    private static Connection dbConnection = null;
    private static Statement statement = null;

    public MySqlConnector() {
    }

    private void mySqlConnector() throws SQLException {
        try {
            if (dbConnection == null) {
                try {
                    Map<String, String> settings = new FilePropertiesReader().getSettings();
                    dbConnection = DriverManager.getConnection(settings.get("url"), settings.get("login"), settings.get("password"));
                }
                catch (IOException e){
                    System.out.println("Ошибка при получении данных для подключения из properties-фала");
                }

            }
            if (statement == null) {
                statement = dbConnection.createStatement();
            }
        }
        catch (SQLException e){
            System.out.println("Не удалось подключитсяк БД");
            throw e;
        }

    }

    public void executeQuery(String sqlQuery) throws SQLException {
        mySqlConnector();
        statement.executeUpdate(sqlQuery);
    }

    public ResultSet executeQueryWithAnswer(String sqlQuery) throws SQLException {
        mySqlConnector();
        return statement.executeQuery(sqlQuery);
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (dbConnection != null) {
            dbConnection.close();
        }


    }
}
