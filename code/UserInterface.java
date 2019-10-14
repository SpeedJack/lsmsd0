import java.util.*;

public class UserInterface{
	
	static int userID;
	static String type;
	
	static Scanner sc;
	
	
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
					
					//metodo che manda la richiesta di accesso con le credenziali (us/pass). Se puo' acccedere viene restituito
					//l'oggetto utente da cui si prevela il tipo di utente e si mette in 'type'. l'userID viene messo in 'UserID'
					type = "cliente";
					check = 1;
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
				User u = new User(username, password, type);
				List<User> ul = new ArrayList<User>();
				ul.add(u);
				
				Request r = new Request(1, "Registration Request", ul, new ArrayList<>(), new ArrayList<>());
				RequestHandlerClient.sendRequest(r);
				Request rx = RequestHandlerClient.receiveRequest();
				
			}
			else{
				System.out.println("Not valid input. To login, type '/ login', if you are not registered yet type '/register'\n");	
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
		System.out.println("'/quit': exit to RistoGo.");
		
		
		while(finish != 1) {
			input = sc.nextLine();
			if(input.equals("/list")) {
				//metodo per la lista dei ristoranti
			}
			else if(input.equals("/book")) {
				System.out.println("Name of the resturant:");
				//inserire nome del ristorante nella classe bean
				System.out.println("Date of the reservation(YYYY-MM-DD):");
				//inserire data 
				System.out.println("Hour of the reservation(HH:MM):");
				//inserire orario
				System.out.println("Number of seats:");
				
				
				
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
		System.out.println("'/quit': exit to RistoGo");

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
				System.out.println("'/quit': exit to RistoGo");
			}
		}
	}
	
	
	public static void main (String args[]) {
		rhc = new RequestHandlerClient();
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