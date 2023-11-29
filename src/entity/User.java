package entity;

import java.util.ArrayList;

public class User
{	
	String name;
	ArrayList<Song> songs;
	
	public void addSong(Song song) {
		songs.add(song);
	}
	
	public void removeSong(int ind) {
		songs.remove(ind);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}