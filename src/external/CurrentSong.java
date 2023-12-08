package external;

import javax.swing.JLabel;

public class CurrentSong extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public CurrentSong() {
		setText("Current Song: ");
	}
	
	public void setSong(String name) {
		setText("Current Song: " + name + ";");
	}
	
}
