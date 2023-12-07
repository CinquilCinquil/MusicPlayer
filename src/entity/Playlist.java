package entity;

import java.util.ArrayList;

public class Playlist
{
	int id;
	String name;
	ArrayList<Song> songs;

	public Playlist(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
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