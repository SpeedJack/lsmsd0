import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
					 												+"WHERE nome= "+us.name+" AND password = " + us.password + ";");
			 ResultSet res = ps.executeQuery();
			 if(res.getInt("Numero")== 1) {
				 return res.getInt("IdUtente");
			 }
			 else {
				 return -1;
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
		 
	 }
	 
	 public static int register(User us) {
		 
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("SELECT COUNT(*) AS Numero" 
					 												+ "FROM utente"
					 												+"WHERE nome= "+us.name+";");
			 ResultSet res = ps.executeQuery();
			 if(res.getInt("Numero")== 0) {
				 return 1;
			 }
			 else {
				 return -1;
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
		  
	 }
	 
	 
	 public static List<Resturant> list_resturant(){
		 List<Resturant> resturant = new ArrayList<>();
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("SELECT*" 
					 												+ "FROM ristorante;");
			 ResultSet res = ps.executeQuery();
			 while(res.next()) {
				 String name = res.getString("nome");
				 String type = res.getString("genere");
				 int price = res.getInt("prezzo");
				 String city = res.getString("citta");
				 String address = res.getString("via");
				 String description = res.getString("descrizione");
				 int seats = res.getInt("coperti");
				 String open = res.getString("oraApertura");
				 String close = res.getString("oraChiusura");
				 
				 resturant.add(new Resturant(name, type, price, city, address, description, seats, open, close));
				 
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
		  
	 }
	
	 public static int book(Reservation r) {
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("INSERT INTO prenotazione" 
					 												+ "VALUES(?,?,?,?,?);");
			 ps.setString(1, r.name);
			 ps.setInt(2,r.idResturant);
			 ps.setDate(3,r.date);
			 ps.setString(4,r.hour);
			 ps.setInt(5, r.seats);
			 ResultSet res = ps.executeUpdate();
			 //Manca gestione errore
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }	 
	 }
	
	 public static List<Reservation> list_reservation_client(User s){
		 List<Reservation> reservations = new ArrayList<>();
		 try {
			 PreparedStatement ps = connectionToDB.prepareStatement("SELECT r.nome AS Nome, p.data As Data, p.ora AS ORA, p.persone AS Persone" 
																	+ "FROM prenotazione p INNER JOIN ristorante r ON p.IdRisto=r.IdRisto "
																	+"WHERE nomeCliente= "+s.name+";");
			 ResultSet res = ps.executeQuery();
			 while(res.next()) {
				 String name = res.getString("Nome");
				 Date date = res.getDate("Data");
				 String hour = res.getString("Ora");
				 int seats = res.getInt("Persone");
				 
				 reservations.add(new Reservation(name, date, hour, seats));
				 
			 }
			 
		 }catch(SQLException e) {
			 System.out.println(e.getMessage());
		 }
		  
	 }
	
	
}