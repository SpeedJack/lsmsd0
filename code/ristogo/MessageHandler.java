package ristogo;
/*@TODO
 * Aggiungere condizione per request/response
 * Aggiungere gestione risposte
* 
*/

import java.net.*;
import java.time.LocalDate;

import com.thoughtworks.xstream.XStream;

import java.io.*;

import java.util.*;

class MessageHandler {
	
	private static Map<String,Integer> restIdMap = new HashMap<String, Integer>(); //Map Used to save the RestId using as key the name
	private static List<Reservation> rsvList = new ArrayList<>();
	public static final String REQ = "req";
	public static final String RES = "res";
	private static String xml;
	
	/*----------------------------------------------------------------------------------------------------------------------------
	 * REQUEST/RESPONSE CODES
	 -----------------------------------------------------------------------------------------------------------------------------*/
	
	private static final int LOGIN = 1;
	private static final int REGISTRATION = 2;
	private static final int RESERVATION = 3;
	private static final int LIST_RESTAURANT = 4;
	private static final int LIST_RESERVATION = 5;
	private static final int LIST_RESERVATION_REST = 6;
	private static final int MODIFY_RESTAURANT = 7;
	private static final int DELETE_RESERVATION = 8;
	private static final int CHECK_SEATS = 9;
	
	/*----------------------------------------------------------------------------------------------------------------------------
	 * COMMON UTILITIES
	 -----------------------------------------------------------------------------------------------------------------------------*/

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
	
	/*----------------------------------------------------------------------------------------------------------------------------
	 * CLIENT SIDE UTILITIES
	 -----------------------------------------------------------------------------------------------------------------------------*/


	public static Object sendRequest(int type, String ... strings){
		/**
		 * This function is used by the User interface to send a request to
		 * the server, the function returns an Object that in order to be used
		 * must be casted from the user interface to the desired Class
		 */
		
		Request r = null; 
		Response res = null;
		switch(type) {
		case LOGIN: {
			r = prepareLogin(strings[0], strings[1] );
			if(r == null ) return null;
			send(r, REQ);
			res = (Response) receive();
			if (res == null) return null;
			if(res.isSuccess()) {
				
				res.getUser().setUsername(strings[0]);
				res.getUser().setPassword(strings[1]);
				return res.getUser();
				
			};
			break;
		}
		case REGISTRATION: {
			r = prepareRegistration(strings[0], strings[1], Boolean.parseBoolean(strings[2]));
			if(r == null ) return false;
			send(r, REQ);
			res = (Response) receive();
			if (res == null) return false;
			if(res.isSuccess()) {
				return true;
			};
			break;
		}
		case LIST_RESTAURANT: {
			r = prepareList();
			if (r==null) return null;
			send(r, REQ);
			res = (Response) receive();
			if (res == null) return null;
			if(res.isSuccess()) {
				for(Restaurant rr:res.getRestaurants()) {
					restIdMap.put(rr.getName(), rr.getIdRestaurant());
				}
				return res.getRestaurants();
			};
			break;
		} case RESERVATION: {
			int rid = restIdMap.get(strings[1]);
		//	r = prepareReservation(Integer.parseInt(strings[0]), rid, strings[2], strings[3], 
		//			Integer.parseInt(strings[4]));
			if(r == null ) return false;
			send(r, REQ);
			res = (Response) receive();
			if (res == null) return false;
			if(res.isSuccess()) {
				return true;
			};
			break;
		} case LIST_RESERVATION: {
			rsvList.clear();
			r = prepareListReservation(Integer.parseInt(strings[0]));
			if(r == null ) return null;
			send(r, REQ);
			res = (Response) receive();
			if (res == null) return null;
			if(res.isSuccess()) {
				rsvList.addAll(res.getReservations());
				return res.getReservations();
			};
		} case LIST_RESERVATION_REST: {
			r = prepareListReservationRest(Integer.parseInt(strings[0]));
			if(r == null ) return null;
			send(r, REQ);
			res = (Response) receive();
			if (res == null) return null;
			if(res.isSuccess()) {
				return res.getReservations();
			};
		} case DELETE_RESERVATION: {
			int id;
			
			for(Reservation rr : rsvList) {
		//		if(rr.getUser() == Integer.parseInt(strings[0]) && 
	//				rr.get
			}
		//	r = prepareDeleteReservation(Integer.parseInt(strings[0]), strings[1], strings[2], strings[3], 
		//			Integer.parseInt(strings[4]));
			if(r == null ) return false;
			send(r, REQ);
			res = (Response) receive();
			if (res == null) return false;
			if(res.isSuccess()) {
				return true;
			};
			break;
		}
		
		default: break;
		}
	return null;
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
	
	
	public static Request prepareReservation(int uid, String n, String d, String h, int s) {
		
		Reservation r = new Reservation(0, uid, 0, d.toString(), h, s );
		Restaurant rst = new Restaurant(0, 0, n, null, 0, null, null, null, 0, null);
		
		return new Request(RESERVATION, null, rst, r);
		
	}
	
	public static Request prepareListReservation(int uid) {
		
		User u = new User(uid, null ,null);
		return new Request(LIST_RESERVATION, u, null ,null);
	}
	
	public static Request prepareListReservationRest(int uid) {
		User u = new User(uid, null ,null);
		return new Request(LIST_RESERVATION_REST, u, null ,null);
	};
	
	public static Request prepareDeleteReservation(int rid) {
		Reservation r = new Reservation(rid, 0, 0, null, null, 0);
		return new Request(DELETE_RESERVATION, null, null, r);
	}
	
	public static Request prepareModifyRestaurant(int idR, int idU, String n, String t, int p, String c, String a, String d, int s, String oa ) {
		Restaurant r = new Restaurant(idR,idU,n,t,p,c,a,d,s,oa);
		return new Request(MODIFY_RESTAURANT, null, r, null);
	};
	
	public static Request prepareCheckSeats(int rid, LocalDate d, String oa) {
		Reservation r = new Reservation(0, 0, rid, d.toString(), oa, 0 );
		return new Request(CHECK_SEATS, null, null, r);
	};	
	
/*----------------------------------------------------------------------------------------------------------------------------
 * SERVER SIDE UTILITIES
 -----------------------------------------------------------------------------------------------------------------------------*/
	
};



