package service;

import java.util.ArrayList;

import entity.Playlist;
import repository.PlaylistRepository;
import repository.UserRepository;

public class ContentService {
	
	private PlaylistRepository playlistRepository;
	private UserRepository userRepository;
	
	public ContentService() {
		playlistRepository = new PlaylistRepository();
		userRepository = new UserRepository();
	}
	
	public void addPlaylist(int userId) {
		playlistRepository.create(userId, new Playlist("new playlist"));
	}
	
	public ArrayList<Playlist> getUserPlaylists(int userId) {
		return userRepository.getUserPlaylists(userId);
	}

}
