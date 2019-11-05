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
	public Response(boolean success, int resType, List<Restaurant> restaurants,
			List<Reservation> reservations, User user) {
		setSuccess(success);
		setResType(resType);
		setRestaurants(restaurants);
		setReservations(reservations);
		setUser(user);		
	}
	
	public Response() {	};
		
}
