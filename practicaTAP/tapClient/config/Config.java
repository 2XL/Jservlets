package config;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {

	private static Config instance = null;

	private Properties config = null;

	private String protocol, serverside, url;

	private ServerFactory serverFactory;

	protected Config() throws Exception {

		// Load Properties
		config = new Properties();
		config.load(new FileInputStream("conf/app.properties"));

		// Choose the protocol: either xml or json
		protocol = config.getProperty("comm.protocol", "xml");
		protocol = protocol.substring(0, 1).toUpperCase()
				.concat(protocol.substring(1).toLowerCase());

		// Get information about serverside and which url we'll use
		serverside = config.getProperty("server.type", "local").trim();
		url = config.getProperty("server.host." + serverside);

		// Create a new serverFactory using the protocol choosed in the step
		// before
		String prefix = protocol.toLowerCase() + ".".concat(protocol);
		String classname = prefix.concat("ServerFactory");
		serverFactory = (ServerFactory) Class.forName(classname).newInstance();
	}

	public static Config getConfig() throws Exception {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	public IServer getServer() {
		return serverFactory.getServer();
	}

	public String getProtocol() {
		return protocol;
	}

	public String getServerside() {
		return serverside;
	}

	public String getUrl() {
		return url;
	}

}
