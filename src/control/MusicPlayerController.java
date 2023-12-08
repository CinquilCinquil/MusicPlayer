package control;

import javax.swing.JPanel;
import service.MusicPlayerService;
import service.MusicPlayerState;

public class MusicPlayerController {
	
	private MusicPlayerService service;
	
	public MusicPlayerController() {
		service = new MusicPlayerService();
	}
	
	public MusicPlayerState play(String filepath)
	{
		return service.play(filepath);
	}
	
	public MusicPlayerState pause()
	{		
		return service.pause();
	}
	
	public String chooseFile(JPanel panel)
	{
		return service.chooseFile(panel);
	}
	
	public void loop()
	{
		service.loop();
	}

	public void stop() {
		service.stop();
	}

}
