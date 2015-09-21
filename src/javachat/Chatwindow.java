package javachat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Chatwindow {

	JLabel label = new JLabel();
	JPanel panel = new JPanel();
	JTextArea text = new JTextArea();
	JButton send = new JButton("Send");
	JButton close = new JButton("Schließen");

	private Chatwindow getMe() {

		return this;
	}

	public Chatwindow(String who) {
		this.label.setText(who);
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getChatengine().send(
						"a button was clicked" + " ID: " + "the test button");

			}

		});
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int index = Main.getChatengine().getGui().getTabs()
						.lastIndexOf(getMe());
				Main.getChatengine().getGui().getTabs().remove(index);
				Main.getChatengine().getUsers().remove(getLabel().getText());
				Main.getChatengine().getGui().updatetabs();

			}

		});
		text.setSize(100, 500);
		panel.add(text);
		panel.add(send);
		panel.add(close);

	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JPanel getPanel() {

		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextArea getText() {
		return text;
	}

	public void setText(JTextArea text) {
		this.text = text;
	}

}
