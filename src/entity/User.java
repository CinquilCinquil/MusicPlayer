package entity;

import java.util.ArrayList;

public class User
{	
	private int id;
	private boolean isVip;
	private String name;
	private ArrayList<Song> songs;

	public User(int id, String name, boolean isVip) {
		this.id = id;
		this.isVip = isVip;
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
	
	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}
	
	public boolean getVip() {
		return isVip;
	}
	
}