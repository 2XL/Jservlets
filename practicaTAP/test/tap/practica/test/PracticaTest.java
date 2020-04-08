package tap.practica.test;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tap.practica.*;
import cat.urv.tap.*;
import cat.urv.tap.server.TapServerServlet;
import client.*;
import config.*;
import server.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class PracticaTest {
	

	@BeforeClass
	public static void initOnce() {
		//todo
	}
	
	@AfterClass
	public static void finalizeOnce() {
		//todo
	}
	
	@Before
	public void init() {
		
		
	}
	
	@After
	public void finalization() {
		//do whatever
	}	
	
	
	@Test
	public void testAddClientServer() {
	
	System.out.println("Test addClient del Servidor");
	System.out.println("\n");
	
	ClientServer goku = new PersonServer("1234", "Goku");
	ClientServer krilin = new PersonServer("1235", "Krilin");
	Server server = new Server();
		
	try {		
		Map<String, ClientServer> clients=server.getClients();
		
		for (ClientServer c : clients.values())
			System.out.println(c.getId()+"   "+c.getName());
		System.out.println("\n");
		
		server.addClient(goku);		
		server.addClient(krilin);
		
		for (ClientServer c : clients.values())
			System.out.println(c.getId()+"   "+c.getName());
		System.out.println("\n");
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testRemoveClientServer() {
		
	System.out.println("Test removeClient del Servidor");
	System.out.println("\n");
	
	ClientServer goku = new PersonServer("1234", "Goku");
	ClientServer krilin = new PersonServer("1235", "Krilin");
	Server server = new Server();
		
	try {		
		server.addClient(goku);		
		server.addClient(krilin);
		Map<String, ClientServer> clients=server.getClients();
		
		for (ClientServer c : clients.values())
			System.out.println(c.getId()+"   "+c.getName());
		System.out.println("\n");
		
		server.removeClient(goku);
		
		for (ClientServer c : clients.values())
			System.out.println(c.getId()+"   "+c.getName());
		System.out.println("\n");
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testSetClientServer() {
	
	System.out.println("Test setClient del Servidor");
	System.out.println("Comprovar que cambia toda la lista de clientes");
	System.out.println("\n");
	
	ClientServer goku = new PersonServer("1234", "Goku");
	ClientServer krilin = new PersonServer("1235", "Krilin");
	Server server = new Server();
		
	try {		
		
		Map<String, ClientServer> clients = new HashMap<String, ClientServer>();
		clients.put(goku.getId(), goku);		
		clients.put(krilin.getId(), krilin);

		for (ClientServer c : server.getClients().values())
			System.out.println(c.getId()+"   "+c.getName());
		System.out.println("\n");
		
		server.setClients(clients);
		
		for (ClientServer c : server.getClients().values())
			System.out.println(c.getId()+"   "+c.getName());
		System.out.println("\n");
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testLastMessagesClient() {
		
	System.out.println("Test lastMessagesClient del Cliente");
	System.out.println("Comprovar que devuelve los mensajes no leidos del cliente seleccionado del Cliente");
	System.out.println("\n");
	
	Client goku = new Person("1234", "Goku");
	Client krilin = new Person("2345", "Krilin");
	List<Message> messages = new ArrayList<Message>();
	
	try {
		Config config = Config.getConfig();

		// initialitzation of proxy
		IServer proxiedServer = config.getServer();

		// Add Goku and Krilin to the server
		proxiedServer.addClient(goku);
		proxiedServer.addClient(krilin);

		// use proxy as if the real server was local
		Message m = new Message();
		m.setSource(krilin);
		m.setDest(goku);
		m.setMessage("Ieeeep!!! Ha arribat el SonGokuu!!!!");

		proxiedServer.setMessage(m);

		// receive the message
		messages = proxiedServer.getLastMessages((Person) goku);
		for (Message m1 : messages) {
			System.out.println("Send by: " + m1.getSource().getName());
			System.out.println("Received by: " + m1.getDest().getName());
			System.out.println(m1.getMessage());
			System.out.println("\n");
		}

		System.out.println("Fin de la lista de ultimos mensajes");
		System.out.println("\n");
		
		m.setMessage("Ieeeeep!!!! SonGoku que vas a buscar mongetes magiques?");
		proxiedServer.setMessage(m);

		// receive the message
		messages = proxiedServer.getLastMessages((Person) goku);
		for (Message m1 : messages) {
			System.out.println("Send by: " + m1.getSource().getName());
			System.out.println("Received by: " + m1.getDest().getName());
			System.out.println(m1.getMessage());
			System.out.println("\n");
		}

		System.out.println("Fin de la lista de ultimos mensajes");
		System.out.println("\n");
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	}
	
	@Test
	public void testLastMessagesServer() {
		
	System.out.println("Test lastMessagesClient del Servidor");
	System.out.println("Comprovar que devuelve los mensajes no leidos del cliente seleccionado del Servidor");
	System.out.println("\n");
	
	ClientServer goku = new PersonServer("1234", "Goku");
	ClientServer krilin = new PersonServer("2345", "Krilin");
	List<MessageServer> messages = new ArrayList<MessageServer>();
	Server server = new Server();
	
	try {
		
		// Add Goku and Krilin to the server
		server.addClient(goku);
		server.addClient(krilin);

		// use proxy as if the real server was local
		MessageServer m = new MessageServer();
		m.setSource(krilin);
		m.setDest(goku);
		m.setMessage("Ieeeep!!! Ha arribat el SonGokuu!!!!");

		server.setMessage(m);

		// receive the message
		messages = server.getLastMessages((PersonServer) goku);
		
		for (MessageServer m1 : messages) {
			System.out.println("Send by: " + m1.getSource().getName());
			System.out.println("Received by: " + m1.getDest().getName());
			System.out.println(m1.getMessage());
			System.out.println("\n");
		}
		
		System.out.println("Fin de la lista de ultimos mensajes");
		System.out.println("\n");
		
		m.setMessage("Ieeeeep!!!! SonGoku que vas a buscar mongetes magiques?");
		server.setMessage(m);

		// receive the message
		messages = server.getLastMessages((PersonServer) goku);
		
		for (MessageServer m1 : messages) {
			System.out.println("Send by: " + m1.getSource().getName());
			System.out.println("Received by: " + m1.getDest().getName());
			System.out.println(m1.getMessage());
			System.out.println("\n");
		}
		
		System.out.println("Fin de la lista de ultimos mensajes");
		System.out.println("\n");

	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	}
	
	
	@Test
	public void testLastMessagesClient2() {
		
	System.out.println("Test lastMessagesClient del Cliente");
	System.out.println("Comprovar que devuelve los mensajes no leidos del cliente seleccionado del Cliente, en este caso mas de uno");
	System.out.println("\n");	
		
	Client goku = new Person("1234", "Goku");
	Client krilin = new Person("2345", "Krilin");
	List<Message> messages = new ArrayList<Message>();
	
	try {
		Config config = Config.getConfig();

		// initialitzation of proxy
		IServer proxiedServer = config.getServer();

		// Add Goku and Krilin to the server
		proxiedServer.addClient(goku);
		proxiedServer.addClient(krilin);

		// use proxy as if the real server was local
		Message m = new Message();
		m.setSource(krilin);
		m.setDest(goku);
		m.setMessage("Ieeeep!!! Ha arribat el SonGokuu!!!!");

		proxiedServer.setMessage(m);

		m.setMessage("Ieeeeep!!!! SonGoku que vas a buscar mongetes magiques?");
		proxiedServer.setMessage(m);

		// receive the message
		messages = proxiedServer.getLastMessages((Person) goku);
		
		for (Message m1 : messages) {
			System.out.println("Send by: " + m1.getSource().getName());
			System.out.println("Received by: " + m1.getDest().getName());
			System.out.println(m1.getMessage());
			System.out.println("\n");
		}
		
		System.out.println("Fin de la lista de ultimos mensajes");
		System.out.println("\n");
		
		messages = proxiedServer.getLastMessages((Person) goku);
		
		for (Message m1 : messages) {
			System.out.println("Send by: " + m1.getSource().getName());
			System.out.println("Received by: " + m1.getDest().getName());
			System.out.println(m1.getMessage());
			System.out.println("\n");
		}
		
		System.out.println("Fin de la lista de ultimos mensajes");
		System.out.println("\n");
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	}
	
	
	@Test
	public void testLastMessagesServer2() {
		
	System.out.println("Test lastMessagesClient del Servidor");
	System.out.println("Comprovar que devuelve los mensajes no leidos del cliente seleccionado del Servidor, en este caso mas de uno");
	System.out.println("\n");
	
	ClientServer goku = new PersonServer("1234", "Goku");
	ClientServer krilin = new PersonServer("2345", "Krilin");
	List<MessageServer> messages = new ArrayList<MessageServer>();
	Server server = new Server();
	
	try {
		
		// Add Goku and Krilin to the server
		server.addClient(goku);
		server.addClient(krilin);

		// use proxy as if the real server was local
		MessageServer m = new MessageServer();
		m.setSource(krilin);
		m.setDest(goku);
		m.setMessage("Ieeeep!!! Ha arribat el SonGokuu!!!!");

		server.setMessage(m);
		
		m.setMessage("Ieeeeep!!!! SonGoku que vas a buscar mongetes magiques?");
		server.setMessage(m);

		// receive the message
		messages = server.getLastMessages((PersonServer) goku);
		
		for (MessageServer m1 : messages) {
			System.out.println("Send by: " + m1.getSource().getName());
			System.out.println("Received by: " + m1.getDest().getName());
			System.out.println(m1.getMessage());
			System.out.println("\n");
		}
		
		System.out.println("Fin de la lista de ultimos mensajes");
		System.out.println("\n");
		
		messages = server.getLastMessages((PersonServer) goku);
		
		for (MessageServer m1 : messages) {
			System.out.println("Send by: " + m1.getSource().getName());
			System.out.println("Received by: " + m1.getDest().getName());
			System.out.println(m1.getMessage());
			System.out.println("\n");
		}
		
		System.out.println("Fin de la lista de ultimos mensajes");
		System.out.println("\n");

	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	}
	
}

