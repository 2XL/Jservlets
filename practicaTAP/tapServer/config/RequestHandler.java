package config;

import java.io.InputStream;
import java.io.OutputStream;

import server.IServerServer;

public interface RequestHandler {
	public void attendRequest(InputStream in, OutputStream out, IServerServer server)
			throws Exception;
}
