package cat.urv.tap.converter.client;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class HttpClientMain {

	public static void main(String[] args) throws Exception {
		//initialization
		Properties config = new Properties();
		config.load(new FileInputStream("conf/app.properties"));
		String serverside = config.getProperty("server.type", "local").trim();
		
		//message to send
		String message = "hello world!";
		String messageLength = String.valueOf(message.getBytes("UTF-8").length);
		//url service
		String url = config.getProperty("server.host."+serverside);
		//adding parameters: pattern: ?key1=valu1&key2=valu2&key3=valu3
		url = url+"?key=value";
		
		//before starting, some stats
		System.out.println("message: "+message);
		System.out.println("messageLength: "+messageLength);
		System.out.println("serverside: "+serverside);
		System.out.println("host prop: "+"server.host."+serverside);
		System.out.println("url: "+url);
		
		//opening connection to server
		HttpURLConnection server = (HttpURLConnection)new URL(url).openConnection();
		
		//settings connection
		server.setRequestMethod("POST"); //POST or GET reacts similarly
		//server will use always POST as invocation method
		//GET method is dedicated to web visiting
		server.setDoInput(true);
		server.setDoOutput(true);
		server.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		server.setRequestProperty("Content-Length", messageLength);
		server.setRequestProperty("Content-Language", "en-US");
		
		//getting access to write something to server
		OutputStream out = server.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
		
		writer.write("message="+message);
		writer.flush();
		
		//what is the result?
		System.out.println("response code:"+server.getResponseCode()+" "+server.getResponseMessage());
		
		//getting access to read the result from the server
		InputStream in = server.getInputStream();
		Reader reader = new InputStreamReader(in, "UTF-8");
		
		//reading all the content from server
		char[] buffer = new char[256];
		int read=0;
		StringBuffer response = new StringBuffer();
		while ((read=reader.read(buffer))>0) {
			response.append(buffer, 0, read);
		}
		
		//showing the server response
		System.out.println("server response:"+response.toString());
		
		//bye bye!
		server.disconnect();
	}

}
