package ristogo;

public class RistogoServer {
	private static int serverPort = 8080;
    
	public static void main(String[] args) {
    	ThreadPoolServer server = new ThreadPoolServer(serverPort);
    	new Thread(server).start();
    }
    
   
 
}
