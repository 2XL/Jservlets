package cat.urv.tap.converter;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ConverterServlet extends HttpServlet {
	
	//data available along several client connections
	private static int i = 0;
	
	//accepted actions: GET and POST
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		processRequest(req, resp);		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws IOException {
		processRequest(req, resp);
	}
	
	//well... GET or POST... it doesn't matter, always the same ;-)
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
		throws IOException {
		
		//example of parameter passed to the server
		String value = req.getParameter("key");
		
		//getting access to the content sent from the client
		Reader in = req.getReader(); //don't use BufferedReader!!!
		Writer out = resp.getWriter(); //don't use BufferedWriter!!!

		//reading request content
		char[] buffer = new char[256];
		StringBuffer incoming = new StringBuffer();
		int read = 0;
		while ((read=in.read(buffer))>0) {
			incoming.append(buffer, 0, read); 
		}
		String incomingMessage = incoming.toString();
		
		//doing some specific stuff on server side
		i++;
		
		//preparing response for the client using some server results (i)
		String outgoingMessage = 
				"key=" + value + 
				"; client-message: [" +incomingMessage + 
				"] (" + i + " from Cloud)";
		
		//sending answer
		out.write(outgoingMessage);
		out.flush();
	}
}
 