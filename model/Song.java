package model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class Song {
	
	String name, artist;
	FileInputStream file;
	
	private Player player;
	
	public Song(FileInputStream file) {
		
		BufferedInputStream bis = new BufferedInputStream(file);
		
		try {
		
			player = new Player(bis);
		
		}
		catch(Exception e)
		{
			
			System.out.println("Failed to create song!");
			
			e.printStackTrace();
			
		}
	}
	
	public void play() {
        
        try {
        	
			player.play();
			
		}
        catch (Exception e) {
        	
        	System.out.println("Could not play audio file!");
			
			e.printStackTrace();
		}
	}
	
	public void close() {
		
		player.close();
		
	}
	
}