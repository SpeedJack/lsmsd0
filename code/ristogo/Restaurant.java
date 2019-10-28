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
		return new RestaurantBean(idRisto, idOwner, name, type, cost.ordinal(), city, address, description, seatsAvailable, openAt.toString());
	};
	
	public Restaurant() {
		idRisto = 0;
		idOwner = 0;
		name = null;
		type = null;
		cost = null;
		city = null;
		address = null;
		description = null;
		seatsAvailable = 0;
		openAt = null;		
	};
	
	public Restaurant(int idRisto, int idOwner, String name, String type, int cost, String city, String address, String description, int seatsAvailable, String openAt ) {
		this.idRisto = idRisto;
		this.idOwner = idOwner;
		this.name = name;
		this.type = type;
		this.cost = Price.values()[cost-1];
		this.city = city;
		this.address = address;
		this.description = description;
		this.seatsAvailable = seatsAvailable;
		this.openAt = OpeningHour.valueOf(openAt);		
	};
        
	public Restaurant(RestaurantBean rb) {
		this.idRisto = rb.getIdRestaurant();
		this.idOwner = rb.getIdUser();
		this.name = rb.getName();
		this.type = rb.getType();
		this.cost = Price.values()[rb.getPrice()];
		this.city = rb.getCity();
		this.address = rb.getAddress();
		this.description = rb.getDescription();
		this.seatsAvailable = rb.getSeats();
		this.openAt = OpeningHour.valueOf(rb.getOpening());
	};
	

	
	public int getIdRisto() {return idRisto;}
	public int getIdOwner() {return idOwner;}
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
	public void setName(String name) {this.name = name;}
	public void setCost(Price cost) {this.cost = cost;}
	public void setCity(String city) {this.city = city;}
	public void setAddress(String address) {this.address = address;}
	public void setDescription(String description) {this.description = description;}
	public void setSeatsAvailable(int seatsAvailable) {this.seatsAvailable = seatsAvailable;}
	public void setOpenAt(OpeningHour openAt) {this.openAt = openAt;}
	public void setType(String type) {this.type = type;}
	
}
