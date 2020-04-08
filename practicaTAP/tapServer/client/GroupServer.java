package client;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
public class GroupServer extends ClientServer implements Iterable<PersonServer>{
	
	@Persistent
	private List<PersonServer> members;
	
	public GroupServer() {
		members = new LinkedList<PersonServer>();
	}

	public GroupServer(String id, String name) {
		super(id, name);
		members = new LinkedList<PersonServer>();
	}
	
	public void addMember(PersonServer p ){
		members.add(p);
	}
	
	public void removeMember(PersonServer p){
		members.remove(p);
	}

	@Override
	public Iterator<PersonServer> iterator() {
		return members.iterator();
	}

	@Override
	public void setMessage(MessageServer m) {
		for(PersonServer p : this){
			p.setMessage(m); //No he pensat el cas en el qual t'envies un missatge a tu mateix
		}
	}
	


}
