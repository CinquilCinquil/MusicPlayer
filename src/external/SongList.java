package external;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import entity.Playlist;
import entity.Song;
import repository.UserRepository;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class SongList extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<SongItem> songList;
	private UserRepository userRepository;
	private MusicPlayer musicPlayer;
	private PlaylistList playlistList;
	private JLabel title;
	
	public class SongItem extends JLabel implements MouseListener {
		
		private Song song = null;
		
		public SongItem(String text) {
			super(text);
			addMouseListener(this);
		}
		
		public SongItem(Song song) {
			super(song.getName());
			addMouseListener(this);
			this.song = song;
		}
		
		public Song getSong() {
			return song;
		}
		
		@Override
	    public void mouseClicked(MouseEvent e) {
			File selectedFile = new File(song.getFilepath());
			musicPlayer.setSongPath(selectedFile.getAbsolutePath());
	    }
	    @Override
	    public void mousePressed(MouseEvent e) {
	    }
	    @Override
	    public void mouseReleased(MouseEvent e) {
	    }
	    @Override
	    public void mouseEntered(MouseEvent e) {
	    }
	    @Override
	    public void mouseExited(MouseEvent e) {
	    }
	}
	
	public JScrollPane getScroll() {
		
		JScrollPane j = new JScrollPane(this);
		j.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return j;
	}
	
	// Normal song list
	public SongList(int userId, MusicPlayer musicPlayer) {
		setBackground(new Color(141, 193, 163));
		setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS) );
		
		title = new JLabel("<html><b><span style=\"color:#000000;font-size:14px;\">" + " Song List" + "</b></html>");
		
		add(title);
		
		this.musicPlayer = musicPlayer;
		
		userRepository = new UserRepository();
		
		songList = toSongItem(userRepository.getUserSongs(userId));
		
		for (SongItem song : songList) {
			add(song);
		}
		
	}
	
	// Song list for a certain playlist
	public SongList(PlaylistList playlistList, MusicPlayer musicPlayer) {
		setBackground(new Color(219, 81, 101));
		setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS) );

		title = new JLabel("<html><b><span style=\"color:#000000;font-size:14px;\">" + "-" + "</b></html>");
		
		add(title);
		
		this.musicPlayer = musicPlayer;
		
		this.playlistList = playlistList;
		
		updateCurrentPlaylist();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	public ArrayList<SongItem> toSongItem(ArrayList<Song> list) {
		
		ArrayList<SongItem> newList = new ArrayList<SongItem>();
		
		for (Song song : list) {
			newList.add(new SongItem(song));
		}
		
		return newList;
	}
	
	public void updateCurrentPlaylist() {
		
		Playlist p = playlistList.getCurrentPlaylist();
		
		if (p != null) {
		
			title.setText("<html><b><span style=\"color:#000000;font-size:14px;\">" + 
					p.getName() + "</b></html>");
			
			
			songList = toSongItem(playlistList.getCurrentPlaylist().getSongs());
			
			for (SongItem song : songList) {
				add(song);
			}	
		
		}
	}

}
