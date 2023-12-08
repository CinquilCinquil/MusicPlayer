package external;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import control.ContentController;
import entity.Playlist;
import entity.Song;
import util.IAlterableItem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class SongList extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private PlayerWindow frame;
	private ArrayList<SongItem> songList;
	private ContentController contentController;
	private MusicPlayer musicPlayer;
	private JLabel title;
	private boolean playlistSongList = false;
	
	public class SongItem extends JLabel implements MouseListener, IAlterableItem {
		
		private static final long serialVersionUID = 1L;
		
		private Song song = null;
		private SongList panel;
		
		public SongItem(SongList panel, String text) {
			super("<html><b><span style=\"color:#000000;font-size:9.5px;\">" + text + "</b></html>");
			addMouseListener(this);
			this.panel = panel;
		}
		
		public SongItem(SongList panel, Song song) {
			super("<html><b><span style=\"color:#000000;font-size:9.5px;\">" + song.getName() + "</b></html>");
			addMouseListener(this);
			this.song = song;
			this.panel = panel;
		}
		
		public Song getSong() {
			return song;
		}
		
		@Override
		public void fromWindowAlterName(String newName) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void fromWindowDelete() {
			if (playlistSongList) {
				
				Playlist p = frame.playlistList.getCurrentPlaylist();
				
				if (p != null) {
					contentController.deletePlaylistSong(p, song);
					p.removeSong(song);
					panel.updateCurrentPlaylist(p);
				}
			}
			else {
				contentController.deleteUserSong(frame.userId, song);
				panel.updateFiles();
			}
		}
		
		@Override
	    public void mouseClicked(MouseEvent e) {
			
			if (SwingUtilities.isLeftMouseButton(e))
			{
				File selectedFile = new File(song.getFilepath());
				musicPlayer.setSong(selectedFile.getAbsolutePath());
				frame.currentSong = song;
				frame.currentSongComponent.update();
			}
			else {
				new AlterItemWindow(this, song.getName());
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
	    	setText("<html><b><span style=\"color:#FFFFFF;font-size:9.5px;\">" + song.getName() + "</b></html>");
	    }
	    @Override
	    public void mouseExited(MouseEvent e) {
	    	setText("<html><b><span style=\"color:#000000;font-size:9.5px;\">" + song.getName() + "</b></html>");
	    }

	}
	
	public JScrollPane getScroll() {
		
		JScrollPane j = new JScrollPane(this);
		j.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		j.setBackground(frame.getBackground());
		return j;
	}
	
	// Normal song list
	public SongList(PlayerWindow frame) {
		setBackground(new Color(141, 193, 163));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS) );
		
		title = new JLabel("<html><b><span style=\"color:#000000;font-size:14px;\">" + " Song List" + "</b></html>");
		
		add(title);
		
		this.frame = frame;
		
		this.musicPlayer = frame.musicPlayer;
		
		contentController = new ContentController();
		
		updateFiles();
		
	}
	
	// Song list for a certain playlist
	public SongList(PlayerWindow frame, boolean playlistSongList) {
		setBackground(new Color(219, 81, 101));
		setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS) );

		title = new JLabel("<html><b><span style=\"color:#000000;font-size:14px;\">" + "-" + "</b></html>");
		
		add(title);
		
		this.frame = frame;
		
		this.musicPlayer = frame.musicPlayer;
		
		this.playlistSongList = playlistSongList;
		
		contentController = new ContentController();
	}
	
	public ArrayList<SongItem> toSongItem(ArrayList<Song> list) {
		
		ArrayList<SongItem> newList = new ArrayList<SongItem>();
		
		for (Song song : list) {
			newList.add(new SongItem(this, song));
		}
		
		return newList;
	}
	
	// An update for the Playlist song list
	public void updateCurrentPlaylist(Playlist p) {
		
		repaint();
		
		clearPanelList();
		
		if (p != null) {

			title.setText("<html><b><span style=\"color:#000000;font-size:14px;\">" + 
					p.getName() + "</b></html>");
			
			songList = toSongItem(p.getSongs());
			
			for (SongItem song : songList) {
				add(song);
			}	
		
		}
		
		revalidate();
	}
	
	// An update for the User song list
	public void updateFiles() {
		
		repaint();
		
		clearPanelList();
		
		songList = toSongItem(contentController.updateUserSongs(frame.userId));
		
		for (SongItem song : songList) {
			add(song);
		}
		
		revalidate();
		
	}

	private void clearPanelList() {
		for (Component p : getComponents()) {
			if (p instanceof SongItem) {
				remove(p);
			}
		}
	}

}
