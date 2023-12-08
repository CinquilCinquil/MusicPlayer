package service;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicPlayerService {
	
	private Clip clip;
	private boolean isPaused = false;
	private boolean isLooping = false;
	
	public MusicPlayerState play(String filepath)
	{
		
		MusicPlayerState state = MusicPlayerState.OVER;
		
		// Pausing existing clip
		if (clip != null && clip.isRunning()) {
			clip.stop();
		}
		
		// Trying to create a new clip
		try {
			File file = new File(filepath);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			
			if (isLooping) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
			clip.start();
			state = MusicPlayerState.PLAYING;
			
			isPaused = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return state;
	}
	
	public MusicPlayerState pause()
	{		
		MusicPlayerState state = MusicPlayerState.OVER;
		
		if (clip != null)
		{
			if (clip.isRunning())
			{
				clip.stop();
				state = MusicPlayerState.PAUSED;
			}
			else if (isPaused)
			{	
				clip.start();
				
				if (isLooping) {
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
				
				state = MusicPlayerState.PLAYING;
			}
		}
		
		isPaused = !isPaused;
		return state;
	}
	
	public String chooseFile(JPanel panel)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("WAV Files", "wav"));
		fileChooser.setCurrentDirectory(new File(".")); //current directory
		
		int result = fileChooser.showOpenDialog(panel);
		
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}
		
		return null;
		
	}
	
	public void loop()
	{
		isLooping = !isLooping;

		if (isLooping && clip.isRunning()) {
			clip.loop(isLooping ? Clip.LOOP_CONTINUOUSLY : 0);
		}

	}

	public void stop() {
		if (clip != null) {
			if (!isPaused) clip.stop();
		}
	}
}
