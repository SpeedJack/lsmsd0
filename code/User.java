import javafx.beans.property.*;

  public class User {
      
      private final SimpleStringProperty username;
      private final SimpleStringProperty password;
      private final SimpleStringProperty type;
 
      public User(String u, String p, String r) {
        username = new SimpleStringProperty(u);
        password = new SimpleStringProperty(p);
        type = new SimpleStringProperty(r);
      }
 
      public String getUsername() { return username.get(); }
      public String getPassword() { return password.get(); }
      public String getType() { return type.get(); }
      
      public void setUsername(String u) { username.set(u); }
      public void setPassword(String p) { password.set(p); }
      public void setType(String r) { type.set(r); }
 } 