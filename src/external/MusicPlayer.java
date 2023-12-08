package external;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.MusicPlayerController;
import service.MusicPlayerService;
import service.MusicPlayerState;

public class MusicPlayer extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField filePathField;
	private JButton playBtt;
	private JButton pauseBtt;
	private JButton loopBtt;
	private JButton chooseBtt;
	private MusicPlayerController musicPlayerController;
	
	public MusicPlayer() {
		
		musicPlayerController = new MusicPlayerController();
		
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
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		MusicPlayerState playerState = MusicPlayerState.OVER;
		
		// Checking buttons
		
		if (source == playBtt)
		{
			playerState = musicPlayerController.play(filePathField.getText());
		}
		else if (source == pauseBtt)
		{	
			playerState = musicPlayerController.pause();
		}
		else if (source == chooseBtt)
		{
			filePathField.setText(musicPlayerController.chooseFile(this));
		}
		else if (source == loopBtt)
		{
			musicPlayerController.loop();
			
			// Switching text between 'Loop' and 'UnLoop'
			if (loopBtt.getText().compareTo("Loop") == 0) {
				loopBtt.setText("UnLoop");
			} else {
				loopBtt.setText("Loop");
			}
		}
		
		changeBttText(playerState);
		
	}
	
	// Sets the current song in the player
	public void setSong(String path) {
		filePathField.setText(path);
		
		MusicPlayerState playerState = musicPlayerController.play(filePathField.getText());
		changeBttText(playerState);
	}
	
	// Changes the text on the buttons depending on the MusicPlayer state
	private void changeBttText(MusicPlayerState playerState) {
		switch(playerState)
		{
			case PLAYING: pauseBtt.setText("Pause"); break;
			case PAUSED: pauseBtt.setText("Resume"); break;
			case OVER: pauseBtt.setText("Pause"); break;
		}
	}

}
