package javachat;

import java.util.ArrayList;

public class Buffer {

	ArrayList<String> messages;

	public Buffer() {

	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void flush() {
		this.messages.clear();
	}

	public void addMessage(String message) {
		this.messages.add(message);
	}

}
