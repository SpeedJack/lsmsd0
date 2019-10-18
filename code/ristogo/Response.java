package ristogo;
import java.util.List;

public class Response {
	private boolean success;
	private int resType;
	private List<Restaurant> restaurants;
	private List<Reservation> reservations;
	private User user;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getResType() {
		return resType;
	}
	public void setResType(int resType) {
		this.resType = resType;
	}
	public String getResMessage() {
		return resMessage;
	}
	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Response(boolean success, int resType, String resMessage, List<Restaurant> restaurants,
			List<Reservation> reservations, User user) {
		super();
		this.success = success;
		this.resType = resType;
		this.resMessage = resMessage;
		this.restaurants = restaurants;
		this.reservations = reservations;
		this.user = user;
	}
	public Response() {
		super();
	}
		
}
