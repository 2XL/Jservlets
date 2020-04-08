package xml;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import config.IServer;
import config.ServerFactory;

public class XmlServerFactory implements ServerFactory{

	@Override
	public IServer getServer() {
		//Build remote handler
		InvocationHandler handler = new XmlInvocationHandler();
		
		//Create the proxiedServer using the Java Reflection object Proxy
		IServer proxiedServer = (IServer) Proxy.newProxyInstance(IServer.class.getClassLoader(), new Class [] { IServer.class}, handler);
		
		return proxiedServer;
	}


}
