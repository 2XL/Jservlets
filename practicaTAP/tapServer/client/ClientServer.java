package client;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class ClientServer {
	
	@PrimaryKey
	@Persistent
	private String id;
	
	@Persistent
	private String name;
	
	@Persistent
	protected List<MessageServer> lastMessages = new LinkedList<MessageServer>();

	public ClientServer() {
		this.id = null;
		this.name = null;
	}

	public ClientServer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public abstract void setMessage(MessageServer m);

	// public String getFirstMessage(){
	// return messages.get(0);
	// }

	public String getId() {
		return id;
	}

	public List<MessageServer> getMessages() {
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
		if (obj instanceof ClientServer) {
			return this.id.equals(((ClientServer) obj).getId());
		}
		return false;
	}

	// This method returns the last clients' messages
	public List<MessageServer> getLastMessages() {
		List<MessageServer> last = lastMessages;
		lastMessages = new LinkedList<MessageServer>();
		return last;
	}
}