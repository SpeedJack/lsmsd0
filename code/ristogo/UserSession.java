package ristogo;

import java.net.*;

public class UserSession {
	private static User currUser = null;
	private static Socket sClientToServer = null;
	private static ServerSocket sServerToClient = null;
	static{
		try {
			sClientToServer = new Socket("localhost", 8080);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	static Socket getSocket(){return sClientToServer;};
	static ServerSocket getServerSocket(){return sServerToClient;};
	static User getUser() {return currUser;};
	static void setUser(User u) { currUser = u;};
}
