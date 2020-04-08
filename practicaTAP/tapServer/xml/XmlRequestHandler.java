package xml;

import java.io.InputStream;
import java.io.OutputStream;

import server.IServerServer;
import xStream.XStreamGaeFactory;

import com.thoughtworks.xstream.XStream;

import config.RequestServer;
import config.RequestHandler;
import config.ResponseServer;

public class XmlRequestHandler implements RequestHandler {

	public XmlRequestHandler() {
	}

	@Override
	public void attendRequest(InputStream in, OutputStream out, IServerServer server)
			throws Exception {
		
		XStream xStream = XStreamGaeFactory.getXStream();
		RequestServer request = (RequestServer)xStream.fromXML(in);
		Object result = request.getAction().invoke(server, request.getArgs());
		ResponseServer response = new ResponseServer(result);
		xStream.toXML(response, out);
		
	}
	
//	@Override
//	public void attendRequest(Reader in, Writer out, IServer server)
//			throws Exception {
//		 String message = "No va";
//		 try{
//		 XStream xStream = new XStreamGae(new StaxDriver());
//		 xStream.alias("Message", Message.class);
//		 xStream.alias("Client", Client.class);
//		 xStream.alias("Person", Person.class);
//		 Message m = (Message)xStream.fromXML(in);
//		 message = m.getMessage();
//		 message += "\n ++Source: "+m.getSource().getName();
//		 message += "\n ++Dest: "+m.getDest().getName();
//		 xStream.toXML(m, out);
//		 }catch(Exception e){
//		 message = e.getMessage();
//		 message += "\n -----"+e.toString() + "\n ------";
//		 }
//	}

}