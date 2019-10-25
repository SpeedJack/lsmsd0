package ristogo;
/*@TODO
 * Aggiungere condizione per request/response
 * Aggiungere gestione risposte
* 
*/

import java.net.*;
import java.time.LocalDate;
import java.lang.String;

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
	
	public static final int LOGIN = 1;
	public static final int REGISTRATION = 2;
	public static final int RESERVATION = 3;
	public static final int LIST_RESTAURANT = 4;
	public static final int LIST_RESERVATION = 5;
	public static final int LIST_RESERVATION_REST = 6;
	public static final int MODIFY_RESTAURANT = 7;
	public static final int DELETE_RESERVATION = 8;
	public static final int CHECK_SEATS = 9;
	
	/*----------------------------------------------------------------------------------------------------------------------------
	 * COMMON UTILITIES
	 -----------------------------------------------------------------------------------------------------------------------------*/

	public static void send(Object r, String type, Socket s) { 
		XStream xs = new XStream();
		
		if(type.equals(REQ)) {
			xml = xs.toXML((Request)r);
		} else if (type.equals(RES)) {
			xml = xs.toXML((Request)r);
		}
	    try ( DataOutputStream dout = new DataOutputStream(s.getOutputStream());
	    ) { dout.writeUTF(xml);} catch (Exception e) {e.printStackTrace();}
		return;
	};
	
	public static Object receive(Socket s) {
		try (DataInputStream din = new DataInputStream(s.getInputStream())) { 
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
		/** ADATTARE I RITORNI
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
			send(r, REQ, UserSession.getSocket());
			res = (Response) receive(UserSession.getSocket());
			if (res == null) return null;
			if(res.isSuccess()) {
				
				res.getUser().setUsername(strings[0]);
				res.getUser().setPassword(strings[1]);
				return res.getUser().getBean();
				
			};
			break;
		}
		case REGISTRATION: {
			r = prepareRegistration(strings[0], strings[1], Boolean.parseBoolean(strings[2]));
			if(r == null ) return false;
			send(r, REQ, UserSession.getSocket());
			res = (Response) receive(UserSession.getSocket());
			if (res == null) return false;
			if(res.isSuccess()) {
				return true;
			};
			break;
		}
		case LIST_RESTAURANT: {
			r = prepareList();
			List<RestaurantBean> lr = new ArrayList<>();
			if (r==null) return null;
			send(r, REQ, UserSession.getSocket());
			res = (Response) receive(UserSession.getSocket());
			if (res == null) return null;
			if(res.isSuccess()) {
				for(Restaurant rr:res.getRestaurants()) {
					restIdMap.put(rr.getName(), rr.getIdRisto());
					lr.add(rr.getBean());
				}
				return lr;
			};
			break;
		} case RESERVATION: {
			r = prepareReservation(strings[0], strings[1], strings[2], Integer.parseInt(strings[3]));
			if(r == null ) return false;
			send(r, REQ, UserSession.getSocket());
			res = (Response) receive(UserSession.getSocket());
			if (res == null) return false;
			if(res.isSuccess()) {
				return true;
			};
			break;
		} case LIST_RESERVATION: {
			rsvList.clear();
			r = prepareListReservation();
			if(r == null ) return null;
			send(r, REQ, UserSession.getSocket());
			res = (Response) receive(UserSession.getSocket());
			if (res == null) return null;
			if(res.isSuccess()) {
				rsvList.addAll(res.getReservations());
				List<ReservationBean> lr = new ArrayList<>();
				for(Reservation resv: res.getReservations()) {
					lr.add(resv.getBean());
				};
				return lr;
			};
			
		} case LIST_RESERVATION_REST: {
			r = prepareListReservationRest();
			if(r == null ) return null;
			send(r, REQ, UserSession.getSocket());
			res = (Response) receive(UserSession.getSocket());
			if (res == null) return null;
			if(res.isSuccess()) {
				rsvList.addAll(res.getReservations());
				List<ReservationBean> lr = new ArrayList<>();
				for(Reservation resv: res.getReservations()) {
					lr.add(resv.getBean());
				};
				return lr;
			};
			
		} case DELETE_RESERVATION: {
			
			r = prepareDeleteReservation(Integer.parseInt(strings[0]));
			
			if(r == null ) return false;
			send(r, REQ, UserSession.getSocket());
			res = (Response) receive(UserSession.getSocket());
			if (res == null) return false;
			if(res.isSuccess()) {
				return true;
			};
			break;
		} case CHECK_SEATS: {
			r = prepareCheckSeats(restIdMap.get(strings[0]), strings[1], strings[2]);
			send(r, REQ, UserSession.getSocket());
			res = (Response) receive(UserSession.getSocket());
			if (res == null) return false;
			if(res.isSuccess()) {
				return res.getReservations().get(0).getSeats();
			};
			break;			
		} case MODIFY_RESTAURANT:{
			//nome, tipo, costo, città, undirizzo, descrizion, num posti, orario
			return true;
		}
		
		default: break;
		}
	return null;
	}
	
/*
 * @TODO Adattare metodi a nuovi User
 */
	public static Request prepareLogin(String username, String password) {
				
			User u = new User();
			//Create Request
			u.setUsername(username);
			u.setPassword(password);
			
			return new Request(LOGIN, u, null, null);
	}
	
	public static Request prepareRegistration(String username, String password, boolean restaurateur) {
		
		//Create User Bean to insert in Request
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setRestaurateur(restaurateur);
		Restaurant r = null;
		if (restaurateur) r =new Restaurant();
		//Create Request
		return new Request(REGISTRATION, u, r, null);
	};
	
	public static Request prepareList() {
		return new Request(LIST_RESTAURANT, null, null, null);
	};
	
	
	public static Request prepareReservation(String n, String d, String h, int s) {
		
		Reservation r = new Reservation();
		r.setCustomer(UserSession.getUser());
		r.setDate(d);
		r.setResTime(OpeningHour.valueOf(h));
		Restaurant rst = new Restaurant();
		rst.setName(n);
		rst.setIdRisto(restIdMap.get(n));
		r.setRestaurant(rst);
		
		return new Request(RESERVATION, UserSession.getUser(), rst, r);
		
	}
	
	public static Request prepareListReservation() {
		return new Request(LIST_RESERVATION, UserSession.getUser(), null ,null);
	}
	
	public static Request prepareListReservationRest() {
		return new Request(LIST_RESERVATION_REST, UserSession.getUser(), null ,null);
	};
	
	public static Request prepareDeleteReservation(int rid) {
		Reservation r = new Reservation();
		return new Request(DELETE_RESERVATION, UserSession.getUser(), null, r);
	}
	
	public static Request prepareModifyRestaurant(int idR, int idU, String n, String t, int p, String c, String a, String d, int s, String oa ) {
		Restaurant r = new Restaurant();
		return new Request(MODIFY_RESTAURANT, UserSession.getUser(), r, null);
	};
	
	public static Request prepareCheckSeats(int rid, String d, String oa) {
		Reservation r = new Reservation();
		r.setCustomer(UserSession.getUser());
		r.setRestaurant(new Restaurant());
		r.getRestaurant().setIdRisto(rid);
		r.setDate(d);
		r.setResTime(OpeningHour.valueOf(oa));
		return new Request(CHECK_SEATS, UserSession.getUser(), null, r);
	};	
	
/*----------------------------------------------------------------------------------------------------------------------------
 * SERVER SIDE UTILITIES @TODO ADATTARE RICHIESTE A DB
 -----------------------------------------------------------------------------------------------------------------------------*/
	public static int parseRequest(Request r) {
		return r.getReqType();
	};	
	
	
	public static Response buildResponse(int type, boolean success, Object rx) {
		
		switch(type) {
		case LOGIN: { 
			User u = (User)rx;
			return new Response(success, type, null, null, u );
		}
		case REGISTRATION: {
			 return new Response(success, type, null, null, null );
		} 
		case LIST_RESTAURANT: {
			List<Restaurant> lr = (List<Restaurant>) rx;
			return new Response(success, type, lr , null, null);
		} case RESERVATION: {
			
			Reservation res = (Reservation)rx;
			List<Reservation> lr= new ArrayList<>();
			lr.add(res);
			return new Response(success, type, null, lr, null );
			
		} case LIST_RESERVATION: {
			List<Reservation> lr= (List<Reservation>) rx;
			return new Response(success, type, null, lr, null );
			
		} case LIST_RESERVATION_REST: {
			List<Reservation> lr= (List<Reservation>) rx;
			return new Response(success, type, null, lr, null );
			
		} case DELETE_RESERVATION: {
			return new Response(success, type, null, null, null);
		} case CHECK_SEATS: {
			Restaurant r = new Restaurant(0 , 0, "", "", 0, "", "", "", 0, "");
			List<Restaurant> lr = new ArrayList<>();
			lr.add(r);
			return new Response(success, type, lr, null, null);
			
		} case MODIFY_RESTAURANT:{			
			return new Response(success, type, null, null, null);
		}
		default: break;
		}
	return null;
	}
}



