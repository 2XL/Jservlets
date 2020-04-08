package xStream;

import server.IServerServer;
import client.ClientServer;
import client.GroupServer;
import client.MessageServer;
import client.PersonServer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import config.RequestServer;
import config.ResponseServer;

public class XStreamGaeFactory {

	public static XStream getXStream(){
		XStream xStream = new XStreamGae(new StaxDriver());
		xStream.alias("IServer", IServerServer.class);
		xStream.alias("Message", MessageServer.class);
		xStream.alias("Client", ClientServer.class);
		xStream.alias("Person", PersonServer.class);
		xStream.alias("Group", GroupServer.class);
		xStream.alias("Request", RequestServer.class);
		xStream.alias("Response", ResponseServer.class);
		return xStream;
	}
	
	public static XStream getXStreamJson(){
		XStream xStream = new XStreamGae(new JettisonMappedXmlDriver());
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.alias("IServer", IServerServer.class);
		xStream.alias("Message", MessageServer.class);
		xStream.alias("Client", ClientServer.class);
		xStream.alias("Person", PersonServer.class);
		xStream.alias("Group", GroupServer.class);
		xStream.alias("Request", RequestServer.class);
		xStream.alias("Response", ResponseServer.class);
		return xStream;
	}
}
