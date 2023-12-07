package external;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.*;

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
		
		// Music Player
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.04;
		c.gridx = 4;
		c.gridy = 0;
		MusicPlayer musicPlayer = new MusicPlayer();
		add(musicPlayer, c);

		// ???
		c.weightx = 0.32;
		c.gridx = 0;
		c.gridy = 0;
		add(new JButton("Add Dir"), c);

		// ???
		c.weightx = 0.32;
		c.gridx = 1;
		c.gridy = 0;
		add(new JButton("-"), c);
		
		// ???
		c.weightx = 0.32;
		c.gridx = 2;
		c.gridy = 0;
		add(new JButton("-"), c);

		// Song List
		c.ipady = 260;
		c.weightx = 0.32;
		c.ipady = 260;
		c.gridx = 0;
		c.gridy = 1;
		add((new SongList(userId, musicPlayer)).getScroll(), c);
		
		if (isVip) {
			// Song List
			c.ipady = 276;
			c.weightx = 0.32;
			c.gridx = 1;
			c.gridy = 1;
			PlaylistList playlistList = new PlaylistList(userId);
			add(playlistList.getScroll(), c);
			
			// PlaylistSongList
			c.ipady = 292;
			c.ipadx = 68;
			c.weightx = 0.32;
			c.gridx = 2;
			c.gridy = 1;
			SongList playlistSongList = new SongList(playlistList, musicPlayer);
			add(playlistSongList.getScroll(), c);
			
			playlistList.setSongList(playlistSongList);
			
		}
		
		// Baldis
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.04;
		c.ipady = 40;
		c.gridx = 4;
		c.gridy = 1;
		JPanel baldis = new JPanel();
		baldis.add(new JLabel(
				new ImageIcon((new ImageIcon("src/data/baldis.gif").getImage().getScaledInstance(240, 300, Image.SCALE_DEFAULT))
		)));
		add(baldis, c);

		// ???
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(10,0,0,0);
		c.gridx = 1;       
		c.gridwidth = 2;   
		c.gridy = 2;       
		add(new JButton("-"), c);
		
		
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
