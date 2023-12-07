package external;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import repository.SongRepository;
import repository.UserRepository;

public class AddContent extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JFileChooser fileChooser;
	private int userId;
	private UserRepository userRepository;
	private SongRepository songRepository;
	private boolean addDir;
	private SongList songList;
	
	public AddContent(int userId, boolean addDir) {
		setText(addDir ? "Add Dir" : "Add File");

		addActionListener(this);
		
		this.addDir = addDir;
		
		this.userId = userId;
		
		userRepository = new UserRepository();
		songRepository = new SongRepository();
		fileChooser = new JFileChooser();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this)
		{
			songList.updateFiles();
			if (addDir) {
				chooseDir();
			}
			else {
				chooseFile();
			}
		}
		
	}
	
	private void chooseDir()
	{
		fileChooser.setCurrentDirectory(new File(".")); //current directory
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			addDir(selectedFile.getAbsolutePath());
		}
		
	}
	
	private void chooseFile()
	{
		fileChooser.setCurrentDirectory(new File(".")); //current directory
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = fileChooser.getSelectedFile();
			addFile(selectedFile.getAbsolutePath());
		}
		
	}
	
	private void addDir(String filepath) {
		userRepository.addUserDir(userId, filepath);
	}
	
	private void addFile(String filepath) {
		songRepository.addUserSong(userId, filepath);
	}

	public void setSongList(SongList songList) {
		this.songList = songList;
	}
}
