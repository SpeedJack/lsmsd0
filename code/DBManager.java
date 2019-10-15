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
							+ "(IdCliente, IdRisto, data, ora, persone) VALUES(?,?,?,?,?);");
			 ps.setInt(1, r.getUser());
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
			 PreparedStatement ps = connectionToDB.prepareStatement("SELECT r.IdUtente AS Utente, " + 
                                 "p.data As Data, r.IdRisto AS Ristorante, p.ora AS ORA, p.persone AS Persone" 
					+ "FROM prenotazione p INNER JOIN ristorante r ON p.IdRisto=r.IdRisto "
                                    +"WHERE nomeCliente= "+s.getUsername()+";");
			 ResultSet res = ps.executeQuery();
			 while(res.next()) {
				 int utente = res.getInt("Utente");
				 String date = res.getString("Data");
                                 int restaurant = res.getInt("Ristorante");
                                 int seats = res.getInt("Persone");
				 String hour = res.getString("Ora");
				 
				 reservations.add(new Reservation(utente, restaurant, date, seats, hour));
				 
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
		  
            return reservations;   
	 }
         
         public static int deleteReservation(Reservation r) {
                 int res = -1;
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("DELETE * FROM prenotazione " + 
                          "WHERE nomeCliente = ? and IdRisto = ? and data = ? and ora = ?"); 
					 												
			 ps.setInt(1, r.getUser());
			 ps.setInt(2,r.getRestaurant());
			 ps.setString(3,r.getDate());
			 ps.setString(4,r.getHour());
			 res = ps.executeUpdate();
			 //Manca gestione errore
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }	 
                 return res;
	 }
         
         public static int updateReservation(Reservation r) {
                 int res = -1;
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("UPDATE prenotazione " + 
                         "SET data = ? AND ora = ? and persone = ? WHERE IdPrenotazione = ? "); 
			
                         ps.setString(1, r.getDate());
                         ps.setString(2,r.getHour());
                         ps.setInt(3, r.getSeats());
			 ps.setInt(4, r.getReservation());
			 
			 res = ps.executeUpdate();
			 //Manca gestione errore
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }	 
                 return res;
	 }
	
	public static int updateRestaurant(Restaurant r) {
                 int res = -1;
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("UPDATE ristorante " +
            "SET nome = ? AND genere = ? AND costo = ? AND citta = ? AND via = ? AND descrizione = ? AND coperti = ? " +
            "AND oraApertura = ? AND oraChiusura = ? WHERE IdRisto = ? "); 
			
                         ps.setString(1, r.getName());
                         ps.setString(2,r.getType());
                         ps.setInt(3, r.getPrice());
			 ps.setString(4, r.getCity());
			 ps.setString(5, r.getAddress());
                         ps.setString(6, r.getDescription());
                         ps.setInt(7, r.getSeats());
			 ps.setString(8, r.getOpeningAt());
                         ps.setString(9, r.getClosingAt());
                         ps.setInt(10, r.getId());
                         
			 res = ps.executeUpdate();
			 //Manca gestione errore
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }	 
                 return res;
	 }
	
	
}