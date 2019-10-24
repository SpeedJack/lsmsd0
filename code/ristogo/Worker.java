package ristogo;

import java.net.Socket;
import java.util.List;

public class Worker implements Runnable {
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
		currentRequest = (Request)MessageHandler.receive(clientSocket);
		if (currentRequest == null) return null;
		u = currentRequest.getUser();
		int code = MessageHandler.parseRequest(currentRequest);
		switch (code) {
		case MessageHandler.LOGIN: { 
			
			int ret = DBManager.login(u);
			u.setIdUser(ret);
			if (ret == -1) success = false;
			rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, u);
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
			Restaurant rest = new Restaurant();
			rest.setSeatsAvailable(ret);
			rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, rest);
			break;
		} case MessageHandler.MODIFY_RESTAURANT:{	
			
			int ret = DBManager.updateRestaurant(currentRequest.getRestaurant());
			if (ret == -1) success = false;
			rx = MessageHandler.buildResponse(currentRequest.getReqType(), success, null);
			break;
		}
		default: break;
		}
		return rx;
	}

	@Override
	public void run() {
		try {
			currentResponse = evaluateRequest();
			if (currentResponse == null) return;
			MessageHandler.send(currentResponse, MessageHandler.RES, clientSocket);	
		} catch (Exception e) {
			e.printStackTrace();
			return;
		};
	}
	
}
