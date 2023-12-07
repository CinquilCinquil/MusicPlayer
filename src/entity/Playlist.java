package entity;

import java.util.ArrayList;

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
	
	public ArrayList<Song> getSongs() {
		return songs;
	}
	
	public void updateSongs() {
		UserRepository userRepository = new UserRepository();
		songs = userRepository.playlistGetSongs(id);
		System.out.println(songs);
	}
	
}