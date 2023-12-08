package external;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import repository.UserRepository;

public class UserInfo extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserInfo(PlayerWindow frame) {
		setBackground(frame.getContentPane().getBackground());
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS) );
		
		add(new JLabel(" User: " + (new UserRepository()).getOne(frame.userId).getName()));
		add(new JLabel(" Account Type: " + (frame.isVip ? "Vip" : "Normal")));
	}

}
