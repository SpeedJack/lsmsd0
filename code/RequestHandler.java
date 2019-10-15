

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
				
			User u = new User(username, password, "");
			List<User> ul = new ArrayList<User>();
			ul.add(u);
			//Create Request
			return new Request(0, "Login Request", true,  ul, new ArrayList<>(), new ArrayList<>());
	}
	
	public static Request prepareRegistration(String username, String password, String type) {
		
		
		//Create User Bean to insert in Request
		User u = new User(username, password, type);
		List<User> ul = new ArrayList<User>();
		ul.add(u);
		
		//Create Request
		return new Request(1, "Registration Request", true,  ul, new ArrayList<>(), new ArrayList<>());
	};
	
	public static Request prepareList() {
		return new Request(2, "List Request", true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	};
	public static Request prepareBook(User u, String todo) {
		Reservation r = new Reservation(0, 0, "", 0, "");
		Scanner sc = new Scanner(System.in);
		String input;
		int type;
		
		if(todo.equals("add")) type = 10;
		else if(todo.equals("mod")) type = 11;
		else if(todo.equals("del")) type = 12;
		else return null;
		
		if(todo.equals("add") || todo.equals("mod")) {
		r.setUser(u.getUserId());
		
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
		} if (todo.equals("del")) {
			System.out.println("Insert Reservation Id");
			input = sc.nextLine();
			r.setReservation(Integer.parseInt(input));
		}
		
		List<User> ul = new ArrayList<User>();
		ul.add(u);
		List<Reservation> rl = new ArrayList<Reservation>();
		rl.add(r);
		sc.close();
		return new Request(type, "Booking Request", true, ul, new ArrayList<>(), rl);
		
	}
	public static Request prepareListBooking(User u) {
		List<User> ul = new ArrayList<User>();
		ul.add(u);
		return new Request(4, "List Booking Request", true, ul, new ArrayList<>(), new ArrayList<>());
	}
	public static Request prepareModifyReservation(User u) {
		Reservation r = new Reservation(0, 0, "", 0, "");
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
	
	public static Request prepareRestaurant(User u, String todo) {
		Restaurant r = new Restaurant("", "", 0, "", "","", 0, "", "");
		Scanner sc = new Scanner(System.in);
		String input;
		int type;
		if(todo.equals("add")) type = 20;
		else if(todo.equals("mod")) type = 21;
		else if(todo.equals("del")) type = 22;
		else return null;
		
		
	}

	
	
};



