package control;

import java.io.File;
import java.util.ArrayList;

import entity.Playlist;
import entity.Song;
import external.SongList.SongItem;
import service.ContentService;
import util.Util;

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
	
	public ArrayList<Song> updateUserSongs(int userId) {
		return service.updateUserSongs(userId);
	}
	
	public void addUserDir(int userId, String filepath) {
		service.addUserDir(userId, filepath);
	}
	
	public void addUserSong(int userId, String filepath) {
		service.addUserSong(userId, filepath);
	}

}
