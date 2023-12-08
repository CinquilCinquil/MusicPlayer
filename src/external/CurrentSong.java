package external;

import javax.swing.JLabel;

public class CurrentSong extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private PlayerWindow frame;

	public CurrentSong(PlayerWindow frame) {
		this.frame = frame;
		setText("Current Song: ");
	}
	
	public void setSong(String name) {
		setText("Current Song: " + name + ";");
	}
	
	public void update() {
		setText("Current Song: " + frame.currentSongName + ";");
	}
	
}
