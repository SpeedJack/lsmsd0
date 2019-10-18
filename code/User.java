package application;

import javafx.beans.property.*;

  public class User {
      
      private final SimpleIntegerProperty userId;
      private final SimpleStringProperty username;
      private final SimpleStringProperty password;
 
      public User(int i, String u, String p) {
        userId = new SimpleIntegerProperty(i);
        username = new SimpleStringProperty(u);
        password = new SimpleStringProperty(p);
      }
      
      public int getUserId() {return userId.get();}
      public String getUsername() { return username.get(); }
      public String getPassword() { return password.get(); }
      
      public void setUserId(int i) {userId.set(i); };
      public void setUsername(String u) { username.set(u); }
      public void setPassword(String p) { password.set(p); }
 } 