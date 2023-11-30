package entity;

import java.util.ArrayList;

public class UserVip extends User
{	
	ArrayList<Playlist> playlists;
	
	public void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
	}
	
	public void removePlaylist(int ind) {
		playlists.remove(ind);
	}
	
}