package external;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import repository.UserRepository;

public class LoginScreen extends JFrame implements ActionListener {

	private JTextField userField;
	private JPasswordField passwordField;
	private JLabel warningText;
	private JButton logBtt;
	private UserRepository userRepository;
	
	public LoginScreen()
	{
		// Setting up the window
		super("Login Screen");
		setSize(450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		// Instantiating variavbles
		userRepository = new UserRepository();
		
		userField = new JTextField(20);
		passwordField = new JPasswordField(20);
		warningText = new JLabel("");
		logBtt = new JButton("Log In");
		
		logBtt.addActionListener(this);
		
		// Setting up panel
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
		listPane.setBorder(BorderFactory.createEmptyBorder(80, 100, 120, 100));

		listPane.add(new JLabel("User"));
		listPane.add(userField);
		listPane.add(new JLabel("Password"));
		listPane.add(passwordField);
		listPane.add(logBtt);
		listPane.add(warningText);
		
		getContentPane().add(listPane);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == logBtt) {
			logIn();
		}
		
	}
	
	private void logIn() {
		
		String name = userField.getText();
		int id = userRepository.getUserId(name);
		
		if (id >= 1)
		{	
			// if the password is valid
			int passComp = userRepository.getUserPassword(id).compareTo(String.valueOf(passwordField.getPassword()));
			if (passComp == 0)
			{
				new PlayerWindow(this, id, userRepository.isVip(id));
			}
			else {
				setWarning("Invalid password!");
			}
		}
		else {
			setWarning("Invalid user!");
		}
	}
	
	private void setWarning(String warning) {
		warningText.setText("<html><b><span style=\"color:#FF0000;\">" + warning + "</b></html>");
	}

}
