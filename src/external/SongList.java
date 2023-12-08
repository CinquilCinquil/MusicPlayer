package external;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import entity.Playlist;
import entity.Song;
import repository.UserRepository;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class SongList extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private PlayerWindow frame;
	private ArrayList<SongItem> songList;
	private UserRepository userRepository;
	private MusicPlayer musicPlayer;
	private JLabel title;
	private int userId;
	
	public class SongItem extends JLabel implements MouseListener {
		
		private static final long serialVersionUID = 1L;
		
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
			musicPlayer.setSong(selectedFile.getAbsolutePath());
			frame.currentSongName = song.getName();
			frame.currentSong.update();
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
		
		this.userId = frame.userId;
		
		this.musicPlayer = frame.musicPlayer;
		
		userRepository = new UserRepository();
		
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
	}
	
	public ArrayList<SongItem> toSongItem(ArrayList<Song> list) {
		
		ArrayList<SongItem> newList = new ArrayList<SongItem>();
		
		for (Song song : list) {
			newList.add(new SongItem(song));
		}
		
		return newList;
	}
	
	public void updateCurrentPlaylist(Playlist p) {
		
		if (p != null) {
		
			title.setText("<html><b><span style=\"color:#000000;font-size:14px;\">" + 
					p.getName() + "</b></html>");
			
			songList = toSongItem(p.getSongs());
			
			for (SongItem song : songList) {
				add(song);
			}	
		
		}
	}
	
	public void updateFiles() {
		
		// Adding user songs
		
		songList = toSongItem(userRepository.getUserSongs(userId));
		
		for (SongItem song : songList) {
			add(song);
		}
		
		// Adding songs in directories
		ArrayList<String> dirs = userRepository.getUserDirectories(userId);
		
		for (String dir : dirs) {
			
			File currentDir = new File(dir);
			
			for (File file : currentDir.listFiles()) {
				
				if (getExtension(file).compareTo("wav") == 0) {
					add(new SongItem(
							new Song(userId, file.getName(), "?", file.getPath())
					));
				}
			}
			
		}
		
	}
	
	private String getExtension(File file) {
		String fileName = file.toString();

	    int index = fileName.lastIndexOf('.');
	    if(index > 0) {
	      String extension = fileName.substring(index + 1);
	      return extension;
	    }
	    
	    return null;
	}

}
