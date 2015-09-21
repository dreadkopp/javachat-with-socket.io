package javachat;

import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Gui {

	JPanel log = new JPanel();
	JFrame frame = new JFrame();
	JTabbedPane tabbedPane = new JTabbedPane();
	ArrayList<Chatwindow> tabs = new ArrayList<Chatwindow>();
	JTextArea textArea;
	JScrollPane scrollPane = new JScrollPane(log);

	public ArrayList<Chatwindow> getTabs() {
		return tabs;
	}

	public Gui() {
		frame.setSize(400, 800);
		frame.setVisible(false);
		frame.setResizable(true);
		frame.getContentPane().add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void updatetabs() {
		tabbedPane.removeAll();
		tabbedPane.addTab("Log", null, scrollPane, "Does nothing");
		for (Chatwindow tab : tabs) {
			String name = tab.getLabel().getText();
			tabbedPane.addTab(name, null, tab.getPanel(), "");

		}
	}

	public void update(Buffer buffer) {

		while (buffer.size() >= 1) {

			String[] temp = buffer.pop().split(" sagt:");

			for (Chatwindow tab : tabs) {
				if (tab.getLabel().getText().equals(temp[0])) {
					tab.getText().append(temp[1]);
					tab.getText().append("\n");
				}

			}
		}

	}

	public void show() {

		frame.setVisible(true);

		textArea = new JTextArea(50, 10);
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(
				textArea));
		// re-assigns standard output stream and error output stream
		System.setOut(printStream);
		System.setErr(printStream);
		log.add(textArea);

		tabbedPane.addTab("Log", null, scrollPane, "Does nothing");
	}

	public JFrame getGui() {

		return this.frame;
	}

}
