package external;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import util.IAlterableItem;

// Class responsible for window that appears when the user wants to edit an item on a list.
// The window allows the user to change the name of the item or to delete it.

public class AlterItemWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField text;
	private JButton doneBtt, deleteBtt;
	private IAlterableItem comp;
	
	public AlterItemWindow(IAlterableItem comp, String previousName) {
		super("Edit " + previousName);
		setResizable(false);
		setSize(300, 100);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new FlowLayout());
		
		this.comp = comp;
		
		text = new JTextField(20);
		text.setText(previousName);
		
		doneBtt = new JButton("Done");
		deleteBtt = new JButton("Delete");
		
		doneBtt.addActionListener(this);
		deleteBtt.addActionListener(this);
		
		add(text);
		add(doneBtt);
		add(deleteBtt);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == doneBtt) {
			comp.fromWindowAlterName(text.getText());
			dispose();
		}
		
		if (e.getSource() == deleteBtt) {
			comp.fromWindowDelete();
			dispose();
		}
		
	}

}
