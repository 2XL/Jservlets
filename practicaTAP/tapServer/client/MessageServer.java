package client;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@PersistenceCapable
public class MessageServer {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private ClientServer source;
	
	@Persistent
	private ClientServer dest;
	
	@Persistent
	private String message;
	
	public MessageServer() {
		this.source = null;
		this.dest = null;
		this.message = null;
	}

	public MessageServer(ClientServer source, ClientServer dest, String message) {
		this.source = source;
		this.dest = dest;
		this.message = message;
	}

	public ClientServer getSource() {
		return source;
	}

	public void setSource(ClientServer source) {
		this.source = source;
	}

	public ClientServer getDest() {
		return dest;
	}

	public void setDest(ClientServer dest) {
		this.dest = dest;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Key getKey() {
		return key;
	}

}
