package ristogo;

import java.io.Serializable;

public class Reservation implements Serializable{
	int idRes;
	User customer;
	Restaurant restaurant;
	int seats;
	String date;
	OpeningHour resTime;
	
	public Reservation(int idRes, User customer, Restaurant restaurant, int seats, String date, OpeningHour resTime){
		this.idRes = idRes;
		this.customer = customer;
		this.restaurant = restaurant;
		this.seats = seats;
		this.date = date;
		this.resTime = resTime;
	};
	public Reservation(ReservationBean rb){
		//SUI BEAN NON CI SONO LE INFORMAZIONI PER GENERARE GLI OGGETTI
		//USER E RESTAURANT PER customer e restaurant
	};
	
	//VA CONVERTITO ENUM IN STRING
	public ReservationBean getBean() {
		return new ReservationBean(idRes, customer.getIdUser(), restaurant.getIdRisto(), date, resTime, seats, customer.getUsername(), restaurant.getName());
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
