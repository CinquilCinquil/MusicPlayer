package external;

import javax.swing.JLabel;

// Class responsible for displaying the song currently playing.

public class CurrentSong extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private PlayerWindow frame;

	public CurrentSong(PlayerWindow frame) {
		this.frame = frame;
		setText("🎵: ");
	}
	
	public void setSong(String name) {
		setText("🎵: " + name + ";");
	}
	
	public void update() {
		setText("🎵 " + frame.currentSong.getName() + ";");
	}
	
}
