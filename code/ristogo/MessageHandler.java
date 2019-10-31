package ristogo;
/*@TODO
 * Aggiungere condizione per request/response
 * Aggiungere gestione risposte
* 
*/

import java.net.*;
import com.thoughtworks.xstream.*;

import java.io.*;

import java.util.*;

class MessageHandler {
	private static Map<String,Integer> restIdMap = new HashMap<String, Integer>(); //Map Used to save the RestId using as key the name
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
	public static final int EXIT = 10;
	public static final int RESTAURANT_INFO = 11;
	
	/*----------------------------------------------------------------------------------------------------------------------------
	 * COMMON UTILITIES
	 -----------------------------------------------------------------------------------------------------------------------------*/

	public static void send(Object r, String type, Socket s) { 
		XStream xs = new XStream();

		if(type.equals(REQ)) {
			
			xml = xs.toXML((Request)r);
			
		} else if (type.equals(RES)) {
			
			xml = xs.toXML((Response)r);
		}
	    try {
	    	DataOutputStream dout = new DataOutputStream(s.getOutputStream());
	    	//System.out.println("[MESSAGE HANDLER] SEND XML: " + xml);
	    	dout.writeUTF(xml);
	    	
	    } catch (IOException e) {
	    		e.printStackTrace();
	    	}
		return;
	};
	
	public static Object receive(Socket s) {
		XStream xs = new XStream();
		String xml = null;
		try {
			DataInputStream din = new DataInputStream(s.getInputStream());
			//System.out.println("[MESSAGE HANDLER] RECEIVE XML: " + xml);
			xml = din.readUTF(); 
			return xs.fromXML(xml);
			
		} catch(EOFException eofex) {
			//System.out.println(eofex.getMessage());
			return xs.fromXML(xml);
		} 
		catch (IOException e) {
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
		//System.out.println("SendRequest " + type);
		Request r = null; 
		Response res = null;
		try(Socket s = new Socket("localhost", 8080)){
			switch(type) {
			case LOGIN: {
				r = prepareLogin(strings[0], strings[1] );
				if(r == null ) return null;

				send(r, REQ, s);
				System.out.println("Send LOGIN");
				res = (Response) receive(s);
				s.close();
				if(res.isSuccess()) {
					UserSession.setUser(res.getUser());
					res.getUser().setUsername(strings[0]);
					res.getUser().setPassword(strings[1]);
					return res.getUser().getBean();

				} else return false;
			}
			case REGISTRATION: {
				boolean isRes = true;
				if(strings[2].equals("Customer")) isRes = false;
				r = prepareRegistration(strings[0], strings[1], isRes);
				if(r == null ) return false;
				System.out.println("Send REGISTRATION");
				send(r, REQ, s);
				res = (Response) receive(s);
				if(res.isSuccess()) {
					return true;
				}
				else {
					return false;
				}
			}
			case LIST_RESTAURANT: {
				r = prepareList();
				List<RestaurantBean> lr = new ArrayList<>();
				if (r==null) return null;
				System.out.println("Send LIST_RESTAURANT");
				send(r, REQ, s);
				res = (Response) receive(s);
				if (res == null) return null;
				if(res.isSuccess()) {
					for(Restaurant rr:res.getRestaurants()) {
						restIdMap.put(rr.getName(), rr.getIdRisto());
						lr.add(rr.getBean());
					}
					return lr;
				} else return false;
			} case RESERVATION: {
				r = prepareReservation(strings[0], strings[1], strings[2], Integer.parseInt(strings[3]));
				if(r == null ) return false;
				System.out.println("Send RESERVATION");
				send(r, REQ, s);
				res = (Response) receive(s);
				
				if(res.isSuccess()) {
					return true;
				} else return false;
				
			} case LIST_RESERVATION: {
		
				r = prepareListReservation();
				if(r == null ) return null;
				System.out.println("Send LIST_RESERVATION");
				send(r, REQ, s);
				res = (Response) receive(s);
				
				if(res.isSuccess()) {
					List<ReservationBean> lr = new ArrayList<>();
					for(Reservation resv: res.getReservations()) {
						lr.add(resv.getBean());
					};
					return lr;
				} else return false;

			} case LIST_RESERVATION_REST: {
				r = prepareListReservationRest();
				if(r == null ) return null;
				System.out.println("Send LIST_RESERVATION_REST");
				send(r, REQ, s);
				res = (Response) receive(s);
				
				if(res.isSuccess()) {
					
					List<ReservationBean> lr = new ArrayList<>();
					for(Reservation resv: res.getReservations()) {
						lr.add(resv.getBean());
					};
					return lr;
				} else return null;

			} case DELETE_RESERVATION: {

				r = prepareDeleteReservation(Integer.parseInt(strings[0]));
				System.out.println("Send DELETE_RESERVATION");
				send(r, REQ, s);
				res = (Response) receive(s);
				if(res.isSuccess()) {
					return true;
				}else {
					return false;
				}
			} case CHECK_SEATS: {
				r = prepareCheckSeats(restIdMap.get(strings[0]), strings[1], strings[2]);
				System.out.println("Send CHECK_SEATS");
				send(r, REQ, s);
				res = (Response) receive(s);
				
				if(res.isSuccess()) {
					return res.getReservations().get(0).getSeats();
				} else return false;
				
			} case MODIFY_RESTAURANT:{
				//nome, tipo, costo, città, undirizzo, descrizion, num posti, orario
				r = prepareModifyRestaurant(strings[0], strings[1], Integer.parseInt(strings[2]), strings[3], strings[4], strings[5], Integer.parseInt(strings[6]), strings[7]);
				System.out.println("Send MODIFY_RESTAURANT");
				send(r, REQ, s);
				res = (Response) receive(s);
				if(res.isSuccess()) {
					return true;
				}else {
					return false;
				}
			} case EXIT: {
				r = prepareExit();
				System.out.println("Send EXIT");
				send(r, REQ, s);
				break;			
			} case RESTAURANT_INFO:{
			r = prepareRestaurantInfo();
			System.out.println("Send RESTAURANT_INFO");
			send(r, REQ, s);
			res = (Response) receive(s);
			if(res.isSuccess()) {
				System.out.println("ID RISTO CARICATO :" + res.getRestaurants().get(0).getIdRisto());
				UserSession.setCurrRestaurant(res.getRestaurants().get(0));
				return res.getRestaurants().get(0).getBean();
			} else return null;
			}
			default: break;
			}
			return null;
		} 
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
private static Request prepareRestaurantInfo() {
		return new Request(RESTAURANT_INFO, UserSession.getUser(), null, null);
 	}


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
		//Create Request
		return new Request(REGISTRATION, u, null, null);
	};
	
	public static Request prepareList() {
		return new Request(LIST_RESTAURANT, null, null, null);
	};
	
	
	public static Request prepareReservation(String n, String d, String h, int s) {
		
		Reservation r = new Reservation();
		r.setCustomer(UserSession.getUser());
		r.setDate(d);
		r.setResTime(OpeningHour.valueOf(h.toUpperCase()));
		r.setSeats(s);
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
		r.setIdRes(rid);
		return new Request(DELETE_RESERVATION, UserSession.getUser(), null, r);
	}
	
	public static Request prepareModifyRestaurant(String n, String t, int p, String c, String a, String d, int s, String oa ) {
		Restaurant r = new Restaurant();
		r.setIdRisto(UserSession.getCurrRestaurant().getIdRisto());
		r.setIdOwner(UserSession.getUser().getIdUser());
		r.setName(n);
		r.setType(t);
		r.setCost(p);
		r.setCity(c);
		r.setAddress(a);
		r.setSeatsAvailable(s);
		r.setDescription(d);
		r.setOpenAt(OpeningHour.valueOf(oa));
		return new Request(MODIFY_RESTAURANT, UserSession.getUser(), r, null);
	};
	
	public static Request prepareCheckSeats(int rid, String d, String oa) {
		Reservation r = new Reservation();
		
		r.setCustomer(UserSession.getUser());
		r.setRestaurant(new Restaurant());
		r.getRestaurant().setIdRisto(rid);
		r.setDate(d);
		r.setResTime(OpeningHour.valueOf(oa.toUpperCase()));
		return new Request(CHECK_SEATS, UserSession.getUser(), null, r);
	};	
	
	public static Request prepareExit() {
		return new Request(EXIT, null, null, null);
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
			
			List<Reservation> lr = new ArrayList<>();
			lr.add((Reservation)rx);
			return new Response(success, type, null, lr, null);
			
		} case MODIFY_RESTAURANT:{			
			return new Response(success, type, null, null, null);
		}
		case EXIT:{			
			return new Response(success, type, null, null, null);
		} 
		case RESTAURANT_INFO :{
			List<Restaurant> lr = new ArrayList<>();
			lr.add((Restaurant)rx);
			return new Response(success, type, lr, null, null);
		}
		default: break;
		}
	return null;
	}
}



