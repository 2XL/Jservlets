package cat.urv.tap.server;

import java.io.IOException;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.ClientServer;
import client.MessageServer;
import client.PersonServer;

import server.IServerServer;
import server.Server;
import config.RequestHandler;
import config.RequestHandlerFactory;


@SuppressWarnings("serial")
public class TapServerServlet extends HttpServlet {

	private RequestHandlerFactory factory;
	private RequestHandler handler;
	private IServerServer server;

	public void init() {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			factory = RequestHandlerFactory.getRequestHandlerFactory();
			server = new Server();
			//String query = "select from "+PersonServer.class.getName();
			//server.setClients((Map<String, ClientServer>)pm.newQuery(query).execute());
			//String query2 = "select from "+MessageServer.class.getName();
			//server.setClients((Map<String, ClientServer>)pm.newQuery(query2).execute());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            pm.close();
        }
		System.out.println("asdf");
		for (ClientServer c : server.getClients().values())
			System.out.println(c.getId()+"   "+c.getName());
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			String protocol = req.getParameter("protocol");
			handler = factory.getRequestHandler(protocol);
			handler.attendRequest(req.getInputStream(), resp.getOutputStream(), server);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void destroy(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistentAll(server.getClients());
			
        } finally {
            pm.close();
        }
	}
}
