package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Playlist;
import entity.Song;
import entity.User;
import util.Database;

public class PlaylistRepository implements IRepository<Playlist> {
	
	 public ArrayList<Song> playlistGetSongs(int playlistId) {
	    	
    	String query = "SELECT * FROM playlist_song WHERE playlist_id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, playlistId);
            ResultSet resultSet = statement.executeQuery();
      
        	ArrayList<Song> songList = new ArrayList<Song>();
        	
        	SongRepository songRepository = new SongRepository();
        	
        	while (resultSet.next()) {
        		songList.add(songRepository.getOne(resultSet.getInt("song_id")));
        	}
        	
        	return songList;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    	
    }

	public ArrayList<Playlist> getAll() {
		ArrayList<Playlist> playlists = new ArrayList<Playlist>();

        String query = "SELECT id, name FROM playlists";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next())
            {
            	playlists.add(
                    new Playlist(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    )
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
	}

	public Playlist getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(int userId, Playlist entity) {
		
		String query = "INSERT INTO playlists (user_id, name) VALUES (?, ?)";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, userId);
            statement.setString(2, entity.getName());
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public void create(Playlist entity) {
		
		String query = "INSERT INTO playlists (name) VALUES (?)";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setString(1, entity.getName());
            statement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	public void update(Playlist entity) {
		
		 String query = "UPDATE playlists SET name = ? WHERE id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
