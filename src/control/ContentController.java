package control;

import java.util.ArrayList;
import entity.Playlist;
import entity.Song;
import service.ContentService;

// Controller responsible for managing content (user data, songs, playlists) between Front-End and service.

public class ContentController {
	
	private ContentService service;
	
	public ContentController() {
		service = new ContentService();
	}
	
	//  ----- Songs -----
	
	public void addUserSong(int userId, String filepath) {
		service.addUserSong(userId, filepath);
	}
	
	public void updateSong(Song song) {
		service.updateSong(song);
	}
	
	public void deleteUserSong(Song song) {
		service.deleteUserSong(song.getId());
	}
	
	public ArrayList<Song> updateUserSongs(int userId) {
		return service.updateUserSongs(userId);
	}
	
	//  ----- Playlists -----
	
	public void addPlaylist(int userId) {
		service.addPlaylist(userId);
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
	
	public void deletePlaylistSong(Playlist playlist, Song song) {
		service.deletePlaylistSong(playlist.getId(), song.getId());
	}
	
	public ArrayList<Playlist> getUserPlaylists(int userId) {
		return service.getUserPlaylists(userId);
	}
	
	// ----- Dir -----
	
	public void addUserDir(int userId, String filepath) {
		service.addUserDir(userId, filepath);
	}

}
