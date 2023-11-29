package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SongFileManager {
	
	public static Song loadSong(String path)
	{
		try {
			
			FileInputStream fis = new FileInputStream(path);
			
			Song newSong = new Song(fis);
			
			return newSong;
		
		}
		catch(FileNotFoundException e) {
			
			System.out.println("File not found!");
			
			e.printStackTrace();
			
			return null;
		}
	}
}