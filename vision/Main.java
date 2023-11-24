package vision;

import model.*;

public class Main {
	
    public static void main(String[] args) {
		
		try {
			
			// Start program
			
			Song s =  (new SongFileManager()).loadSong("musics/music.mp3");
			s.play();
			
			System.out.println("this will only print once music has stopped playing");
			//s.close();
			
		}
		catch (Exception e)
		{
			System.out.println("Failed to initiate!");
			e.printStackTrace();
		}
		
	}
}