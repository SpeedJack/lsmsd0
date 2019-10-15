import javafx.beans.property.*;

  public class Restaurant {
      private final SimpleIntegerProperty id;
      private final SimpleStringProperty name;
      private final SimpleStringProperty type;
      private final SimpleIntegerProperty seats;
      private final SimpleStringProperty city;
      private final SimpleStringProperty address;
      private final SimpleStringProperty description;
      private final SimpleIntegerProperty price;
      private final SimpleStringProperty openingAt;
      private final SimpleStringProperty closingAt;
 
      public Restaurant(String n, String t, int s, String c, String a, String d, int p, String oa, String ca) {
        name = new SimpleStringProperty(n);
        type = new SimpleStringProperty(t);
        seats = new SimpleIntegerProperty(s);
        city = new SimpleStringProperty(c);
        address = new SimpleStringProperty(a);
        description = new SimpleStringProperty(d);
        price = new SimpleIntegerProperty(p);
        openingAt = new SimpleStringProperty(oa);
        closingAt = new SimpleStringProperty(ca);
        id = new SimpleIntegerProperty();
      }
      
      public int getId() {return id.get(); }
      public String getName() { return name.get(); }
      public String getType() { return type.get(); }
      public int getSeats() { return seats.get(); }
      public String getCity() { return city.get(); }
      public String getAddress() { return address.get(); }
      public String getDescription() { return description.get(); }
      public int getPrice() { return price.get(); }
      public String getOpeningAt() { return openingAt.get(); }
      public String getClosingAt() { return closingAt.get(); }
      
      public void setId(int i) {id.set(i);}
      public void setName(String n) { name.set(n); }
      public void setType(String t) { type.set(t); }
      public void setSeats(int s) { seats.set(s); }
      public void setCity(String c) { city.set(c); }
      public void setAddress(String a) { address.set(a); }
      public void setDescription(String d) { description.set(d); }
      public void setPrice(int p) { price.set(p); }
      public void setOpeningAt(String oa) { openingAt.set(oa); }
      public void setClosingAt(String ca) { closingAt.set(ca); }
      
 }