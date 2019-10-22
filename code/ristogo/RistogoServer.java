package ristogo;

import java.util.List;

public class RistogoServer {

	public static void main(String[] args) {
		Response rx = null;
		Request r = (Request)MessageHandler.receive();
		int code = MessageHandler.parseRequest(r);
		switch (code) {
		case MessageHandler.LOGIN: { 
			boolean success = true;
			int ret = DBManager.login(r.getUser());
			User u = r.getUser();
			u.setUserId(ret);
			if (ret == -1) success = false;
			rx = MessageHandler.buildResponse(r.getReqType(), success, u);
			break;
		}
		case MessageHandler.REGISTRATION: {
			boolean success = true;
			int ret = DBManager.register(r.getUser());
			if (ret == -1) success = false;
			User u = r.getUser();
			u.setUserId(ret);
			rx = MessageHandler.buildResponse(r.getReqType(), success, u);
			break;
		} 
		case MessageHandler.LIST_RESTAURANT: {
			boolean success = true;
			List<Restaurant> lr = DBManager.list_restaurant();
			if (lr == null) success = false;
			rx = MessageHandler.buildResponse(r.getReqType(), success, lr);
			break;
		} case MessageHandler.RESERVATION: {
			boolean success = true;
			int ret = DBManager.book(r.getReservation());
			if (ret == -1) success = false;
			Reservation rsv = r.getReservation();
			rx = MessageHandler.buildResponse(r.getReqType(), success, rsv);
			break;
		} case MessageHandler.LIST_RESERVATION: {
			boolean success = true;
			List<Reservation> lr= DBManager.list_reservation(r.getUser(), false);
			if (lr == null) success = false;
			rx = MessageHandler.buildResponse(r.getReqType(), success, lr);
			break;
		} case MessageHandler.LIST_RESERVATION_REST: {
			boolean success = true;
			List<Reservation> lr= DBManager.list_reservation(r.getUser(), true);
			if (lr == null) success = false;
			rx = MessageHandler.buildResponse(r.getReqType(), success, lr);
			break;
		} case MessageHandler.DELETE_RESERVATION: {
			boolean success = true;
			int ret = DBManager.deleteReservation(r.getReservation());
			if (ret == -1) success = false;
			rx = MessageHandler.buildResponse(r.getReqType(), success, null);
			break;
		} case MessageHandler.CHECK_SEATS: {
			boolean success = true;
			int ret = DBManager.check(r.getReservation());
			if (ret == -1) success = false;
			Restaurant rest = new Restaurant(0, 0, null, null, ret, null, null, null, 0, null);
			rx = MessageHandler.buildResponse(r.getReqType(), success, rest);
			break;
		} case MessageHandler.MODIFY_RESTAURANT:{	
			boolean success = true;
			int ret = DBManager.updateRestaurant(r.getRestaurant());
			if (ret == -1) success = false;
			rx = MessageHandler.buildResponse(r.getReqType(), success, null);
			break;
		}
		default: break;
		}
		MessageHandler.send(rx, MessageHandler.RES);
	}
}
