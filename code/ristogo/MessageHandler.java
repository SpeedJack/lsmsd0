package ristogo;


import java.net.*;
import java.time.LocalDate;

import com.thoughtworks.xstream.XStream;

import java.io.*;

import java.util.*;

class RequestHandler {
	public static String REQ = "req";
	public static String RES = "res";
	private static String xml;
	private static final int LOGIN = 1;
	private static final int REGISTRATION = 2;
	private static final int BOOK = 3;
	private static final int LIST_RESTAURANT = 4;
	private static final int LIST_RESERVATION = 5;
	private static final int LIST_RESERVATION_REST = 6;
	private static final int MODIFY_RESTAURANT = 7;
	private static final int MODIFY_RESERVATION = 8;
	private static final int DELETE_RESERVATION = 9;
	private static final int DELETE_RESTAURANT = 10;
	private static final int CHECK_SEATS = 10;
	
	
	public static void send(Object r, String type) { 
		XStream xs = new XStream();
		
		if(type.equals(REQ)) {
			xml = xs.toXML((Request)r);
		} else if (type.equals(RES)) {
			xml = xs.toXML((Request)r);
		}
	    try ( DataOutputStream dout = new DataOutputStream( (new Socket("localhost",8080) ).getOutputStream())
	    ) { dout.writeUTF(xml);} catch (Exception e) {e.printStackTrace();}
		return;
	};
	
	public static Object receive() {
		try ( ServerSocket servs = new ServerSocket(8080);
		          Socket sd = servs.accept(); 
		          DataInputStream din = new DataInputStream(sd.getInputStream()) //1
		        ) { 
			XStream xs = new XStream();
			String xml = din.readUTF(); 
			return xs.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String sendRequest(int type, String ... strings){
		Request r; 
		switch(type) {
		case LOGIN: {
			r = prepareLogin(strings[0], strings[1] );
			break;
		}
		case REGISTRATION: {
			r = prepareRegistration(strings[0], strings[1], Boolean.parseBoolean(strings[2]));
		}
		}
		
	}
	
	public static Request prepareLogin(String username, String password) {
				
			User u = new User(0, username, password);
			//Create Request
			return new Request(LOGIN, u, null, null);
	}
	
	public static Request prepareRegistration(String username, String password, boolean restaurateur) {
		
		//Create User Bean to insert in Request
		User u = new User(0, username, password);
		Restaurant r = new Restaurant(0, u.getUserId(), "", "", 0, "", "", "", 0, "");    
		//Create Request
		return new Request(REGISTRATION, u, r, null);
	};
	
	public static Request prepareList() {
		return new Request(LIST_RESTAURANT, null, null, null);
	};
	
	
	public static Request prepareBook(int uid, String n, LocalDate d, String h, int s) {
		
		Reservation r = new Reservation(0, uid, 0, d.toString(), h, s );
		Restaurant rst = new Restaurant(0, 0, n, null, 0, null, null, null, 0, null);
		
		return new Request(BOOK, null, rst, r);
		
	}
	
	public static Request prepareListReservation(int uid) {
		
	}
	
	/*Da fare
	
	* LISTA PRENOTAZIONI
	*
	* LISTA PRENOTAZIONI RISTORATORE
	*
	* MODIFICA_CANCELLA_PRENOTAZIONE
	* 
	* MODIFICA_RISTORANTE
	* 
	* CHECK_PRENOTAZIONE
	*/

	

	
	
};



