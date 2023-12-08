package control;

import java.util.ArrayList;

import entity.Playlist;
import service.ContentService;

public class ContentController {
	
	private ContentService service;
	
	public ContentController() {
		service = new ContentService();
	}
	
	public void addPlaylist(int userId) {
		service.addPlaylist(userId);
	}
	
	public ArrayList<Playlist> getUserPlaylists(int userId) {
		return service.getUserPlaylists(userId);
	}

}
