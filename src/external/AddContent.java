package external;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import control.ContentController;

// Class responsible for the Add Song and Add directory buttons.
// When pressed, the user can add a file or a directory of files to their song list.

public class AddContent extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JFileChooser fileChooser;
	private ContentController contentController;
	private SongList songList;
	private PlayerWindow frame;
	private boolean addDir;
	
	public AddContent(PlayerWindow frame, SongList songList, boolean addDir) {
		setText(addDir ? "Add Dir" : "Add Song");

		addActionListener(this);
		
		this.frame = frame;
		
		this.songList = songList;
		
		this.addDir = addDir;
		
		contentController = new ContentController();
		fileChooser = new JFileChooser();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (addDir) {
			chooseDir();
		}
		else {
			chooseFile();
		}
		songList.updateFiles();
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
		contentController.addUserDir(frame.userId, filepath);
	}
	
	private void addFile(String filepath) {
		contentController.addUserSong(frame.userId, filepath);
	}

	public void setSongList(SongList songList) {
		this.songList = songList;
	}
}
