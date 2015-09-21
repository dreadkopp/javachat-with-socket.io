package javachat;

public class Buffer {
	
	String who ="";
	String message ="";
	
	
	public Buffer (String who, String message) {
		this.who = who;
		this.message = message;
	}
	
	
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
