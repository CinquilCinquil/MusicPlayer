package entity;

import java.util.ArrayList;

import repository.PlaylistRepository;
import repository.UserRepository;

public class Playlist
{
	int id;
	String name;
	ArrayList<Song> songs;

	public Playlist(int id, String name) {
		this.id = id;
		this.name = name;
		
		updateSongs();
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
	
	public void updateSongs() {
		PlaylistRepository playlistRepository = new PlaylistRepository();
		songs = playlistRepository.playlistGetSongs(id);
	}
	
}