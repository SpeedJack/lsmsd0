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
	int idRisto = 0;
	int idOwner = 0;
	String ownerName = null;
	String name = null;
	Price cost = null;
	String city = null;
	String type = null;
	String address = null;
	String description = null;
	int seatsAvailable = 0;
	OpeningHour openAt = null;
	
	RestaurantBean getBean() {
		return new RestaurantBean(idOwner, idOwner, address, address, idOwner, address, address, address, idOwner, address);
	};
	
	public Restaurant() {};
	public Restaurant(RestaurantBean rb) {};
	
	public int getIdRisto() {
		return idRisto;
	}
	public void setIdRisto(int idRisto) {
		this.idRisto = idRisto;
	}
	public int getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Price getCost() {
		return cost;
	}
	public void setCost(Price cost) {
		this.cost = cost;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public OpeningHour getOpenAt() {
		return openAt;
	}
	public void setOpenAt(OpeningHour openAt) {
		this.openAt = openAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
