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
	    	connectionToDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristogo", "root", "root");      
	    } catch (SQLException e) {
	        System.out.println("Impossible to connect with DB");
	        System.err.println(e.getMessage());
	      }
	    }
	
//login	 
	public static User login(User us) {
            try {
            	PreparedStatement ps = connectionToDB.prepareStatement("SELECT u.IdUtente, COUNT(*) AS Numero "+
            			"FROM utente u "+
            			"WHERE u.nome = ? AND u.password =? "+
            			"group by u.IdUtente; ");                         
                ps.setString(1, us.getUsername());
                ps.setString(2, us.getPassword());
                ResultSet res = ps.executeQuery();
                res.next();
				if(res.getInt("Numero")== 1) {
                    us.setIdUser(res.getInt("IdUtente"));
                    PreparedStatement ps1 = connectionToDB.prepareStatement("SELECT IF(COUNT(*) > 0, TRUE, FALSE) AS hasRestaurants " + 
                    														"FROM utente u INNER JOIN ristorante r ON r.IdUtente = u.IdUtente " + 
                    														"WHERE u.IdUtente = ?;");
                    ps1.setInt(1, us.getIdUser());
                    ResultSet res1 = ps1.executeQuery();
                    res1.next();
                    if(res1.getBoolean("hasRestaurants")) {
                    	us.setRestaurateur(true);
                    }
                    else {
                    	us.setRestaurateur(false);
                    }
                    return us;
                    
                }
				else {
                    return null;
				}			 
            }catch(SQLException e) {
            	System.out.println(e.getMessage());
            	return null;
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
				
		PreparedStatement ps1 = connectionToDB.prepareStatement("SELECT IdUtente"
									+" FROM utente WHERE nome= ? AND password = ?;");
                ps1.setString(1, us.getUsername());
                ps1.setString(2, us.getPassword());
                ResultSet result = ps1.executeQuery();
                result.next();
                int id = result.getInt("IdUtente");
				
                PreparedStatement ps2 = connectionToDB.prepareStatement("INSERT INTO ristorante (IdUtente, nome, genere, costo, citta, via, descrizione, coperti, apertura)"
								+ " VALUES(?, null, null, null, null, null, null, null, null)");
		ps2.setInt(1, id);
		if(us.isRestaurateur())
                    res = ps2.executeUpdate();
                
	    }catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return res;
	}
	 
//list of restaurant	 
	public static List<Restaurant> list_restaurant(){
		 
            List<Restaurant> restaurants = new ArrayList();
            try {
                PreparedStatement ps = connectionToDB.prepareStatement("SELECT * " 
                                                                    + "FROM ristorante r INNER JOIN utente u "
                                                                    + "ON r.IdUtente = u.IdUtente;");
                ResultSet res = ps.executeQuery();
                    while(res.next()) {
	                int idRestaurant = res.getInt("r.IdRisto");
	                int idUser = res.getInt("u.IdUtente");
	                String name = res.getString("r.nome");
	                String type = res.getString("r.genere");
	                int price = res.getInt("r.costo");				 
	                String city = res.getString("r.citta");
	                String address = res.getString("r.via");
	                String description = res.getString("r.descrizione");
	                int seats = res.getInt("r.coperti");
	                String open = res.getString("r.apertura");
				 
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
            PreparedStatement ps = connectionToDB.prepareStatement("SELECT (r.coperti - SUM(p.coperti)) AS PostiRimasti "
            							+"FROM ristorante r INNER JOIN prenotazione p ON r.IdRisto = p.IdRisto "
                                                                + "WHERE p.IdRisto= ? AND p.data = ? AND p.orario=? ;");
						
			ps.setInt(1, r.getRestaurant().getIdRisto());
			ps.setString(3,r.getDate());
			ps.setString(4,r.getResTime().toString());
			ResultSet res = ps.executeQuery();
			res.next();
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
            	if(!restaurateur) {ps = connectionToDB.prepareStatement("SELECT p.IdPrenotazione AS Prenotazione, u.nome AS NomeU, "
                + " r.IdUtente AS Utente, p.data As Data, r.IdRisto AS Ristorante, p.orario AS Orario, p.persone AS Persone, r.nome AS NomeR "  
                + " FROM prenotazione p INNER JOIN ristorante r ON p.IdRisto = r.IdRisto "
                + " INNER JOIN utente u ON u.IdUtente = p.IdCliente"
                + " WHERE p.idCliente = ?;");}
            	else {
                    ps = connectionToDB.prepareStatement("SELECT p.IdPrenotazione AS Prenotazione, u.nome AS NomeU, "
                + " r.IdUtente AS Utente, p.data As Data, r.IdRisto AS Ristorante, p.orario AS Orario, p.persone AS Persone, r.nome AS NomeR "  
                + " FROM prenotazione p INNER JOIN ristorante r ON p.IdRisto = r.IdRisto "
                + " INNER JOIN utente u ON u.IdUtente = r.IdUtente"
                + " WHERE r.IdUtente = ?;");
            	};
                
                ps.setInt(1, s.getIdUser());
                ResultSet res = ps.executeQuery();
		while(res.next()) {
	            int reservation = res.getInt("Prenotazione");
	            int user = res.getInt("Utente");
	            int restaurant = res.getInt("Ristorante");
	            String date = res.getString("Data");
	            String hour = res.getString("Orario");
	            int seats = res.getInt("Persone");
                    String nameUser = res.getString("NomeU");
                    String nameRestaurant = res.getString("NomeR");
                     
                    User u = new User();
                    u.setIdUser(user);
                    u.setUsername(nameUser);
                    Restaurant r = new Restaurant();
                    r.setIdRisto(restaurant);
                    r.setName(nameRestaurant);
                    
	            reservations.add(new Reservation(reservation, u, r, date, hour, seats));		 
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
			ps.setInt(1, r.getIdRes());
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
            ps.setString(2,r.getResTime().toString());
            ps.setInt(3, r.getSeats());
            ps.setInt(4, r.getIdRes());	 
            res = ps.executeUpdate();
	
        }catch(SQLException e) {
        	System.out.println(e.getMessage());
	        }	 
        return res;
	}
	
	
}