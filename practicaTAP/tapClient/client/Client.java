package client;

import java.util.LinkedList;
import java.util.List;

public abstract class Client {
	private String id;
	private String name;
	protected List<Message> lastMessages = new LinkedList<Message>();

	public Client() {
		this.id = null;
		this.name = null;
	}

	public Client(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public abstract void setMessage(Message m);

	// public String getFirstMessage(){
	// return messages.get(0);
	// }

	public String getId() {
		return id;
	}

	public List<Message> getMessages() {
		return lastMessages;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Client) {
			return this.id.equals(((Client) obj).getId());
		}
		return false;
	}

	public List<Message> getLastMessages() {
		return lastMessages;
	}
}