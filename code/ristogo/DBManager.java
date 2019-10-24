package ristogo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * FINIRE DI ADATTARE A NUOVI USER REST RES
 */
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
	
//login	 
	public static int login(User us) {
            try {
            	PreparedStatement ps = connectionToDB.prepareStatement("SELECT IdUtente, COUNT(*) AS Numero " 
		                                                                + "FROM utente WHERE nome= ? AND password = ?;");                         
                ps.setString(1, us.getUsername());
                ps.setString(2, us.getPassword());
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
	
//register	 
	public static int register(User us) {
		
            int res = -1;
            try {
				PreparedStatement ps = connectionToDB.prepareStatement("INSERT INTO utente (nome, password) " 
												+ "VALUES(?,?)");
            	ps.setString(1, us.getUsername());
	            ps.setString(2, us.getPassword());
				res = ps.executeUpdate();
				
				int id;
				PreparedStatement ps1 = connectionToDB.prepareStatement("SELECT IdUtente"
																		+"FROM utente WHERE nome= ? AND password = ?;");
                ps1.setString(1, us.getUsername());
                ps1.setString(2, us.getPassword());
                ResultSet result = ps1.executeQuery();
                id = result.getInt("IdUtente");
				
				PreparedStatement ps2 = connectionToDB.prepareStatement("INSERT INTO ristorante (IdUtente, nome, genere, costo, citta, via, descrizione, coperti, apertura)"
																		+ "VALUES(?, null, null, null, null, null, null, null, null)");
				ps2.setInt(1, id);
			                     
	        }catch(SQLException e) {
			            System.out.println(e.getMessage());
            }
            return res;
	}
	 
//list of restaurant	 
	public static List<Restaurant> list_restaurant(){
		 
            List<Restaurant> restaurants = new ArrayList();
            try {
                PreparedStatement ps = connectionToDB.prepareStatement("SELECT* " 
                                                                    + "FROM ristorante;");
                ResultSet res = ps.executeQuery();
				while(res.next()) {
	                int idRestaurant = res.getInt("IdRisto");
	                int idUser = res.getInt("IdUtente");
	                String name = res.getString("nome");
	                String type = res.getString("genere");
	                int price = res.getInt("costo");				 
	                String city = res.getString("citta");
	                String address = res.getString("via");
	                String description = res.getString("descrizione");
	                int seats = res.getInt("coperti");
	                String open = res.getString("apertura");
				 
	                restaurants.add(new Restaurant(idRestaurant, idUser, name, type, price, city, address, description, seats, open));
						 
				}
			 
            }catch(SQLException e) {
            	System.out.println(e.getMessage());
            }
            return restaurants;
	 }
	
//modify a restaurant
	public static int updateRestaurant(Restaurant r) {
                 
        int res = -1;
        try {
        	PreparedStatement ps = connectionToDB.prepareStatement("UPDATE ristorante " +
                    "SET IdUtente = ? AND nome = ? AND genere = ? AND costo = ? AND citta = ? AND via = ? " +
                    "AND descrizione = ? AND coperti = ? AND apertura = ? WHERE IdRisto = ? "); 
            ps.setInt(1, r.getIdOwner());
            ps.setString(2, r.getName());
            ps.setString(3,r.getType());
            ps.setInt(4, r.getCost().ordinal());
            ps.setString(5, r.getCity());
            ps.setString(6, r.getAddress());
            ps.setString(7, r.getDescription());
            ps.setInt(8, r.getSeatsAvailable());
            ps.setString(9, r.getOpenAt().toString());
            ps.setInt(10, r.getIdRisto());                         
            res = ps.executeUpdate();
		
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }	 
        return res;
	}
	
//check for a table
        public static int check(Reservation r) {
       	
        try {
            PreparedStatement ps = connectionToDB.prepareStatement("SELECT (r.coperti - SUM(p.coperti)) AS PostiRimasti"
            														+"FROM ristorante r INNER JOIN prenotazione p ON r.IdRisto = p.IdRisto"
            														+ " WHERE p.IdRisto= ? AND p.data = ? AND p.orario=? ;");
						
			ps.setInt(1, r.getRestaurant());
			ps.setString(3,r.getDate());
			ps.setString(4,r.getHour());
			ResultSet res = ps.executeQuery();
			return res.getInt("PostiRimasti");

        }catch(SQLException e) {
        	System.out.println(e.getMessage());
        	return -1;
        }
        
 }
	
//book a table
	public static int book(Reservation r) {
                 
            int res = -1;
            try {
                PreparedStatement ps = connectionToDB.prepareStatement("INSERT INTO prenotazione " 
							+ "(IdCliente, IdRisto, data, orario, persone) VALUES(?,?,?,?,?);");
				ps.setInt(1, r.getCustomer().getIdUser());
				ps.setInt(2,r.getRestaurant().getIdRisto());
				ps.setString(3,r.getDate());
				ps.setString(4,r.getResTime().toString());
				ps.setInt(5, r.getSeats());
				res = ps.executeUpdate();

            }catch(SQLException e) {
            	System.out.println(e.getMessage());
            }	 
            return res;
	 }

//list of reservation (client/restaurateur) 
	public static List<Reservation> list_reservation(User s, boolean restaurateur){
	
            List<Reservation> reservations = new ArrayList<>();
            try {
            	PreparedStatement ps;
            	if(!restaurateur) {ps = connectionToDB.prepareStatement("SELECT p.IdPrenotazione AS Prenotazione "
                + "r.IdUtente AS Utente, p.data As Data, r.IdRisto AS Ristorante, p.orario AS Orario, p.persone AS Persone "  
                + "FROM prenotazione p INNER JOIN ristorante r ON p.IdRisto = r.IdRisto "
                + "WHERE idCliente = ?;");}
            	else {
                    ps = connectionToDB.prepareStatement("SELECT p.IdPrenotazione AS Prenotazione "
                + "r.IdUtente AS Utente, p.data As Data, r.IdRisto AS Ristorante, p.orario AS Orario, p.persone AS Persone "  
                + "FROM prenotazione p INNER JOIN ristorante r ON p.IdRisto = r.IdRisto "
                + "WHERE r.IdUtente = ?;");
            	};
                
                ps.setInt(1, s.getUserId());
                ResultSet res = ps.executeQuery();
		while(res.next()) {
	            int reservation = res.getInt("Prenotazione");
	            int user = res.getInt("Utente");
	            int restaurant = res.getInt("Ristorante");
	            String date = res.getString("Data");
	            String hour = res.getString("Orario");
	            int seats = res.getInt("Persone");
				 
	            reservations.add(new Reservation(reservation, user, restaurant, date, hour, seats, "", ""));		 
				}
            }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
		  
            return reservations;   
	}

//delete a reservation
	public static int deleteReservation(Reservation r) {
	
	    int res = -1;
	    try {
			PreparedStatement ps = connectionToDB.prepareStatement("DELETE * FROM prenotazione " + 
			                                                               "WHERE IdPrenotazione = ?"); 									
			ps.setInt(1, r.getReservation());
			res = ps.executeUpdate();
	            
        }catch(SQLException e) {
        	System.out.println(e.getMessage());
	        }	 
        return res;
	}
//modify a reservation(not used)         
	public static int updateReservation(Reservation r) {
	         
	    int res = -1;
	    try {
	        PreparedStatement ps = connectionToDB.prepareStatement("UPDATE prenotazione " + 
	                                            "SET data = ? AND orario = ? and persone = ? WHERE IdPrenotazione = ? "); 
            ps.setString(1, r.getDate());
            ps.setString(2,r.getHour());
            ps.setInt(3, r.getSeats());
            ps.setInt(4, r.getReservation());	 
            res = ps.executeUpdate();
	
        }catch(SQLException e) {
        	System.out.println(e.getMessage());
	        }	 
        return res;
	}
	
	
}