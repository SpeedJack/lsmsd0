package ristogo;

import javafx.beans.property.*;

  public class ReservationBean {
      
      private final SimpleIntegerProperty idReservation;
      private final SimpleIntegerProperty idClient;
      private final SimpleIntegerProperty idRestaurant;
      private final SimpleStringProperty date;
      private final SimpleStringProperty hour;
      private final SimpleIntegerProperty seats;
      private final SimpleStringProperty clientName;
      private final SimpleStringProperty restaurantName;

      public ReservationBean(int reservation, int client, int resturant, String d , String h, int s, String cn, String rn) {
    	clientName = new SimpleStringProperty(cn);
	restaurantName = new SimpleStringProperty(rn);
	idReservation = new SimpleIntegerProperty(reservation);
        idClient = new SimpleIntegerProperty(client);
        idRestaurant = new SimpleIntegerProperty(resturant);
        date = new SimpleStringProperty(d);
        seats = new SimpleIntegerProperty(s);
        hour = new SimpleStringProperty(h);
        
      }
      
      public int getReservation() {return idReservation.get();};
      public int getUser() { return idClient.get(); }
      public int getRestaurant() { return idRestaurant.get(); }
      public String getDate() { return date.get(); }
      public int getSeats() { return seats.get(); }
      public String getHour() { return hour.get(); }
      public String getClientName() { return clientName.get(); }
      public String getRestaurantName() { return restaurantName.get(); }
      
      public void setReservation(int reservation) {idReservation.set(reservation);};
      public void setUser(int client) { idClient.set(client); }
      public void setRestaurant(int resturant) { idRestaurant.set(resturant); }
      public void setDate(String d) { date.set(d); }
      public void setSeats(int s) { seats.set(s); }
      public void setHour(String h) { hour.set(h); }
      
  }