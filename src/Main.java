import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Database;

public class Main
{
    public static void main(String[] args)
    {
        Database database = new Database();

        try (Statement statement = database.getConnection().createStatement())
        {
            statement.executeUpdate("DROP TABLE IF EXISTS terminalroot");

            statement.executeUpdate("CREATE TABLE terminalroot (id INTEGER, name STRING)");

            statement.executeUpdate("INSERT INTO terminalroot VALUES (1, 'Marcos Oliveira')");
            statement.executeUpdate("INSERT INTO terminalroot VALUES (2, 'James Gosling')");
            
            // printing table
            ResultSet rs = statement.executeQuery("SELECT * FROM terminalroot");
            while (rs.next()) {  
        		System.out.println(rs.getInt("id") +  "\t" +   
                rs.getString("name"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}