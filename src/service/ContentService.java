package service;

import entity.Playlist;
import repository.PlaylistRepository;

public class ContentService {
	
	private PlaylistRepository playlistRepository;
	
	public ContentService() {
		playlistRepository = new PlaylistRepository();
	}
	
	public void addPlaylist(int userId) {
		playlistRepository.create(userId, new Playlist("new playlist"));
	}

}
