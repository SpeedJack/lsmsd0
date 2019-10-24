package ristogo;

import java.io.Serializable;

enum Price{
	ECONOMIC,
	LOW,
	MIDDLE,
	HIGH,
	LUXURY
}

enum OpeningHour{
	LUNCH,
	DINNER,
	ALWAYS;
	
}

public class Restaurant implements Serializable{
	int idRisto;
	int idOwner;
	String ownerName;
	String name;
	String type;
	Price cost;
	String city;
	String address;
	String description;
	int seatsAvailable;
	OpeningHour openAt;
	
	//VA CONVERTITO ENUM IN STRING
	RestaurantBean getBean() {
		return new RestaurantBean(idRisto, idOwner, ownerName, name, type, cost, city, address, description, seatsAvailable, openAt);
	};
	
	public Restaurant(int idRisto, int idOwner, String ownerName, String name, Price cost, String city, String type, String address, String description, int seatsAvailable, OpeningHour openAt ) {
		this.idRisto = idRisto;
		this.idOwner = idOwner;
		this.ownerName = ownerName;
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.city = city;
		this.address = address;
		this.description = description;
		this.seatsAvailable = seatsAvailable;
		this.openAt = openAt;
		
	};
	public Restaurant(RestaurantBean rb) {
		this.idRisto = rb.getIdRestaurant();
		this.idOwner = rb.getIdUser();
		this.ownerName = rb.getNameUser();
		this.name = rb.getName();
		this.type = rb.getType();
		this.cost = rb.getPrice();
		this.city = rb.getCity();
		this.address = rb.getAddress();
		this.description = rb.getDescription();
		this.seatsAvailable = rb.getSeats();
		this.openAt = rb.getOpening();
	};
	
	public int getIdRisto() {return idRisto;}
	public int getIdOwner() {return idOwner;}
	public String getOwnerName() {return ownerName;}
	public String getName() {return name;}
	public Price getCost() {return cost;}
	public String getCity() {return city;}
	public String getAddress() {return address;}
	public String getDescription() {return description;}
	public int getSeatsAvailable() {return seatsAvailable;}
	public OpeningHour getOpenAt() {return openAt;}
	public String getType() {return type;}

	public void setIdRisto(int idRisto) {this.idRisto = idRisto;}
	public void setIdOwner(int idOwner) {this.idOwner = idOwner;}
	public void setOwnerName(String ownerName) {this.ownerName = ownerName;}
	public void setName(String name) {this.name = name;}
	public void setCost(Price cost) {this.cost = cost;}
	public void setCity(String city) {this.city = city;}
	public void setAddress(String address) {this.address = address;}
	public void setDescription(String description) {this.description = description;}
	public void setSeatsAvailable(int seatsAvailable) {this.seatsAvailable = seatsAvailable;}
	public void setOpenAt(OpeningHour openAt) {this.openAt = openAt;}
	public void setType(String type) {this.type = type;}
	
}
