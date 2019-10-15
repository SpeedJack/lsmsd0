import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBManager{
	
	public static Connection connectionToDB;
	
	 static {
	      try {
	    	  connectionToDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristogo", "root", "");      
	      } catch (SQLException e) {
	          System.out.println("Impossible to connect with DB");
	        System.err.println(e.getMessage());
	      }
	    }
	
	 
	 public static int login(User us) {
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("SELECT IdUtente, COUNT(*) AS Numero" 
					 												+ "FROM utente"
					 												+"WHERE nome= "+us.getUsername()+" AND password = " + us.getPassword() + ";");
			 ResultSet res = ps.executeQuery();
			 if(res.getInt("Numero")== 1) {
				 return res.getInt("IdUtente");
			 }
			 else {
				 return -1;
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
                         return -1;
		 }
		 
	 }
	 
	 public static int register(User us) {
		 
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("SELECT COUNT(*) AS Numero" 
					 												+ "FROM utente"
					 												+"WHERE nome= "+us.getUsername()+";");
			 ResultSet res = ps.executeQuery();
			 if(res.getInt("Numero")== 0) {
				 return 1;
			 }
			 else {
				 return -1;
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
                         return -1;
		 }
		  
	 }
	 
	 
	 public static List<Restaurant> list_restaurant(){
		 List<Restaurant> restaurant = new ArrayList<>();
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("SELECT*" 
					 												+ "FROM ristorante;");
			 ResultSet res = ps.executeQuery();
			 while(res.next()) {
				 String name = res.getString("nome");
				 String type = res.getString("genere");
                                 int seats = res.getInt("coperti");				 
				 String city = res.getString("citta");
				 String address = res.getString("via");
				 String description = res.getString("descrizione");
				 int price = res.getInt("prezzo");
				 String open = res.getString("oraApertura");
				 String close = res.getString("oraChiusura");
				 
				 restaurant.add(new Restaurant(name, type, seats, city, address, description, price, open, close));
				 
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
		  
                 return restaurant;
	 }
	
	 public static int book(Reservation r) {
                 int res = -1;
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("INSERT INTO prenotazione" 
					 												+ "VALUES(?,?,?,?,?);");
			 ps.setString(1, r.getUsername());
			 ps.setInt(2,r.getRestaurant());
			 ps.setString(3,r.getDate());
			 ps.setString(4,r.getHour());
			 ps.setInt(5, r.getSeats());
			 res = ps.executeUpdate();
			 //Manca gestione errore
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }	 
                 return res;
	 }
	
	 public static List<Reservation> list_reservation_client(User s){
		 List<Reservation> reservations = new ArrayList<>();
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("SELECT r.nome AS Nome, p.data As Data, r.IdRisto AS Ristorante, p.ora AS ORA, p.persone AS Persone" 
																	+ "FROM prenotazione p INNER JOIN ristorante r ON p.IdRisto=r.IdRisto "
																	+"WHERE nomeCliente= "+s.getUsername()+";");
			 ResultSet res = ps.executeQuery();
			 while(res.next()) {
				 String name = res.getString("Nome");
				 String date = res.getString("Data");
                                 int restaurant = res.getInt("Ristorante");
                                 int seats = res.getInt("Persone");
				 String hour = res.getString("Ora");
				 
				 reservations.add(new Reservation(name, restaurant, date, seats, hour));
				 
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
		  
            return reservations;   
	 }
	 public static void main (String args[]) {
			
			System.out.println("Welcome to RistoGo!\n"
					+ "The application that allows you to book tables at your favorite restaurants.");
			
			Scanner sc = new Scanner(System.in);
			while(1) {
				//Ricevo richiesta
				Request rst = RequestHandler.receiveRequest();
				//Parse
				//QueryDB
				//Invio risultati
			}
		}	
	
	
}