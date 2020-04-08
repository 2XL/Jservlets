package config;

import json.JsonRequestHandler;

public class RequestHandlerFactory {

	private static RequestHandlerFactory instance = null;

	protected RequestHandlerFactory() {
	}

	public static RequestHandlerFactory getRequestHandlerFactory() {
		if (instance == null) {
			instance = new RequestHandlerFactory();
		}
		return instance;
	}

	public RequestHandler getRequestHandler(String protocol) {
		String classname = protocol.toLowerCase()+"."+protocol + "RequestHandler";
		RequestHandler protocolHandler;
		try {
			protocolHandler = (RequestHandler) Class.forName(classname)
					.newInstance();
		} catch (Exception e) {
			protocolHandler = new JsonRequestHandler();
		}
		RequestHandler communicationHandler = new RemoteRequestHandler(
				protocolHandler);
		return communicationHandler;
	}
}
