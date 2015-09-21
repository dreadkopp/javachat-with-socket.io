package javachat;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Main {

	static Engine chatengine = null;

	public static void main(String[] args) throws MalformedURLException,
			URISyntaxException, InterruptedException {

		chatengine = new Engine();

		System.out.println("Setting up Socket ...");
		chatengine.initializeSocket("http://yoshi.dynu.com:3000");
		System.out.println("drawing GUI ...");
		chatengine.setGui(new Gui());
		System.out.println("here you go");
		chatengine.run();
		System.out.println("ID: " + chatengine.getSocket().id());
		chatengine.getSocket().connect();

	}

	public static Engine getChatengine() {
		if (chatengine != null) {
			return chatengine;
		} else
			return new Engine();
	}

}
