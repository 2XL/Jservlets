package config;

import java.io.InputStream;
import java.io.OutputStream;

import server.IServerServer;

public class RemoteRequestHandler implements RequestHandler {
	
	private RequestHandler requestHandler;
	
	public RemoteRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

	@Override
	public void attendRequest(InputStream in, OutputStream out, IServerServer server)
			throws Exception {
		requestHandler.attendRequest(in, out, server);		
	}

}
