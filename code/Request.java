

import javafx.beans.property.*;
import java.util.*;

class Request {
	private SimpleIntegerProperty reqType;
	private SimpleStringProperty reqMessage;
	private List<User> users;
	private List<Restaurant> restaurants;
	private List<Reservation> reservations;
	public Request(int t, String m, List<User> lu, List<Restaurant> lrst, List<Reservation> lrsv) {
		setUsers(lu);
		setRestaurants(lrst);
		setReservations(lrsv);
		setType(t);
		setMessage(m);
		};
	public void setType(int t) {reqType = new SimpleIntegerProperty(t);};
	public void setMessage(String m) {reqMessage = new SimpleStringProperty(m);};
	public void setUsers(List<User> u) {users = new ArrayList<User>(u);};
	public void setRestaurants(List<Restaurant> r) {restaurants = new ArrayList<Restaurant>(r);};
	public void setReservations(List<Reservation> r) {reservations = new ArrayList<Reservation>(r);};
	public List<User> getUsers(){return users;};
	public List<Restaurant> getRestaurants(){return restaurants;};
	public List<Reservation> getReservations(){return reservations;};
}
