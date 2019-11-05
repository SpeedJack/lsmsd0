package ristogo;

import java.net.*;

public class UserSession {
	private static User currUser = null;
	private static Restaurant currRestaurant = null;
	
	private static Socket sClientToServer = null;
	private static ServerSocket sServerToClient = null;
	static{
		try {
			sClientToServer = new Socket("localhost", 8080);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Restaurant getCurrRestaurant() {
		return currRestaurant;
	}
	public static void setCurrRestaurant(Restaurant currRestaurant) {
		UserSession.currRestaurant = currRestaurant;
	}
	static Socket getSocket(){return sClientToServer;};
	static ServerSocket getServerSocket(){return sServerToClient;};
	static User getUser() {return currUser;};
	static void setUser(User u) { currUser = u;};
}
