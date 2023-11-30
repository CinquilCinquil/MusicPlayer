package external;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class MusicPlayerJFrame extends JFrame implements ActionListener {

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
	
	public MusicPlayerJFrame()
	{
		super("Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
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
		
		// Baldis
		add(new JLabel(new ImageIcon("src/data/baldis.gif")));
		
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("WAV Files", "wav"));
		
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setVisible(true);
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
				
				pauseBtt.setText("Pause");
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
}
