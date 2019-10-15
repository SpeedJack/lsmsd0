

import java.net.*;

import com.thoughtworks.xstream.XStream;

import java.io.*;

import java.util.*;

class RequestHandler {
	
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
	}
	public static Request prepareLogin(String username, String password) {
		Scanner sc = new Scanner(System.in);
		User u = new User(username, password, "");
		String input;
		System.out.println("insert your username:");
		username = sc.nextLine();
		System.out.println("Insert your password:");
		password = sc.nextLine();
		if(input.equals("/login")) {
			
			u = new User(username, password, "");
			List<User> ul = new ArrayList<User>();
			ul.add(u);
			//Create Request
			return new Request(0, "Login Request", true,  ul, new ArrayList<>(), new ArrayList<>());
	};
	
	public static Request prepareRegistration(String username, String password) {
		System.out.println("insert 'c' if you are a customer or 'r' if you are a ristorateur");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String type;
		while(type.isEmpty()) {
			if(input.equals("c"))
					type = new String("cliente");
			else if(input.equals("r"))
					type = new String("ristoratore");
			else System.out.println("Bad Input, Values Admitted 'c' or 'r'");
		};
		
		//Create User Bean to insert in Request
		User u = new User(username, password, type);
		List<User> ul = new ArrayList<User>();
		ul.add(u);
		
		//Create Request
		return new Request(1, "Registration Request", true,  ul, new ArrayList<>(), new ArrayList<>());
		};
	
	public static Request prepareList(User u) {};
	public static Request prepareBook(User u) {
		Reservation r = new Reservation("", 0, "", 0, "");
		Scanner sc = new Scanner(System.in);
		String input;
		System.out.println("resturant id:");
		input = sc.nextLine();
		r.setRestaurant(Integer.parseInt(input));
		
		System.out.println("Date of the reservation(YYYY-MM-DD):");
		input = sc.nextLine();
		r.setDate(input);
		
		System.out.println("Hour of the reservation(HH:MM):");
		//inserire orario
		input = sc.nextLine();
		r.setHour(input);
		
		System.out.println("Number of seats:");
		input = sc.nextLine();
		r.setSeats(Integer.parseInt(input));
		
		List<User> ul = new ArrayList<User>();
		ul.add(u);
		List<Reservation> rl = new ArrayList<Reservation>();
		rl.add(r);
		sc.close();
		return new Request(2, "Booking Request", true, ul, new ArrayList<>(), rl);
		
	}
	public static Request prepareListBooking(User u) {}
	public static Request prepareModifyReservation(User u) {}
	public static Request prepareDeleteReservation(User u) {}
	public static Request prepareModifyRestaurant(User u) {}
	public static Request prepareDeleteRestaurant(User u) {}
	
	
};



