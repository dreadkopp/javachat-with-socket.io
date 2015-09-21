package javachat;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Chatwindow {

	JPanel panel = new JPanel();

	String messages;

	public Chatwindow() {

		// generate a head with 'who'

		// add text with messages

	}

	public void update(ArrayList<Buffer> buffers) {

		// check Buffers for new messages since last update

	}

	public JPanel getPanel() {
		panel.setSize(350, 700);
		panel.setVisible(true);

		return panel;
	}
}
