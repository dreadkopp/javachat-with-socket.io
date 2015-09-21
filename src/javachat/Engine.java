package javachat;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class Engine {

	ArrayList<Buffer> buffers;
	Gui gui;
	ArrayList<Chatwindow> windows;
	Socket socket;

	public Engine() {
		
		
	}

	public void initializeSocket(String url) {
		
		
		//set up socket
		try {
			socket = IO.socket(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		

		//tell me when connected
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("Yey, i connected to the server");

			}

		});

		//print incomming message
		//TODO: write this into the buffer
		socket.on("chat message",  new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("there was something: " + args.toString());

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
		
		
		//tell me when disconnected
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

	public void update(ArrayList<Buffer> buffers) {

		// update the Buffer

	}

	public void setGui(Gui gui) {
		this.gui = gui;

	}

	public void run() {

		gui.show();

	}

	public void send(String message) {

		socket.emit("chat message", message);
	}
	
	public Socket getSocket(){
		return socket;
	}

}
