package ristogo;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolServer implements Runnable{
	
	 class Worker implements Runnable {
		private Socket clientSocket = null;
		private Request currentRequest = null;
		private Response currentResponse = null;
		public Worker(Socket cs) {
			this.clientSocket = cs;
		};
		
		private Response evaluateRequest() {
			Response rx = null;
			User u = null;
			boolean success = true;
			try {
				currentRequest = (Request)MessageHandler.receive(clientSocket);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			if (currentRequest == null) return null;
			u = currentRequest.getUser();
			int code = MessageHandler.parseRequest(currentRequest);
			switch (code) {
			case MessageHandler.LOGIN: { 
				System.out.println("CALLING DB MANAGER FOR QUERY: " + code);
				User ret = DBManager.login(u);
				if (ret == null) success = false;
				System.out.println("BUILDING RESPONSE TO" + code);
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, ret);
				break;
			}
			case MessageHandler.REGISTRATION: {
				
				int ret = DBManager.register(u);
				if (ret == -1) success = false;
				u.setIdUser(ret);
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, u);
				break;
			} 
			case MessageHandler.LIST_RESTAURANT: {
				
				List<Restaurant> lr = DBManager.list_restaurant();
				if (lr == null) success = false;
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, lr);
				break;
			} case MessageHandler.RESERVATION: {
				
				int ret = DBManager.book(currentRequest.getReservation());
				if (ret == -1) success = false;
				Reservation rsv = currentRequest.getReservation();
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, rsv);
				break;
			} case MessageHandler.LIST_RESERVATION: {
				
				List<Reservation> lr= DBManager.list_reservation(currentRequest.getUser(), false);
				if (lr == null) success = false;
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, lr);
				break;
			} case MessageHandler.LIST_RESERVATION_REST: {
				
				List<Reservation> lr= DBManager.list_reservation(currentRequest.getUser(), true);
				if (lr == null) success = false;
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, lr);
				break;
			} case MessageHandler.DELETE_RESERVATION: {
				
				int ret = DBManager.deleteReservation(currentRequest.getReservation());
				if (ret == -1) success = false;
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, null);
				break;
			} case MessageHandler.CHECK_SEATS: {
				
				int ret = DBManager.check(currentRequest.getReservation());
				
				if (ret == -1) success = false;
				currentRequest.getReservation().setSeats(ret);
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, currentRequest.getReservation());
				break;
			} case MessageHandler.MODIFY_RESTAURANT:{	
				
				int ret = DBManager.updateRestaurant(currentRequest.getRestaurant());
				if (ret == -1) success = false;
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, null);
				break;
			}case MessageHandler.EXIT:{	
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), true, null);
				break;
			} case MessageHandler.RESTAURANT_INFO :{
				Restaurant r = DBManager.restaurantInfo(currentRequest.getUser().getIdUser());
				rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, r);
				break;
			}
			default: break;
			}
			return rx;
		}
		
		
		

		@Override
		public void run() {
			try {
				System.out.println("EVALUATING REQUEST");
				currentResponse = evaluateRequest();
				if (currentResponse == null) return;
				if(currentResponse.getResType()== MessageHandler.EXIT) {
					System.out.println("CLIENT EXIT");
					return;
				}
				System.out.println("SENDING RESPONSE");
				MessageHandler.send(currentResponse, MessageHandler.RES, clientSocket);	
			} catch (Exception e) {
				e.printStackTrace();
				return;
			};
		}
		
	}


    protected int          serverPort   = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool =
        Executors.newFixedThreadPool(10);

    public ThreadPoolServer(int port){
        this.serverPort = port;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        System.out.println("RistoGo Thread Pool Server Started");
        openServerSocket();
        System.out.println("Server Socket is open");
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    break;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            System.out.println("New Request accepted");
            this.threadPool.execute(new Worker(clientSocket));
        };
        this.threadPool.shutdown();
        System.out.println("Server Stopped.") ;
        
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }
}