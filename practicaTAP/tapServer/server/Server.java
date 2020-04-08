package server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import client.ClientServer;
import client.MessageServer;
import client.PersonServer;


public class Server implements IServerServer, Iterable<ClientServer> {
	public Map<String, ClientServer> clients;	
	
	
	public Server() {
		this.clients = new HashMap<String, ClientServer>();
		addClient(new PersonServer("35235", "LL"));
		addClient(new PersonServer("152", "LoL"));
	}
	
	public Server(Map<String, ClientServer> clients) {
		this.clients = clients;
	}
	
	@Override
	public String addClient(ClientServer c) {
		if(clients.containsKey(c.getId()))
			return "ERROR";
		clients.put(c.getId(), c);
		return "OK";
	}

	@Override
	public String removeClient(ClientServer c) {
		clients.remove(c.getId());
		if(clients.containsKey(c.getId())){
			return "ERROR";
		}
		return "OK";
	}

	public Map<String, ClientServer> getClients() {
		return clients;
	}

	public void setClients(Map<String, ClientServer> clients) {
		this.clients = clients;
	}

	@Override
	public String setMessage(MessageServer m) {
		ClientServer c = clients.get(m.getDest().getId());
		c.setMessage(m);
		return "OK";
	}

	@Override
	public List<MessageServer> getListMessages(PersonServer p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<ClientServer> iterator() {
		return clients.values().iterator();
	}

	@Override
	public List<MessageServer> getLastMessages(PersonServer c) {
		return clients.get(c.getId()).getLastMessages();
	}
}
