package external;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import repository.UserRepository;

public class UserInfo extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserInfo(int userId, boolean isVip) {
		//setBackground(new Color(200, 200, 200));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS) );
		
		add(new JLabel(" User: " + (new UserRepository()).getOne(userId).getName()));
		add(new JLabel(" Account Type: " + (isVip ? "Vip" : "Normal")));
	}

}
