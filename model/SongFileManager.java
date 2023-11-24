package model;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class SongFileManager {
	
	public Song loadSong(String path)
	{
		try {
			
			FileInputStream fis = new FileInputStream(path);
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        Player player = new Player(bis);
	        
	        player.play();
			
			Song newSong = new Song();
			
			return newSong;
		}
		catch(Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
}