package ristogo;

import javafx.beans.property.*;

  public class RestaurantBean {
      private final SimpleIntegerProperty idRestaurant;
      private final SimpleIntegerProperty idUser;
      private final SimpleStringProperty nameUser;
      private final SimpleStringProperty name;
      private final SimpleStringProperty type;
      private final SimpleIntegerProperty price;
      private final SimpleStringProperty city;
      private final SimpleStringProperty address;
      private final SimpleStringProperty description;
      private final SimpleIntegerProperty seats;
      private final SimpleStringProperty opening;
 
      public RestaurantBean(int idR, int idU, String un, String n, String t, int p, String c, String a, String d, int s, String oa) {
        
        idRestaurant = new SimpleIntegerProperty(idR);
        idUser = new SimpleIntegerProperty(idU);
        nameUser = new SimpleStringProperty(un);
        name = new SimpleStringProperty(n);
        type = new SimpleStringProperty(t);
        seats = new SimpleIntegerProperty(s);
        city = new SimpleStringProperty(c);
        address = new SimpleStringProperty(a);
        description = new SimpleStringProperty(d);
        price = new SimpleIntegerProperty(p);
        opening = new SimpleStringProperty(oa);
      }
      
      public int getIdRestaurant() { return idRestaurant.get(); }
      public int getIdUser() { return idUser.get(); }
      public String getNameUser() {return nameUser.get();}
      public String getName() { return name.get(); }
      public String getType() { return type.get(); }
      public int getSeats() { return seats.get(); }
      public String getCity() { return city.get(); }
      public String getAddress() { return address.get(); }
      public String getDescription() { return description.get(); }
      public int getPrice() { return price.get(); }
      public String getOpening() { return opening.get(); }
      
      public void setIdRestaurant(int i) { idRestaurant.set(i); }
      public void setIdUser(int i) { idUser.set(i); }
      public void setNameUser(String n) { nameUser.set(n);}
      public void setName(String n) { name.set(n); }
      public void setType(String t) { type.set(t); }
      public void setSeats(int s) { seats.set(s); }
      public void setCity(String c) { city.set(c); }
      public void setAddress(String a) { address.set(a); }
      public void setDescription(String d) { description.set(d); }
      public void setPrice(int p) { price.set(p); }
      public void setOpening(String oa) { opening.set(oa); }
      
 }