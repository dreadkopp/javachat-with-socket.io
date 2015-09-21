package javachat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Gui {

	JFrame frame = new JFrame();
	JTabbedPane tabbedPane = new JTabbedPane();

	public Gui() {
		frame.setSize(400, 800);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.getContentPane().add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void update(ArrayList<Buffer> buffers) {

		// check buffers since last update for new ids -> add new panel (tab)

	}

	public void show() {

		frame.setVisible(true);

		JPanel log = new JPanel();
		JTextArea textArea;
		textArea = new JTextArea(50, 10);
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(
				textArea));
		// re-assigns standard output stream and error output stream
		System.setOut(printStream);
		System.setErr(printStream);
		log.add(textArea);
		JScrollPane scrollPane = new JScrollPane(log);
		tabbedPane.addTab("Log", null, scrollPane, "Does nothing");

		// TEST

		JPanel panel1 = new JPanel();
		panel1.add(new JTextArea());

		JButton button = new JButton("Send something");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main.getChatengine().send("a button was clicked");

			}
		});

		panel1.add(button);

		tabbedPane.addTab("User 1", null, panel1, "for testing purpose only");

	}

	public JFrame getGui() {

		return this.frame;
	}

}
