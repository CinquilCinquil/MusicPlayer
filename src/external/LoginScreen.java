package external;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.LoginControl;
import repository.UserRepository;

public class LoginScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private LoginControl loginControl;
	private JTextField userField;
	private JPasswordField passwordField;
	private JLabel warningText;
	private JButton logBtt, registerBtt;
	
	public LoginScreen()
	{
		// ----- Setting up the window -----
		super("Login Screen");
		setSize(450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		// ----- Instantiating variavbles -----
		loginControl = new LoginControl(); 
		
		userField = new JTextField(20);
		passwordField = new JPasswordField(20);
		warningText = new JLabel("");
		logBtt = new JButton("Log In");
		registerBtt = new JButton("Register");
		
		logBtt.addActionListener(this);
		registerBtt.addActionListener(this);
		
		// ----- Setting up panel  -----
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
		listPane.setBorder(BorderFactory.createEmptyBorder(80, 100, 120, 100));

		listPane.add(new JLabel("User"));
		listPane.add(userField);
		listPane.add(new JLabel("Password"));
		listPane.add(passwordField);
		listPane.add(logBtt);
		listPane.add(registerBtt);
		listPane.add(warningText);
		
		getContentPane().add(listPane);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == logBtt) {
			logIn();
		}
		if (e.getSource() == registerBtt) {
			register();
		}
		
	}
	
	private void logIn() {
	
		String name = userField.getText();
		
		switch(loginControl.logIn(name, String.valueOf(passwordField.getPassword()))) {
			case OK: new PlayerWindow(this, loginControl.getUserId(name), loginControl.isVip(name)); break;
			case USER: setWarning("Invalid user!"); break;
			case PASSWORD: setWarning("Invalid password!"); break;
		}
	
	}
	
	private void register() {
		new RegisterWindow();
	}
	
	private void setWarning(String warning) {
		warningText.setText("<html><b><span style=\"color:#FF0000;\">" + warning + "</b></html>");
	}

}
