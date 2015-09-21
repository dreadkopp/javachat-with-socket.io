package javachat;

import java.util.ArrayList;

public class Buffer {

	ArrayList<String> messages = new ArrayList<String>();

	public Buffer() {

	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void flush() {
		this.messages.clear();
	}

	public void push(String message) {
		this.messages.add(message);
	}

	public String pop() {
		if (messages.isEmpty())
			return "";
		else {
			String tmp = messages.get(messages.size() - 1);
			messages.remove(messages.size() - 1);
			return tmp;
		}
	}

	public int size() {
		return messages.size();
	}

}
