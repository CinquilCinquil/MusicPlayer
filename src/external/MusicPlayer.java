package external;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MusicPlayer extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField filePathField;
	private JButton playBtt;
	private JButton pauseBtt;
	private JButton loopBtt;
	private JButton chooseBtt;
	private JFileChooser fileChooser;
	private Clip clip;
	private boolean isPaused = false;
	private boolean isLooping = false;
	
	public MusicPlayer() {
		
		//setPreferredSize(new Dimension(600, 100));
		
		filePathField = new JTextField(20);
		playBtt = new JButton("Play");
		pauseBtt = new JButton("Pause");
		loopBtt = new JButton("Loop");
		chooseBtt = new JButton("Choose File");
		
		playBtt.addActionListener(this);
		pauseBtt.addActionListener(this);
		loopBtt.addActionListener(this);
		chooseBtt.addActionListener(this);
		
		add(filePathField);
		add(playBtt);
		add(pauseBtt);
		add(loopBtt);
		add(chooseBtt);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("WAV Files", "wav"));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == playBtt)
		{
			play();
		}
		else if (e.getSource() == pauseBtt)
		{	
			pause();
		}
		else if (e.getSource() == chooseBtt)
		{
			chooseFile();
		}
		else if (e.getSource() == loopBtt)
		{
			loop();
		}
		
	}
	
	private void play()
	{
		// Pausing existing clip
		if (clip != null && clip.isRunning()) {
			clip.stop();
		}
		
		// Trying to create a new clip
		try {
			File file = new File(filePathField.getText());
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
			
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			
			if (isLooping) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
			clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void pause()
	{		
		if (clip != null)
		{
			if (clip.isRunning())
			{
				clip.stop();
				pauseBtt.setText("Resume");
			}
			else if (isPaused)
			{	
				clip.start();
				
				if (isLooping) {
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
				
				pauseBtt.setText("Pause⠀");
			}
		}
		
		isPaused = !isPaused;
	}
	
	private void chooseFile()
	{
		fileChooser.setCurrentDirectory(new File(".")); //current directory
		int result = fileChooser.showOpenDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			filePathField.setText(selectedFile.getAbsolutePath());
		}
		
	}
	
	private void loop()
	{
		isLooping = !isLooping;
		
		if (isLooping)
		{
			loopBtt.setText("Unloop");
			
			if (clip.isRunning()) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		else {
			loopBtt.setText("Loop");
			
			if (clip.isRunning()) {
				clip.loop(0); // loop 0 times (only playing it once)
			}
		}
	}
	
	public void setSongPath(String path) {
		filePathField.setText(path);
	}

}
