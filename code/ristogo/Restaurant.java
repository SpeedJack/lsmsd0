package ristogo;



enum OpeningHour{
	LUNCH,
	DINNER,
	ALWAYS;	
}

public class Restaurant{
	int idRisto;
	int idOwner;
	String name;
	String type;
	int cost;
	String city;
	String address;
	String description;
	int seatsAvailable;
	OpeningHour openAt;
	
	//VA CONVERTITO ENUM IN STRING
	RestaurantBean getBean() {
		if(name == null) {
			return new RestaurantBean(idRisto, idOwner, "", "", 0, "", "", "", 0, null);
		}
		return new RestaurantBean(idRisto, idOwner, name, type, cost, city, address, description, seatsAvailable, openAt.toString());
	};
	
	public Restaurant() {
		idRisto = 0;
		idOwner = 0;
		name = null;
		type = null;
		cost = 1;
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
		this.cost = cost;
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
		this.cost = rb.getPrice();
		this.city = rb.getCity();
		this.address = rb.getAddress();
		this.description = rb.getDescription();
		this.seatsAvailable = rb.getSeats();
		this.openAt = OpeningHour.valueOf(rb.getOpening());
	};
	

	
	public int getIdRisto() {return idRisto;}
	public int getIdOwner() {return idOwner;}
	public String getName() {return name;}
	public int getCost() {return cost;}
	public String getCity() {return city;}
	public String getAddress() {return address;}
	public String getDescription() {return description;}
	public int getSeatsAvailable() {return seatsAvailable;}
	public OpeningHour getOpenAt() {return openAt;}
	public String getType() {return type;}

	public void setIdRisto(int idRisto) {this.idRisto = idRisto;}
	public void setIdOwner(int idOwner) {this.idOwner = idOwner;}
	public void setName(String name) {this.name = name;}
	public void setCost(int cost) {this.cost = cost;}
	public void setCity(String city) {this.city = city;}
	public void setAddress(String address) {this.address = address;}
	public void setDescription(String description) {this.description = description;}
	public void setSeatsAvailable(int seatsAvailable) {this.seatsAvailable = seatsAvailable;}
	public void setOpenAt(OpeningHour openAt) {this.openAt = openAt;}
	public void setType(String type) {this.type = type;}
	
}
