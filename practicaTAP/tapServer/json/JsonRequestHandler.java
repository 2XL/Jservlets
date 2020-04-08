package json;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import server.IServerServer;
import xStream.XStreamGaeFactory;

import com.thoughtworks.xstream.XStream;

import config.RequestServer;
import config.RequestHandler;
import config.ResponseServer;

public class JsonRequestHandler implements RequestHandler {

	@Override
	public void attendRequest(InputStream in, OutputStream out, IServerServer server)
			throws Exception {
		XStream xStream = XStreamGaeFactory.getXStreamJson();

		// Catch the Json petition using DataInputStream (less ugly :D)
		DataInputStream dis = new DataInputStream(in);
		String jsonPetition = dis.readUTF();

		RequestServer request = (RequestServer) xStream.fromXML(jsonPetition);
		Object result = request.getAction().invoke(server, request.getArgs());
		ResponseServer response = new ResponseServer(result);
		xStream.toXML(response, out);
	}

}
