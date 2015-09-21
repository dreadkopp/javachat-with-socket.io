package javachat;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class Engine {

	Buffer buffer = new Buffer();
	Gui gui;
	Socket socket;
	String tempwho = "";
	String tempmessage = "";
	ArrayList<String> users = new ArrayList<String>();

	public Engine() {

	}

	public void initializeSocket(String url) {

		// set up socket
		try {
			socket = IO.socket(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		// tell me when connected
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("Yey, i connected to the server");

			}

		});

		// print incomming message
		// TODO: write this into the buffer
		socket.on("who", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				tempwho = args[0].toString();
			}

		}).on("support message", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				tempmessage = tempwho + " sagt: " + args[0].toString();
				System.out.println(tempmessage);

				buffer.push(tempmessage);
				if (!users.contains(tempwho)) {
					gui.getTabs().add(new Chatwindow(tempwho));
					gui.updatetabs();
				}
				users.add(tempwho);
				// clean
				tempwho = "";
				tempmessage = "";
				gui.update(buffer);
				buffer.flush();

			}

		});

		socket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				String data = (String) args[0];
				System.out.println(data.toString());
			}
		}).on(Socket.EVENT_ERROR, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Exception err = (Exception) args[0];
				System.out.println(err);
			}
		});

		// tell me when disconnected
		socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("I disconnected :(");
				socket.disconnect();
			}
		});

		//
		socket.connect();

	}

	public ArrayList<String> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}

	public void setGui(Gui gui) {
		this.gui = gui;

	}

	public void run() {

		gui.show();
		gui.updatetabs();

	}

	public void send(String message) {

		socket.emit("chat message", message);
	}

	public Socket getSocket() {
		return socket;
	}

	public Gui getGui() {
		return this.gui;
	}

}
