package vision;

import model.*;

public class Main {
	
    public static void main(String[] args) {
		
		try {
			
			// Start program
			
			SongFileManager a = new SongFileManager();
			
			a.loadSong("musics/music.mp3");
			
		}
		catch (Exception e)
		{
			System.out.println("Failed to initiate!");
			e.printStackTrace();
		}
		
	}
}