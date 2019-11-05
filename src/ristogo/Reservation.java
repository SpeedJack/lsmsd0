package ristogo;


public class Reservation {
	int idRes;
	User customer;
	Restaurant restaurant;
	String date;
	OpeningHour resTime;
        int seats;
	
	public Reservation() {
		this.idRes = 0;
		this.customer = null;
		this.restaurant = null;
		this.seats = 0;
		this.date = null;
		this.resTime = null;
	}
	
	public Reservation(int idRes, User customer, Restaurant restaurant, String date, String resTime, int seats){
		this.idRes = idRes;
		this.customer = customer;
		this.restaurant = restaurant;
		this.seats = seats;
		this.date = date;
		this.resTime = OpeningHour.valueOf(resTime);
	};
//	public Reservation(ReservationBean rb){
////		this.idRes = rb.getReservation();
////		this.customer = UserSession.getUser();
////		this.restaurant = new Restaurant();
////		this.restaurant.setName(rb.getRestaurantName());
////		this.seats = 
//	};
	
	//VA CONVERTITO ENUM IN STRING
	public ReservationBean getBean() {
		return new ReservationBean(idRes, customer.getIdUser(), restaurant.getIdRisto(), date, resTime.toString(), seats, customer.getUsername(), restaurant.getName());
	};
	
	public int getIdRes() {return idRes;}
	public User getCustomer() {return customer;}
	public Restaurant getRestaurant() {return restaurant;}
	public int getSeats() {return seats;}
	public String getDate() {return date;}
	public OpeningHour getResTime() {return resTime;}
	
	public void setIdRes(int idRes) {this.idRes = idRes;}
	public void setCustomer(User customer) {this.customer = customer;}
	public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}
	public void setSeats(int seats) {this.seats = seats;}
	public void setDate(String date) {this.date = date;}
	public void setResTime(OpeningHour resTime) {this.resTime = resTime;}
}
