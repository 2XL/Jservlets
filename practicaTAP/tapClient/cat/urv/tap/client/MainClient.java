package cat.urv.tap.client;

import java.util.List;

import client.Client;
import client.Message;
import client.Person;
import config.Config;
import config.IServer;

public class MainClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Joc de proves crear 2 clients: Goku Krilin
		Client goku = new Person("1234", "Goku");
		Client krilin = new Person("2345", "Krilin");
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
			List<Message> messages = proxiedServer
					.getLastMessages((Person) goku);
			for (Message m1 : messages) {
				System.out.println("Send by: " + m1.getSource().getName());
				System.out.println("Received by: " + m1.getDest().getName());
				System.out.println(m1.getMessage());
				System.out.println("\n\n");
			}

			m.setMessage("Ieeeeep!!!! SonGoku que vas a buscar mongetes magiques?");
			proxiedServer.setMessage(m);

			// receive the message
			messages = proxiedServer.getLastMessages((Person) goku);
			for (Message m1 : messages) {
				System.out.println("Send by: " + m1.getSource().getName());
				System.out.println("Received by: " + m1.getDest().getName());
				System.out.println(m1.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
