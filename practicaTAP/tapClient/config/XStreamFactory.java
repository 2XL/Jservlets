package config;

import client.Client;
import client.Group;
import client.Message;
import client.Person;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XStreamFactory {

	public static XStream getXStream(){
		XStream xStream = new XStream(new StaxDriver());
		xStream.alias("IServer", IServer.class);
		xStream.alias("Message", Message.class);
		xStream.alias("Client", Client.class);
		xStream.alias("Person", Person.class);
		xStream.alias("Group", Group.class);
		xStream.alias("Request", Request.class);
		xStream.alias("Response", Response.class);
		return xStream;
	}
	
	public static XStream getXStreamJson(){
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		xStream.setMode(XStream.NO_REFERENCES);
		xStream.alias("IServer", IServer.class);
		xStream.alias("Message", Message.class);
		xStream.alias("Client", Client.class);
		xStream.alias("Person", Person.class);
		xStream.alias("Group", Group.class);
		xStream.alias("Request", Request.class);
		xStream.alias("Response", Response.class);
		return xStream;
	}
}
