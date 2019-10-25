package ristogo;

import javafx.beans.property.*;

  public class UserBean {
      
      private final SimpleIntegerProperty userId;
      private final SimpleStringProperty username;
      private final SimpleStringProperty password;
      private final SimpleBooleanProperty restaurateur;
 
      public UserBean(int i, String u, String p, boolean r) {
        userId = new SimpleIntegerProperty(i);
        username = new SimpleStringProperty(u);
        password = new SimpleStringProperty(p);
        restaurateur = new SimpleBooleanProperty(r);
      }
      
      public int getUserId() {return userId.get();}
      public String getUsername() { return username.get(); }
      public String getPassword() { return password.get(); }
      public boolean isRestauranteur() { return restaurateur.get();}
      
      public void setUserId(int i) {userId.set(i); };
      public void setUsername(String u) { username.set(u); }
      public void setPassword(String p) { password.set(p); }
      public void setRestaurateur(boolean r) { restaurateur.set(r);}
 } 