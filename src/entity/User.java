package entity;

import java.util.ArrayList;

public class User {
	
	String name;
	ArrayList<Song> songs;
	
	void addSong(Song song) {
		songs.add(song);
	}
	
	void removeSong(int ind) {
		songs.remove(ind);
	}
	
	String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
}