package external;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import control.ContentController;

public class AddPlaylist extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ContentController addContentController;
	private PlayerWindow frame;
	private PlaylistList playlistList;
	
	public AddPlaylist(PlayerWindow frame, PlaylistList playlistList) {
		super("Add Playlist");
		addActionListener(this);
		
		this.frame = frame;
		
		this.playlistList = playlistList;
		
		this.addContentController = new ContentController();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addContentController.addPlaylist(frame.userId);
		System.out.println("amwgwa");
		playlistList.update();
	}

}
