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

// Class responsible for the user registering window.

public class RegisterWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private LoginControl loginControl;
	private JTextField userField;
	private JPasswordField passwordField;
	private JLabel warningText;
	private JButton vipBtt, registerBtt;
	private boolean isVip = false;
	
	public RegisterWindow()
	{
		// Setting up the window
		super("Register Window");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		
		// Instantiating variavbles
		loginControl = new LoginControl(); 
		
		userField = new JTextField(20);
		passwordField = new JPasswordField(20);
		warningText = new JLabel("");
		registerBtt = new JButton("Register");
		vipBtt = new JButton("Vip?");
		
		registerBtt.addActionListener(this);
		vipBtt.addActionListener(this);
		
		// Setting up panel
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
		listPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 110, 20));

		listPane.add(new JLabel("User"));
		listPane.add(userField);
		listPane.add(new JLabel("Password"));
		listPane.add(passwordField);
		listPane.add(registerBtt);
		listPane.add(vipBtt);
		listPane.add(warningText);
		
		getContentPane().add(listPane);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == registerBtt) {
			if (loginControl.addUser(userField.getText(), String.valueOf(passwordField.getPassword()), isVip)) {  
				dispose(); //closing window
			}
			else {
				setWarning("User already exists!");
			}
		}
		
		if (e.getSource() == vipBtt) {
			vipBtt.setText(isVip ? "Not Vip" : "Is Vip");
			isVip = !isVip;
		}
	}
	
	private void setWarning(String warning) {
		warningText.setText("<html><b><span style=\"color:#FF0000;\">" + warning + "</b></html>");
	}
	
}
