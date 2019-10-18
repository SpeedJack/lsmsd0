package ristogo;

/**
 * This class is used to represent a Request from the Client to the Server
 */

class Request {
	private int reqType;
	private User user;
	private Restaurant restaurant;
	private Reservation reservation;
	
	
	public Request() {};
	
	public int getReqType() {
		return reqType;
	}

	public void setReqType(int reqType) {
		this.reqType = reqType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Request(int t, User u, Restaurant rst, Reservation rsv) {
		setUser(u);
		setRestaurant(rst);
		setReservation(rsv);
		setReqType(t);
	};
	
}
