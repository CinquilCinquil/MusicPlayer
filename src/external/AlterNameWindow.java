package external;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import util.IAlterableName;

public class AlterNameWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField text;
	private JButton doneBtt;
	private IAlterableName comp;
	
	public AlterNameWindow(IAlterableName comp, String previousName) {
		super("Alter name");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setSize(300, 100);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new FlowLayout());
		
		this.comp = comp;
		
		text = new JTextField(20);
		text.setText(previousName);
		
		doneBtt = new JButton("Done");
		
		doneBtt.addActionListener(this);
		
		add(text);
		add(doneBtt);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == doneBtt) {
			comp.fromWindowAlterName(text.getText());
			dispose();
		}
		
	}

}
