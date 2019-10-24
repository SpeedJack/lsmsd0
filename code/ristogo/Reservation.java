package ristogo;

import java.io.Serializable;

public class Reservation implements Serializable{
	int idRes = 0;
	User customer = null;
	Restaurant restaurant = null;
	int seats = 0;
	String date = null;
	OpeningHour resTime = null;
	
	public int getIdRes() {
		return idRes;
	}
	public void setIdRes(int idRes) {
		this.idRes = idRes;
	}
	public Reservation(){};
	public Reservation(ReservationBean rb){};
	public ReservationBean getBean() {return new ReservationBean(seats, seats, seats, date, date, seats, date, date) ;};
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public OpeningHour getResTime() {
		return resTime;
	}
	public void setResTime(OpeningHour resTime) {
		this.resTime = resTime;
	}
}
