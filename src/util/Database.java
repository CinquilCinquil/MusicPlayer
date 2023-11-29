package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    Connection connection;

    public Database()
    {
        try {
             connection = DriverManager.getConnection("jdbc:sqlite:data/sample.db");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            connection = null;
        }
    }

    public Connection getConnection()
    {
        return connection;
    }
}
