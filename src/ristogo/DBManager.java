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
	    	Class.forName("com.mysql.jdbc.Driver");
	    	connectionToDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristogo?useSSL=false", "root", "root");      
	    } catch (Exception e) {
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
                if(res.next()) {
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
            	System.out.println("Error in login in DBManager");
    	        System.err.println(e.getMessage());
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
                if(result.next()) {

                	int id = result.getInt("IdUtente");
                	if(us.isRestaurateur()) {
                		PreparedStatement ps2 = connectionToDB.prepareStatement("INSERT INTO ristorante (IdUtente, nome, genere, costo, citta, via, descrizione, coperti, apertura)"												
                															+ " VALUES(?, null, null, null, null, null, null, null, null)");
                		ps2.setInt(1, id);
                		res = ps2.executeUpdate();
                	}
                }
                return res;
                
        }catch(SQLException e) {
	    	System.out.println("Error in register in DBManager");
	        System.err.println(e.getMessage());
	        return -1;
            }
	}
	 
//list of restaurant	 
	public static List<Restaurant> list_restaurant(){
		 
            List<Restaurant> restaurants = new ArrayList<>();
            try {
                PreparedStatement ps = connectionToDB.prepareStatement("SELECT * " 
                                                                    + "FROM ristorante r INNER JOIN utente u "
                                                                    + "ON r.IdUtente = u.IdUtente "
                                                                    + "WHERE r.nome  != '';");
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
            	System.out.println("Error in list_restaurant in DBManager");
    	        System.err.println(e.getMessage());
            }
            return restaurants;
	 }
	
//modify a restaurant
	public static int updateRestaurant(Restaurant r) {
                 
        int res = -1;
        try {
        	PreparedStatement ps = connectionToDB.prepareStatement("UPDATE ristorante " +
                    "SET nome = ? , genere = ? , costo = ? , citta = ? , via = ? " +
                    ", descrizione = ? , coperti = ? , apertura = ? WHERE IdRisto = ? AND IdUtente= ? ;"); 
            ps.setString(1, r.getName());
            ps.setString(2,r.getType());
            ps.setInt(3, r.getCost());
            ps.setString(4, r.getCity());
            ps.setString(5, r.getAddress());
            ps.setString(6, r.getDescription());
            ps.setInt(7, r.getSeatsAvailable());
            ps.setString(8, r.getOpenAt().toString());
            ps.setInt(9, r.getIdRisto()); 
            ps.setInt(10, r.getIdOwner());
            res = ps.executeUpdate();
		
        }catch(SQLException e) {
        	System.out.println("Error in updateRestaurant in DBManager");
	        System.err.println(e.getMessage());
        }	 
        return res;
	}
	
//check for a table
        public static int check(Reservation r) {
        	int totalSeats =0;
        	int occupedSeats = 0;
       	
        try {
        	PreparedStatement ps = connectionToDB.prepareStatement("SELECT r.coperti AS Coperti "
																	+"FROM ristorante r WHERE r.IdRisto= ? ;");
        	
        	ps.setInt(1, r.getRestaurant().getIdRisto());
        	ResultSet res = ps.executeQuery();
        	if(res.next()) {
        		totalSeats = res.getInt("Coperti");
        	}
            PreparedStatement ps1 = connectionToDB.prepareStatement("SELECT SUM(p.persone) AS PostiOccupati "
            							+"FROM prenotazione p WHERE p.IdRisto= ? AND p.data = ? AND p.orario=? ;");
						
            ps1.setInt(1, r.getRestaurant().getIdRisto());
			ps1.setString(2,r.getDate());
			ps1.setString(3,r.getResTime().toString());
			ResultSet res1 = ps1.executeQuery();
			if(res1.next()) {
				occupedSeats = res1.getInt("PostiOccupati");
			}
			if(totalSeats -occupedSeats == 0) {
				return -1;
			}
			else {
				return totalSeats -occupedSeats;
			}

        }catch(SQLException e) {
        	System.out.println("Error in check in DBManager");
	        System.err.println(e.getMessage());
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
            	System.out.println("Error in book in DBManager");
    	        System.err.println(e.getMessage());
            }	 
            return res;
	 }

//list of reservation (client/restaurant owner) 
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
                + " INNER JOIN utente u ON u.IdUtente = p.IdCliente"
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
            	System.out.println("Error in list_reservation in DBManager");
    	        System.err.println(e.getMessage());
            }
		  
            return reservations;   
	}

//delete a reservation
	public static int deleteReservation(Reservation r) {
	
	    int res = -1;
	    try {
			PreparedStatement ps = connectionToDB.prepareStatement("DELETE FROM prenotazione " + 
			                                                               "WHERE IdPrenotazione = ?"); 									
			ps.setInt(1, r.getIdRes());
			res = ps.executeUpdate();
	            
        }catch(SQLException e) {
        	System.out.println("Error in deleteReservation in DBManager");
	        System.err.println(e.getMessage());
	        }	 
        return res;
	}
	
//restaurant info 
	public static Restaurant restaurantInfo(int id) {
		 try {
			 	Restaurant r = new Restaurant();
	        	PreparedStatement ps = connectionToDB.prepareStatement("SELECT * "
																		+"FROM ristorante r WHERE r.IdUtente= ? ;");
	        	
	        	ps.setInt(1, id);
	        	ResultSet res = ps.executeQuery();
	        	if(res.next()) {
	        		r.setIdRisto(res.getInt("IdRisto"));
	        		r.setIdOwner(id);
	        		if(res.getString("nome") != null){
		        		r.setName(res.getString("nome"));
		        		r.setType(res.getString("genere"));
		        		r.setCost(res.getInt("costo"));
		        		r.setCity(res.getString("citta"));
		        		r.setAddress(res.getString("via"));
		        		r.setDescription(res.getString("descrizione"));
		        		r.setSeatsAvailable(res.getInt("coperti"));
		        		r.setOpenAt(OpeningHour.valueOf(res.getString("apertura")));
	        		}
	        		return r;
	        	}
	        	return null;
		 }catch(SQLException e) {
			 System.out.println("Error in restaurantInfo in DBManager");
		     System.err.println(e.getMessage());
		     return null;
		 }
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
        	System.out.println("Error in updateReservation in DBManager");
	        System.err.println(e.getMessage());
	        }	 
        return res;
	}
	
	
}