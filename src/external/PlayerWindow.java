package external;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import repository.UserRepository;

public class PlayerWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private boolean isVip;
	
	public PlayerWindow(LoginScreen windowToClose, int userId, boolean isVip)
	{
		super("Music Player");
		dispatchEvent(new WindowEvent(windowToClose, WindowEvent.WINDOW_CLOSING));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.isVip = isVip;
		int w = 120;
		int h = 300;
		
		// ------- First row -------
		
		// Music Player
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.94;c.weighty = 0.03;c.gridx = 4;c.gridy = 0;
		MusicPlayer musicPlayer = new MusicPlayer();
		add(musicPlayer, c);
		
		// UserName
		c.gridx = 0;c.gridy = 0;
		UserInfo userInfo = new UserInfo(userId, isVip);
		userInfo.setPreferredSize(new Dimension(w, 50));
		add(userInfo, c);
		
		// Add Directory
		c.weightx = 0.02;c.gridx = 1;c.gridy = 0;
		AddContent addDir = new AddContent(userId, true);
		addDir.setPreferredSize(new Dimension(w, 25));
		add(addDir, c);

		// Add File
		c.gridx = 2;c.gridy = 0;
		AddContent addFile = new AddContent(userId, false);
		addFile.setPreferredSize(new Dimension(w, 25));
		add(addFile, c);
		
		// ------- Second row -------
		
		c.weightx = 1;c.gridx = 0;c.gridy = 1;
		CurrentSong currentSong = new CurrentSong();
		currentSong.setPreferredSize(new Dimension(w, 25));
		add(currentSong, c);
		
		// ------- Third row -------
		
		JScrollPane p;
		
		// Song List
		c.weightx = 0.25;c.weighty = 0.44;c.gridx = 0;c.gridy = 2;
		SongList songList = new SongList(userId, musicPlayer);
		p = songList.getScroll();
		p.setPreferredSize(new Dimension(0,h));
		add(p, c);
		
		addDir.setSongList(songList);
		addFile.setSongList(songList);
		
		if (isVip) {
			
			// Playlist
			c.gridx = 1;c.gridy = 2;
			PlaylistList playlistList = new PlaylistList(userId);
			p = playlistList.getScroll();
			p.setPreferredSize(new Dimension(0,h));
			add(p, c);
			
			// PlaylistSongList
			c.gridx = 2;c.gridy = 2;
			SongList playlistSongList = new SongList(playlistList, musicPlayer);
			p = playlistSongList.getScroll();
			p.setPreferredSize(new Dimension(0,h));
			add(p, c);
			
			playlistList.setSongList(playlistSongList);
			
		}
		
		// Baldis gif
		c.gridx = 4;c.gridy = 2;
		JPanel baldis = new JPanel();
		baldis.add(new JLabel(
				new ImageIcon((new ImageIcon("src/data/baldis.gif").getImage().getScaledInstance((int) (0.8 * h), h, Image.SCALE_DEFAULT))
		)));
		add(baldis, c);
		
		// ------- Fourth row -------
		
		// Bottom text
		c.weighty = 0.5;c.gridwidth = 3;c.gridx = 0;c.gridy = 3;       
		add(new JLabel("made by: awsome people"), c);
		
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
