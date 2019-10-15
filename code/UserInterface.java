import java.util.*;

public class UserInterface{
	
	static int userID;
	static String type;
	
	static Scanner sc;
	static User u;
	
	public static void access() {
		int check = 0;
		String input = new String();
		String username;
		String password;
		
		while(check != 1) {
			input = sc.nextLine();
			if(input.equals("/login") || input.equals("/register")) {
				System.out.println("insert your username:");
				username = sc.nextLine();
				System.out.println("Insert your password:");
				password = sc.nextLine();
				if(input.equals("/login")) {
					
					
//					u = new User(username, password, "");
//					List<User> ul = new ArrayList<User>();
//					ul.add(u);
//					//Create Request
					Request r = RequestHandler.prepareLogin(username, password);
					//Send Request
					RequestHandler.sendRequest(r);
					//Wait response and read results
					Request rx = RequestHandler.receiveRequest();
					if(rx.getSuccess()) {
						u = rx.getUsers().get(0);
						type = u.getType();
						check = 1;
					} else break;
					
				}
				else if(input.equals("/register")) {
					 //metodo che manda richiesta di registrazione.
				System.out.println("insert 'c' if you are a customer or 'r' if you are a ristorateur");
				
				input = sc.nextLine();
				
				while(type.isEmpty()) {
					if(input.equals("c"))
							type = new String("cliente");
					else if(input.equals("r"))
							type = new String("ristoratore");
					else System.out.println("Bad Input, Values Admitted 'c' or 'r'");
				};
				
//				//Create Request
				Request r = RequestHandler.prepareRegistration(username, password,type);
						//Send Request
				RequestHandler.sendRequest(r);
				//Wait response
				Request rx = RequestHandler.receiveRequest();
				if(rx.getSuccess()){
					System.out.println("Registration completed!");
					check = 1;
				} else {
					System.out.println("Invalid credentials, try something different");
				};
			}
			else{
				System.out.println("Not valid input. To login, type '/ login', if you are not registered yet type '/register'\n");	
			};
			}
		}
	}
	
	public static void client_menu() {
		int finish = 0;
		String input = new String();
		System.out.println( "Welcome to the client section of RistoGo!");
		System.out.println( "Here is the list of available actions:");
		System.out.println( "'/list': the list of restaurants available on RistoGo, with all the details for each restaurant");
		System.out.println( "'/book': book a table in one of our restaurants");
		System.out.println( "'/myres': the list of your reservations made, with all the details");
		System.out.println( "'/modres': modify one of yours reservation");
		System.out.println( "'/delres': delete one of yours reservation");
		System.out.println("'/quit': exit to RistoGo\n");
		
		
		while(finish != 1) {
			input = sc.nextLine();
			if(input.equals("/list")) {
				
				//metodo per la lista dei ristoranti
			}
			else if(input.equals("/book")) {
				//Metodo per la prenotazione
				Request rst = RequestHandler.prepareBook(u);
//				Reservation r = new Reservation("", 0, "", 0, "");
//				
//				System.out.println("resturant id:");
//				input = sc.nextLine();
//				r.setRestaurant(Integer.parseInt(input));
//				
//				System.out.println("Date of the reservation(YYYY-MM-DD):");
//				input = sc.nextLine();
//				r.setDate(input);
//				
//				System.out.println("Hour of the reservation(HH:MM):");
//				//inserire orario
//				input = sc.nextLine();
//				r.setHour(input);
//				
//				System.out.println("Number of seats:");
//				input = sc.nextLine();
//				r.setSeats(Integer.parseInt(input));
//				
//				List<User> ul = new ArrayList<User>();
//				ul.add(u);
//				List<Reservation> rl = new ArrayList<Reservation>();
//				rl.add(r);
//				
//				Request rst = new Request(2, "Booking Request", true, ul, new ArrayList<>(), rl);
//				RequestHandlerClient.sendRequest(rst);
				rst = RequestHandler.receiveRequest();
				if(rst.getSuccess()) {
					System.out.println("Booking Completed Succesfully");
				} else {
					System.out.println("Something has gone wrong, please retry! ");
					System.out.println(rst.getMessage());
				};
				
			}
			else if(input.equals("/myres")) {
				
			}
			else if(input.equals("/quit")) {
				finish = 1;
			}
			else {
				System.out.println("Not valid input.");
				System.out.println( "Here is the list of available actions:");
				System.out.println( "'/list': the list of restaurants available on RistoGo, with all the details for each restaurant");
				System.out.println( "'/book': book a table in one of our restaurants");
				System.out.println( "'/myres': the list of your reservations made, with all the details");
				System.out.println( "'/modres': modify one of yours reservation");
				System.out.println( "'/delres': delete one of yours reservation");
				System.out.println("'/quit': exit to RistoGo\n");
			}
		}
		
		
	}
	
	public static void restaurateur_menu() {
		int finish = 0;
		String input = new String();
		System.out.println( "Welcome to the restaurateur section of RistoGo!");
		System.out.println( "Here is the list of available actions:");
		System.out.println( "'/res': the list of  reservations at your resturant, with all the details");
		System.out.println( "'/modify': modify details of your resturant");
		System.out.println("'/quit': exit from RistoGo");

		while(finish != 1) {
			input = sc.nextLine();
			if(input.equals("/res")) {
				
			}
			else if(input.equals("/modify")) {
				
			}
			else if(input.equals("/quit")) {
				finish = 1;
			}
			else {
				System.out.println("Not valid input.");
				System.out.println( "Here is the list of available actions:");
				System.out.println( "'/res': the list of  reservations at your resturant, with all the details");
				System.out.println( "'/modify': modify details of your resturant");
				System.out.println("'/quit': exit from RistoGo");
			}
		}
	}
	
	
	public static void main (String args[]) {
		
		System.out.println("Welcome to RistoGo!\n"
				+ "The application that allows you to book tables at your favorite restaurants.");
		System.out.println("To login, type '/ login', if you are not registered yet type '/register'\n");
		sc = new Scanner(System.in);
		access();
		if(type.equals("cliente")) {
			
			client_menu();
			
		}
		else if(type.equals("ristoratore")) {
			restaurateur_menu();
		}
		sc.close();
		System.out.println("Thank you for using RistoGo. See you soon! :)");
	}	
}