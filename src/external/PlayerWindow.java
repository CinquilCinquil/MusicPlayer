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
	
	public PlayerWindow(LoginScreen windowToClose, boolean isVip)
	{
		super("Music Player");
		dispatchEvent(new WindowEvent(windowToClose, WindowEvent.WINDOW_CLOSING));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		this.isVip = isVip;
		
		// Baldis
		JPanel baldis = new JPanel();
		baldis.setPreferredSize(new Dimension(400,500));
		baldis.add(new JLabel(new ImageIcon("src/data/baldis.gif")));

		add(baldis, BorderLayout.LINE_END);
		add(new MusicPlayer(), BorderLayout.LINE_START);
		
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
