package external;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import repository.UserRepository;

public class PlayerWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected boolean isVip;
	protected int userId;
	protected String currentSongName;
	
	// Components of the screen
	protected MusicPlayer musicPlayer;
	protected UserInfo userInfo;
	protected AddContent addDir;
	protected AddContent addFile;
	protected CurrentSong currentSong;
	protected SongList songList;
	protected PlaylistList playlistList;
	protected SongList playlistSongList;
	protected JPanel baldis;
	
	public PlayerWindow(LoginScreen windowToClose, int userId, boolean isVip)
	{
		super("Music Player");
		dispatchEvent(new WindowEvent(windowToClose, WindowEvent.WINDOW_CLOSING));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		getContentPane().setBackground(Color.decode("#59C17F"));
		setLayout(new GridBagLayout());
		
		this.isVip = isVip;
		this.userId = userId;
		
		// ------- Instantiating components -------
		
		musicPlayer = new MusicPlayer(this);
		currentSong = new CurrentSong(this);
		userInfo = new UserInfo(this);
		songList = new SongList(this);
		addDir = new AddContent(this, songList, true);
		addFile = new AddContent(this, songList, false);
		playlistSongList = new SongList(this, true);
		playlistList = new PlaylistList(this, playlistSongList);
		baldis = new JPanel();
		
		// ------- Defining the layout of the components -------
		
		GridBagConstraints c = new GridBagConstraints();
		
		int w = 120;
		int h = 300;
		
		// ------- First row -------
		
		// UserName
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;c.gridy = 0;c.weightx = 0.02;c.weighty = 0.03;
		userInfo.setPreferredSize(new Dimension(w, 50));
		add(userInfo, c);
		
		// Add Directory
		c.gridx = 1;
		addDir.setPreferredSize(new Dimension(w, 25));
		add(addDir, c);

		// Add File
		c.gridx = 2;
		addFile.setPreferredSize(new Dimension(w, 25));
		add(addFile, c);
		
		// Music Player
		c.weightx = 0.94;c.gridx = 4;
		add(musicPlayer, c);
		
		// ------- Second row -------
		
		c.weightx = 0.33;c.gridx = 0;c.gridy = 1;
		currentSong.setPreferredSize(new Dimension(w, 25));
		add(currentSong, c);
		
		c.gridx = 1;
		currentSong.setPreferredSize(new Dimension(w, 25));
		add(new JButton("Add playlist"), c);
		
		c.gridx = 2;
		currentSong.setPreferredSize(new Dimension(w, 25));
		add(new JButton("Add playlist"), c);
		
		// ------- Third row -------
		
		JScrollPane p;
		
		// Song List
		c.weightx = 0.25;c.weighty = 0.44;c.gridx = 0;c.gridy = 2;
		p = songList.getScroll();
		p.setPreferredSize(new Dimension(0,h));
		add(p, c);
		
		addDir.setSongList(songList);
		addFile.setSongList(songList);
		
		if (isVip) {
			
			// Playlist
			c.gridx = 1;
			p = playlistList.getScroll();
			p.setPreferredSize(new Dimension(0,h));
			add(p, c);
			
			// PlaylistSongList
			c.gridx = 2;
			p = playlistSongList.getScroll();
			p.setPreferredSize(new Dimension(0,h));
			add(p, c);
		}
		else {
			playlistSongList = null;
			playlistList = null;
		}
		
		// Baldis gif
		c.gridx = 4;
		baldis.add(new JLabel(
				new ImageIcon((new ImageIcon("src/data/baldis.gif").getImage().getScaledInstance((int) (0.8 * h), h, Image.SCALE_DEFAULT))
		)));
		baldis.setBackground(getContentPane().getBackground());
		add(baldis, c);
		
		// ------- Fourth row -------
		
		// Bottom text
		c.weighty = 0.5;c.gridwidth = 3;c.gridx = 0;c.gridy = 3;       
		add(new JLabel(" made by: awesome people"), c);
		
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
