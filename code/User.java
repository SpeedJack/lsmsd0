import javafx.beans.property.*;

  public class User {
      
      private final SimpleStringProperty username;
      private final SimpleStringProperty password;
      private final SimpleBooleanProperty restorateur;
 
      public User(String u, String p, boolean r) {
        username = new SimpleStringProperty(u);
        password = new SimpleStringProperty(p);
        restorateur = new SimpleBooleanProperty(r);
      }
 
      public String getUsername() { return username.get(); }
      public String getPassword() { return password.get(); }
      public boolean getRestorateur() { return restorateur.get(); }
      
      public void setUsername(String u) { username.set(u); }
      public void setPassword(String p) { password.set(p); }
      public void setRestorateur(boolean r) { restorateur.set(r); }
 } 