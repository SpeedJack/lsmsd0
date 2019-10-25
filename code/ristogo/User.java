package ristogo;

public class User {
	int idUser = 0;
	String password = null;
	String username = null;
	boolean restaurateur = false;
	
	public User(){};
	public User(int id, String un, String p, boolean r){
		this.idUser = id;
		this.password = p;
		this.username = un;
		this.restaurateur = r;
	};
	
	public boolean isRestaurateur() {
		return restaurateur;
	}
	public void setRestaurateur(boolean restaurateur) {
		this.restaurateur = restaurateur;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
