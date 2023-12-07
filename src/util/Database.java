package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private static final String connectionUrl = "jdbc:sqlite:./data/sample.db";

    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(connectionUrl);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
