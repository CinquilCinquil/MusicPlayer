package repository;

import entity.User;
import util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements IRepository<User>
{
    public ArrayList<User> getAll()
    {
        ArrayList<User> users = new ArrayList<>();

        String query = "SELECT id, name FROM users";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next())
            {
                users.add(
                    new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    )
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getOne(int id)
    {
        String query = "SELECT id, name FROM users WHERE id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void create(User user)
    {
        String query = "INSERT INTO users (name) VALUES (?)";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setString(1, user.getName());
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user)
    {
        String query = "UPDATE users SET name = ? WHERE id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            
            statement.setString(1, user.getName());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id)
    {
        String query = "DELETE FROM users WHERE id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
    public boolean isVip(int id) {
    	String query = "SELECT vip FROM users WHERE id = ?";
    	
    	try (
                Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getBoolean("vip");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public int getUserId(String name) {
    	String query = "SELECT id FROM users WHERE name = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    public String getUserPassword(int id) {
    	
    	String query = "SELECT password FROM users WHERE id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    	
    }
}
