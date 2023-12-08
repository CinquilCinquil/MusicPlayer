package repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Song;
import util.Database;

public class SongRepository implements IRepository<Song> {

	public ArrayList<Song> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Song getOne(int id) {
		
		String query = "SELECT * FROM songs WHERE id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	return new Song(resultSet.getInt("id"),
            			resultSet.getString("name"),
            			resultSet.getString("artist"),
            			resultSet.getString("path")
            	);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
	}

	public void create(Song entity) {
		String query = "INSERT INTO songs (user_id, name, artist ,filepath) VALUES (?, ?, ?, ?)";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getArtist());
            statement.setString(4, entity.getFilepath());
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public void addUserSong(int userId, String dir) {
    	
    	String query = "INSERT INTO songs (user_id, name, path) VALUES (?, ?, ?)";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, userId);
            statement.setString(2, (new File(dir)).getName());
            statement.setString(3, dir);
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void update(Song entity) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public void delete(int userId, int songId) {
		// TODO Auto-generated method stub
		
	}

}
