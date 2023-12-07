package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Playlist;
import entity.Song;
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
		// TODO Auto-generated method stub
		return null;
	}

	public Playlist getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void create(Playlist entity) {
		// TODO Auto-generated method stub
		
	}

	public void update(Playlist entity) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
