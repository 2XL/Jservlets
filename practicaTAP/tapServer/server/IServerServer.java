package server;

import java.util.List;
import java.util.Map;

import client.ClientServer;
import client.MessageServer;
import client.PersonServer;

public interface IServerServer {

	public String addClient(ClientServer c);

	public String removeClient(ClientServer c);

	public String setMessage(MessageServer m);

	public List<MessageServer> getLastMessages(PersonServer p);

	public List<MessageServer> getListMessages(PersonServer p);
	
	public Map<String, ClientServer> getClients();
	
	public void setClients(Map<String, ClientServer> clients);

}
