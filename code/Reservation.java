import javafx.beans.property.*;

  public class Reservation {
      
      private final SimpleStringProperty username;
      private final SimpleIntegerProperty idRestaurant;
      private final SimpleStringProperty date;
      private final SimpleIntegerProperty seats;
      private final SimpleStringProperty hour;
 
      public Reservation(String u, int r, String d, int s, String h) {
        username = new SimpleStringProperty(u);
        idRestaurant = new SimpleIntegerProperty(r);
        date = new SimpleStringProperty(d);
        seats = new SimpleIntegerProperty(s);
        hour = new SimpleStringProperty(h);
      }
 
      public String getUsername() { return username.get(); }
      public int getRestaurant() { return idRestaurant.get(); }
      public String getDate() { return date.get(); }
      public int getSeats() { return seats.get(); }
      public String getHour() { return hour.get(); }
      
      public void setUsername(String u) { username.set(u); }
      public void setRestaurant(int r) { idRestaurant.set(r); }
      public void setDate(String d) { date.set(d); }
      public void setSeats(int s) { seats.set(s); }
      public void setHour(String h) { hour.set(h); }
      
      
  }