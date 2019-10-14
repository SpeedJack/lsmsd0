

import java.net.*;

import com.thoughtworks.xstream.XStream;

import java.io.*;

import java.util.*;

class RequestHandlerClient {
	
	public static void sendRequest(Request r) {
		XStream xs = new XStream();
		String xml = xs.toXML(r);
	    try ( DataOutputStream dout = new DataOutputStream( (new Socket("localhost",8080) ).getOutputStream())
	    ) { dout.writeUTF(xml);} catch (Exception e) {e.printStackTrace();}
		return;
	};
	
	public static Request receiveRequest() {
		try ( ServerSocket servs = new ServerSocket(8080);
		          Socket sd = servs.accept(); 
		          DataInputStream din = new DataInputStream(sd.getInputStream()) //1
		        ) { 
		          XStream xs = new XStream();
		          String xml = din.readUTF(); 
		          Request r = (Request)xs.fromXML(xml);
		          return r;
		        } catch (Exception e) {
		        	e.printStackTrace();
		        	return null;
		        }
};
};


