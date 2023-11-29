package entity;

import java.util.ArrayList;

public class Playlist
{
	String name;
	ArrayList<Song> songs;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public Song getSong(int ind) {
		return songs.get(ind);
	}
	
}