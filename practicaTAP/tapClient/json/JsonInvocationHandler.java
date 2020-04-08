package json;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.thoughtworks.xstream.XStream;

import config.RemoteInvocationHandler;
import config.Request;
import config.Response;
import config.XStreamFactory;

public class JsonInvocationHandler extends RemoteInvocationHandler {

	/**
	 * {@inheritDoc}
	 * 
	 * @throws Exception
	 * @see cat.urv.tap.converter.remote.RemoteInvocationHandler#makePetition(cat.urv.tap.converter.logger.remote.Request,
	 *      java.io.InputStream, java.io.OutputStream)
	 */
	@Override
	public Response makePetition(Request req, String url) throws Exception {
		XStream xStream = XStreamFactory.getXStreamJson();

		// Set the protocol which we'll use
		url = url + "?protocol=Json";

		// Opening connection to server
		HttpURLConnection server = (HttpURLConnection) new URL(url)
				.openConnection();

		// Connection Settings (Via POST)
		server.setRequestMethod("POST");
		server.setDoInput(true);
		server.setDoOutput(true);
		server.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		server.setRequestProperty("Content-Language", "en-US");

		// Send the json petition to the server
		DataOutputStream dos = new DataOutputStream(server.getOutputStream());
		String jsonPetition = xStream.toXML(req);
		dos.writeUTF(jsonPetition);
		dos.flush();

		// Receive the response from server
		Response response = (Response) xStream.fromXML(server.getInputStream());

		// Disconnecting
		server.disconnect();

		return response;
	}

}
