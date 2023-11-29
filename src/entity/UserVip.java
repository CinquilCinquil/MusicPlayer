package entity;

import java.util.ArrayList;

public class UserVip extends User {
	
	ArrayList<Playlist> playlists;
	
	void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
	}
	
	void removePlaylist(int ind) {
		playlists.remove(ind);
	}
	
}