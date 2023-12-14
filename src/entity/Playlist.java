package entity;

import java.util.ArrayList;

import repository.PlaylistRepository;
import repository.UserRepository;

public class Playlist
{
	private int id;
	private String name;
	private ArrayList<Song> songs;

	public Playlist(int id, String name) {
		this.id = id;
		this.name = name;
		this.songs = null;
	}
	
	public Playlist(String name) {
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
	
	public void removeSong(Song song) {
		songs.remove(song);
	}
	
	public ArrayList<Song> getSongs() {
		return songs;
	}
	
	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	
	public boolean hasSong(int songId) {
		for (Song song : songs) {
			if (song.getId() == songId) return true;
		}
		return false;
	}
	
}