package external;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import control.ContentController;
import entity.Playlist;
import repository.UserRepository;

public class PlaylistList extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private PlayerWindow frame;
	private ArrayList<PlaylistItem> playlistList;
	private ContentController contentController;
	private Playlist currentPlaylist;
	private SongList songList;
	
	public class PlaylistItem extends JLabel implements MouseListener {
		
		private static final long serialVersionUID = 1L;
		
		private Playlist playlist = null;
		
		public PlaylistItem(String text) {
			super("<html><b><span style=\"color:#000000;font-size:9.5px;\">" + text + "</b></html>");
			addMouseListener(this);
		}
		
		public PlaylistItem(Playlist playlist) {
			super("<html><b><span style=\"color:#000000;font-size:9.5px;\">" + playlist.getName() + "</b></html>");
			addMouseListener(this);
			this.playlist = playlist;
		}
		
		public Playlist getPlaylist() {
			return playlist;
		}
		
		@Override
	    public void mouseClicked(MouseEvent e) {
			currentPlaylist = playlist;
			songList.updateCurrentPlaylist(currentPlaylist);
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
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	public ArrayList<PlaylistItem> toPlaylistItem(ArrayList<Playlist> list) {
		
		ArrayList<PlaylistItem> newList = new ArrayList<PlaylistItem>();
		
		for (Playlist playlist : list) {
			newList.add(new PlaylistItem(playlist));
		}
		
		return newList;
	}
	
	public void update() {
		
		clearPanelList();
		
		playlistList = toPlaylistItem(contentController.getUserPlaylists(frame.userId));
		
		for (PlaylistItem playlist : playlistList) {
			add(playlist);
		}
		
		revalidate();
	}
	
	public Playlist getCurrentPlaylist() {
		return currentPlaylist;
	}
	
	private void clearPanelList() {
		for (Component p : getComponents()) {
			if (p instanceof PlaylistItem) {
				remove(p);
			}
		}
	}
	
}
