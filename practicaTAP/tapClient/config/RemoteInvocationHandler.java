package config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public abstract class RemoteInvocationHandler implements InvocationHandler {
	private String url = null;

	public abstract Response makePetition(Request req, String url)
			throws Exception;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		url = Config.getConfig().getUrl();

		// Make the petition to the server
		Request req = new Request(method.getName(), args);
		Response resp = makePetition(req, url);

		return resp.getValue();
	}
}