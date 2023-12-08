package control;

import java.util.ArrayList;

import entity.Playlist;
import entity.Song;
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
	
	public void updatePlaylist(Playlist playlist) {
		service.updatePlaylist(playlist);
	}
	
	public void deletePlaylist(Playlist playlist) {
		service.deletePlaylist(playlist.getId());
	}
	
	public void addSongToPlaylist(int playlistId, Song song) {
		service.addSongToPlaylist(playlistId, song);
	}

}
