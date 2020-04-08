package client;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class PersonServer extends ClientServer {

	public PersonServer() {
		super();
	}

	public PersonServer(String id, String name) {
		super(id, name);
	}

	@Override
	public void setMessage(MessageServer m) {
		lastMessages.add(m);		
	}

}
