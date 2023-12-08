package entity;

import java.util.ArrayList;

public class User
{	
	int id;
	String name;
	ArrayList<Song> songs;

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public User(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
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