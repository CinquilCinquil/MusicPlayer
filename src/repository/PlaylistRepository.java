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
            	Playlist playlist = new Playlist(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            	
            	playlist.setSongs(playlistGetSongs(resultSet.getInt("id")));
            	
            	playlists.add(playlist);
            }
            
            return playlists;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
	}

	public Playlist getOne(int id) {

        String query = "SELECT id, name FROM playlists WHERE id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
        	statement.setInt(1, id);
        	ResultSet resultSet = statement.executeQuery();
        	
            if (resultSet.next())
            {
            	Playlist playlist = new Playlist(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            	
            	playlist.setSongs(playlistGetSongs(resultSet.getInt("id")));
            	
            	return playlist;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
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
		
		String query = "DELETE FROM playlists WHERE id = ?";

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

	public void deleteSong(int playlistId, int songId) {
		String query = "DELETE FROM playlist_song WHERE playlist_id = ? AND song_id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, playlistId);
            statement.setInt(2, songId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void deleteSongs(int playlistId) {
		String query = "DELETE FROM playlist_song WHERE playlist_id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, playlistId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void addSong(int playlistId, int songId) {
		
		if (!isInPlaylist(playlistId, songId)) {
			
			String query = "INSERT INTO playlist_song (playlist_id, song_id) VALUES (?, ?)";
	
	        try (
	            Connection connection = Database.getConnection();
	            PreparedStatement statement = connection.prepareStatement(query)
	        ){
	            statement.setInt(1, playlistId);
	            statement.setInt(2, songId);
	            statement.executeUpdate();
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public boolean isInPlaylist(int playlistId, int songId) {
		
		String query = "SELECT id FROM playlist_song WHERE playlist_id = ? AND song_id = ?";

        try (
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
        	statement.setInt(1, playlistId);
        	statement.setInt(2, songId);
        	ResultSet resultSet = statement.executeQuery();
        	
            return resultSet.next();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
		return false;
	}
	
}
