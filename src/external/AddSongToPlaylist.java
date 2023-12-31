package external;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import control.ContentController;
import entity.Playlist;

// Class responsible for the Add Song to Playlist button.
// When pressed, the currently selected song is added to the currently selected playlist.

public class AddSongToPlaylist extends JButton implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private PlayerWindow frame;
	private PlaylistList playlistList;
	private ContentController contentController;
	
	public AddSongToPlaylist(PlayerWindow frame, PlaylistList playlistList) {
		super("<html><b><span style=\"color:#000000;font-size:7px;\">Add Song to " + 
				"Playlist" + "</b></html>");
		
		this.playlistList = playlistList;
		
		this.frame = frame;
		
		this.contentController = new ContentController();
		
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Playlist p = playlistList.getCurrentPlaylist();
		if (p != null) {
			if (frame.currentSong != null) {
				if (!p.hasSong(frame.currentSong.getId()))
				{
					// associating song with playlist in repository
					contentController.addSongToPlaylist(p.getId(), frame.currentSong);
				
					// adding song to the playlist SongList
					p.addSong(frame.currentSong);
					playlistList.getSongList().updateCurrentPlaylist(p);
				}
			}
		}
	}

}
