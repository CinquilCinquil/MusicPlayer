package service;

import java.io.File;
import java.util.ArrayList;
import entity.Playlist;
import entity.Song;
import repository.PlaylistRepository;
import repository.SongRepository;
import repository.UserRepository;
import util.Util;

public class ContentService {
	
	private UserRepository userRepository;
	private PlaylistRepository playlistRepository;
	private SongRepository songRepository;
	
	public ContentService() {
		playlistRepository = new PlaylistRepository();
		userRepository = new UserRepository();
		songRepository = new SongRepository();
	}
	
	public void addPlaylist(int userId) {
		playlistRepository.create(userId, new Playlist("new playlist"));
	}
	
	public ArrayList<Playlist> getUserPlaylists(int userId) {
		return userRepository.getUserPlaylists(userId);
	}
	
	public void updatePlaylist(Playlist playlist) {
		playlistRepository.update(playlist);
	}
	
	public void deletePlaylist(int playlistId) {
		playlistRepository.deleteSongs(playlistId);
		playlistRepository.delete(playlistId);
	}
	
	public void addSongToPlaylist(int playlistId, Song song) {
		playlistRepository.addSong(playlistId, song.getId());
	}

	public ArrayList<Song> updateUserSongs(int userId) {
		
		// Adding user songs
		ArrayList<Song> list = userRepository.getUserSongs(userId);

		// Adding songs in directories
		ArrayList<String> dirs = userRepository.getUserDirectories(userId);
		
		for (String dir : dirs) {
			
			File currentDir = new File(dir);
			
			for (File file : currentDir.listFiles()) {
				
				if (Util.getExtension(file).compareTo("wav") == 0) {
					list.add(new Song(userId, file.getName(), "?", file.getPath()));
				}
			}
			
		}
		
		return list;
	}

	public void addUserDir(int userId, String filepath) {
		userRepository.addUserDir(userId, filepath);
	}

	public void addUserSong(int userId, String filepath) {
		songRepository.addUserSong(userId, filepath);
	}

	public void deleteUserSong(int songId) {
		songRepository.delete(songId);
	}

	public void deletePlaylistSong(int playlistId, int songId) {
		playlistRepository.deleteSong(playlistId, songId);
	}
}
