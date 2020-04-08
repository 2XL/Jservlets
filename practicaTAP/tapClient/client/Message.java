package client;


public class Message {
	private Client source;
	private Client dest;
	private String message;
	
	public Message() {
		this.source = null;
		this.dest = null;
		this.message = null;
	}

	public Message(Client source, Client dest, String message) {
		this.source = source;
		this.dest = dest;
		this.message = message;
	}

	public Client getSource() {
		return source;
	}

	public void setSource(Client source) {
		this.source = source;
	}

	public Client getDest() {
		return dest;
	}

	public void setDest(Client dest) {
		this.dest = dest;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
