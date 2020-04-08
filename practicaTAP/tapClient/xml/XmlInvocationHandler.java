package xml;

import java.net.HttpURLConnection;
import java.net.URL;

import com.thoughtworks.xstream.XStream;

import config.RemoteInvocationHandler;
import config.Request;
import config.Response;
import config.XStreamFactory;

public class XmlInvocationHandler extends RemoteInvocationHandler {
	private Response response;

	@Override
	public Response makePetition(Request req, String url) throws Exception {
		XStream xStream = XStreamFactory.getXStream();

		// Set the protocol which we'll use
		url = url + "?protocol=Xml";

		// Opening connection to server
		HttpURLConnection server = (HttpURLConnection) new URL(url)
				.openConnection();

		// Connection Settings (Via POST)
		server.setRequestMethod("POST");
		server.setDoInput(true);
		server.setDoOutput(true);
		server.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		server.setRequestProperty("Content-Language", "en-US");

		// Send the request and recieve the response
		xStream.toXML(req, server.getOutputStream());
		response = (Response) xStream.fromXML(server.getInputStream());

		// Disconnecting
		server.disconnect();

		return response;
	}
}
