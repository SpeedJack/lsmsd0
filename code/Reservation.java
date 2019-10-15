import javafx.beans.property.*;

  public class Reservation {
      
      private final SimpleIntegerProperty idReservation;
      private final SimpleIntegerProperty idUser;
      private final SimpleIntegerProperty idRestaurant;
      private final SimpleStringProperty date;
      private final SimpleIntegerProperty seats;
      private final SimpleStringProperty hour;
 
      public Reservation(int u, int r, String d, int s, String h) {
        idUser = new SimpleIntegerProperty(u);
        idRestaurant = new SimpleIntegerProperty(r);
        date = new SimpleStringProperty(d);
        seats = new SimpleIntegerProperty(s);
        hour = new SimpleStringProperty(h);
        idReservation = new SimpleIntegerProperty();
      }
      
      public int getReservation() {return idReservation.get();};
      public int getUser() { return idUser.get(); }
      public int getRestaurant() { return idRestaurant.get(); }
      public String getDate() { return date.get(); }
      public int getSeats() { return seats.get(); }
      public String getHour() { return hour.get(); }
      
      public void setReservation(int r) {idReservation.set(r);};
      public void setUser(int u) { idUser.set(u); }
      public void setRestaurant(int r) { idRestaurant.set(r); }
      public void setDate(String d) { date.set(d); }
      public void setSeats(int s) { seats.set(s); }
      public void setHour(String h) { hour.set(h); }
      
      
  }