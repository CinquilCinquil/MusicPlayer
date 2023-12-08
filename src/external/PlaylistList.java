package external;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import control.ContentController;
import entity.Playlist;
import util.IAlterableItem;

public class PlaylistList extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private PlayerWindow frame;
	private ArrayList<PlaylistItem> playlistList;
	private ContentController contentController;
	private Playlist currentPlaylist;
	private SongList songList;
	
	public class PlaylistItem extends JLabel implements MouseListener, IAlterableItem {
		
		private static final long serialVersionUID = 1L;
		
		private Playlist playlist = null;
		private PlaylistList panel;
		
		public PlaylistItem(PlaylistList panel, String text) {
			super("<html><b><span style=\"color:#000000;font-size:9.5px;\">" + text + "</b></html>");
			this.panel = panel;
			addMouseListener(this);
		}
		
		public PlaylistItem(PlaylistList panel, Playlist playlist) {
			addMouseListener(this);
			this.playlist = playlist;
			this.panel = panel;
			updateName();
		}
		
		public Playlist getPlaylist() {
			return playlist;
		}
		
		public void fromWindowAlterName(String newName) {
			if (playlist != null)
			{
				playlist.setName(newName);
				updateName();
				contentController.updatePlaylist(playlist);
			}
		}
		
		public void fromWindowDelete() {
			contentController.deletePlaylist(playlist);
			panel.update();
		}
		
		public void updateName() {
			setText("<html><b><span style=\"color:#000000;font-size:9.5px;\">" + playlist.getName() + "</b></html>");
		}
		
		@Override
	    public void mouseClicked(MouseEvent e) {
			
			if (SwingUtilities.isLeftMouseButton(e))
			{
				currentPlaylist = playlist;
				songList.updateCurrentPlaylist(currentPlaylist);
			}
			else {
				new AlterItemWindow(this, playlist.getName());
			}
			
	    }
	    @Override
	    public void mousePressed(MouseEvent e) {
	    }
	    @Override
	    public void mouseReleased(MouseEvent e) {
	    }
	    @Override
	    public void mouseEntered(MouseEvent e) {
	    	setText("<html><b><span style=\"color:#FFFFFF;font-size:9.5px;\">" + playlist.getName() + "</b></html>");
	    }
	    @Override
	    public void mouseExited(MouseEvent e) {
	    	setText("<html><b><span style=\"color:#000000;font-size:9.5px;\">" + playlist.getName() + "</b></html>");
	    }
	}
	
	public JScrollPane getScroll() {
		
		JScrollPane j = new JScrollPane(this);
		j.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return j;
	}
	
	public PlaylistList(PlayerWindow frame, SongList songList) {
		setBackground(new Color(141, 100, 163));
		setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS) );

		add(new JLabel("<html><b><span style=\"color:#000000;font-size:14px;\">" + " Playlists" + "</b></html>"));	
		
		this.frame = frame;
		
		this.songList = songList;
		
		contentController = new ContentController();
		
		update();
		
	}

	public ArrayList<PlaylistItem> toPlaylistItem(ArrayList<Playlist> list) {
		
		ArrayList<PlaylistItem> newList = new ArrayList<PlaylistItem>();
		
		for (Playlist playlist : list) {
			newList.add(new PlaylistItem(this, playlist));
		}
		
		return newList;
	}
	
	// Updates PlaylistList's content (its appearece, the user's playlists, etc ...)
	public void update() {
		
		clearPanelList();
		
		repaint();
		
		playlistList = toPlaylistItem(contentController.getUserPlaylists(frame.userId));
		
		for (PlaylistItem playlist : playlistList) {
			add(playlist);
		}
		
		revalidate();
	}

	// Removes all PlaylistItems
	private void clearPanelList() {
		for (Component p : getComponents()) {
			if (p instanceof PlaylistItem) {
				remove(p);
			}
		}
	}

	public Playlist getCurrentPlaylist() {
		return currentPlaylist;
	}
	
	public SongList getSongList() {
		return songList;
	}
}
