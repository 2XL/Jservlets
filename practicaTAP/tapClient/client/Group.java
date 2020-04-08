package client;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class Group extends Client implements Iterable<Person>{
	private List<Person> members;
	
	public Group() {
		members = new LinkedList<Person>();
	}

	public Group(String id, String name) {
		super(id, name);
		members = new LinkedList<Person>();
	}
	
	public void addMember(Person p ){
		members.add(p);
	}
	
	public void removeMember(Person p){
		members.remove(p);
	}

	@Override
	public Iterator<Person> iterator() {
		return members.iterator();
	}

	@Override
	public void setMessage(Message m) {
		for(Person p : this){
			p.setMessage(m); //No he pensat el cas en el qual t'envies un missatge a tu mateix
		}
	}
	


}
