package ristogo;
	


import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.geometry.*;
import java.time.*;


public class UserInterface extends Application {
	
	Stage window;
	Scene loginScene;
	Scene regScene;
	Scene tableInterface;
	
	String username = "Prova";
	int userID = 0000;
	
	String font = (new Configuration()).getFont();
	double dimC = (new Configuration()).getDimCharacter();
	String backgroundColor = (new Configuration()).getBackgroundColor();
	String textColor = (new Configuration()).getTextColor();
	
	
//////////START INTERFACE /////////////////////////////////////////////////////////////////////////////////////////////////
	public void start(Stage stage) {
		
		window = stage;
	
///Title Box		 
		Label title = new Label("Welcome to RistoGo!");
		title.setFont(Font.font(font, FontWeight.BOLD, dimC+5));
		title.setTextFill(Color.web(backgroundColor));
		title.setAlignment(Pos.CENTER_LEFT); 
		
		ImageView icon = new ImageView("resources/whiteLogo.png");
		icon.setFitHeight(50);
		icon.setFitWidth(50);
		
		HBox boxTitle = new HBox(20);
		boxTitle.getChildren().addAll(title,icon);
		boxTitle.setAlignment(Pos.CENTER);
////	
///Login Box  
		Label description = new Label("The application that allows you to book tables\n at your favorite restaurants!");
		description.setAlignment(Pos.CENTER_LEFT);
		description.setFont(Font.font(font, FontWeight.NORMAL, dimC+3));
		description.setTextFill(Color.web(backgroundColor));
		  
		  
		  Label name = new Label("Name: ");
		  TextField name_field = new TextField();
		  name.setFont(Font.font(font, FontWeight.NORMAL, dimC+3));
		  name.setTextFill(Color.web(backgroundColor));
		  name_field.setFont(Font.font(font, dimC));
		  name_field.setMaxWidth(200);
		
		  Label password = new Label("Password: ");
		  TextField password_field = new TextField();
		  password.setFont(Font.font(font, FontWeight.NORMAL, dimC+3));
		  password.setTextFill(Color.web(backgroundColor));
		  password_field.setFont(Font.font(font, dimC));
		  password_field.setMaxWidth(200);
		  
		//////////////////error/////////////////////////////////////////////////////     
		  Label error = new Label("Error: Login Failed. Retry");
		  error.setFont(Font.font(font, FontWeight.NORMAL, dimC+3));
		  error.setTextFill(Color.web(backgroundColor));
		  error.setStyle("-fx-background-color:   green;");
		  error.setVisible(false);
		///////////////////////////////////////////////////////////////////////////////
		  
		  VBox boxField = new VBox(10);
		  boxField.getChildren().addAll(boxTitle, description, name, name_field, password, password_field, error);
		  boxField.setAlignment(Pos.CENTER);
		  
			Button login = new Button("Login");   
			login.setOnAction((ActionEvent ev) -> { 
										        	try {
										        		error.setVisible(false);
														String n = name_field.getText();
														String p = password_field.getText();
											        	UserBean res = (UserBean)MessageHandler.sendRequest(MessageHandler.LOGIN, n, p);
											        	if(res.isRestaurateur()) {
											        		restaranteurInterface(window);
											        	}
											        	else if(!res.isRestaurateur()) {
											        		clientInterface(window);
											        	}
											        	else {
											        		error.setVisible(true);
											        	}
													}catch(NullPointerException e) {
														e.getMessage();
														//GESTIRE MESSAGGIO ERRORE
													}

													
		  									});
			
		  login.setFont(Font.font(font, FontWeight.BOLD, dimC+3));
		  login.setTextFill(Color.web(textColor));
		  login.setStyle("-fx-base: " + backgroundColor );  
		  
		  Button register = new Button("Register");   
		  register.setOnAction((ActionEvent ev) -> {
		  										registerInterface(window);
													});
		
		  register.setFont(Font.font(font, FontWeight.BOLD, dimC+3));
		  register.setTextFill(Color.web(textColor));
		  register.setStyle("-fx-base: " + backgroundColor);
		
		
		  HBox boxButton = new HBox(20);
		  boxButton.getChildren().addAll(login, register);
		  boxButton.setAlignment(Pos.CENTER); 

/////////
		  
		  VBox loginInterface = new VBox(20);
		  loginInterface.getChildren().addAll(boxField, boxButton);
		  loginInterface.setPrefSize(400, 400);
		  loginInterface.setAlignment(Pos.CENTER_LEFT);
		  
		  loginScene = new Scene(new Group(loginInterface));
		  loginScene.setFill(Color.web(textColor));
		  
		  window.setOnCloseRequest((WindowEvent ev) -> {
										MessageHandler.sendRequest(MessageHandler.EXIT);
									});
		  window.setTitle("RistoGo");
		  window.setResizable(false);
		  window.setScene(loginScene);
		  window.getIcons().add(new Image("resources/logo.png"));
		  window.show();      
		  
	}
	
	public static void main(String[] args) {
		launch(args);
	}

//////////////////////////////REGISTER INTERFACE ///////////////////////////////////////////////////////////////////////////	
	public void registerInterface(Stage window) {
		
		Label title = new Label("Sign in!");
    	title.setFont(Font.font(font, FontWeight.BOLD, dimC+5));
        title.setTextFill(Color.web(backgroundColor));
        title.setAlignment(Pos.CENTER_LEFT); 
		
		Label name = new Label("Name: ");
        TextField name_field = new TextField();
        name.setFont(Font.font(font, FontWeight.NORMAL, dimC+3));
        name.setTextFill(Color.web(backgroundColor));
        name_field.setFont(Font.font(font, dimC));
        name_field.setMaxWidth(200);
      
        Label password = new Label("Password: ");
        TextField password_field = new TextField();
        password.setFont(Font.font(font, FontWeight.NORMAL, dimC+3));
        password.setTextFill(Color.web(backgroundColor));
        password_field.setFont(Font.font(font, dimC+3));
        password_field.setMaxWidth(200);
        
        Label type = new Label("Type of User: ");
        ChoiceBox<String> cb = new ChoiceBox();
        cb.getItems().addAll("Client", "Restaurateur");
        type.setFont(Font.font(font, FontWeight.NORMAL, dimC+3));
        type.setTextFill(Color.web(backgroundColor));
        
//////////////////error/////////////////////////////////////////////////////     
        Label error = new Label("Error: Registration Failed. Retry with different username");
        error.setFont(Font.font(font, FontWeight.NORMAL, dimC+3));
        error.setTextFill(Color.web(backgroundColor));
        error.setStyle("-fx-background-color:   green;");
        error.setVisible(false);
///////////////////////////////////////////////////////////////////////////////
        
        Button register2 = new Button("Register");   
        register2.setOnAction((ActionEvent ev) -> {
										        	try {
														String n = name_field.getText();
														String p = password_field.getText();
														String t = cb.getValue();
											        	boolean  res = (boolean)MessageHandler.sendRequest(MessageHandler.REGISTRATION, n, p, t);
											        	if(res) {
											        		window.setScene(loginScene);
											        	}
											        	else {
											        		error.setVisible(true);
											        	}
													}catch(NullPointerException e) {
														e.getMessage();
														//GESTIRE MESSAGGIO ERRORE
													}
												});
   
        register2.setFont(Font.font(font, FontWeight.BOLD, dimC+3));
        register2.setTextFill(Color.web(textColor));
        register2.setStyle("-fx-base: " + backgroundColor);  
        
        VBox regInterface = new VBox(10);
        regInterface.getChildren().addAll(title,name,name_field, password, password_field, type, cb,error, register2);
        regInterface.setPrefSize(400, 400);
        regInterface.setAlignment(Pos.CENTER);
        
        regScene = new Scene(new Group(regInterface));
        regScene.setFill(Color.web(textColor));
        window.setScene(regScene);
        window.setOnCloseRequest((WindowEvent ev) -> {
										MessageHandler.sendRequest(MessageHandler.EXIT);
				});
    		
	}
////////////////////////CLIENT INTERFACE /////////////////////////////////////////////////////	
	public void clientInterface(Stage window) {	

//Left Interface
	//Title Box
		Label title = new Label("RistoGo");
		title.setFont(Font.font(font, FontWeight.BOLD, dimC+7));
		title.setTextFill(Color.web(textColor)); 
		
		ImageView icon = new ImageView("resources/logo.png");
		icon.setFitHeight(30);
		icon.setFitWidth(30);
		
		HBox boxTitle = new HBox(10);
		boxTitle.getChildren().addAll(title,icon);
		
		Label title2 = new Label("Welcome " + username + "!");
		title2.setFont(Font.font(font, FontWeight.NORMAL, dimC+4));
		title2.setTextFill(Color.web(textColor));
		
	//Book box
		
		Label subTitle1 = new Label("Book a table");
		Label l1 = new Label("To select a restaurant click on the table on the side" );
		subTitle1.setStyle("-fx-underline: true;");
		subTitle1.setFont(Font.font(font, FontWeight.BOLD, dimC+3));
		subTitle1.setTextFill(Color.web(textColor));
		l1.setFont(Font.font(font, FontWeight.NORMAL, dimC+1));
		l1.setTextFill(Color.web(textColor));

		
		Label name = new Label("Name of Restaurant: ");
		TextField name_f = new TextField();
		name.setFont(Font.font(font, FontWeight.BOLD, dimC));
		name.setTextFill(Color.web(textColor));
		name_f.setEditable(false);
		
		HBox name_box = new HBox(20);
		name_box.getChildren().addAll(name,name_f);
 
		    
		Label date = new Label("Date of Reservation: ");
		date.setFont(Font.font(font, FontWeight.BOLD, dimC));
		date.setTextFill(Color.web(textColor));

		DatePicker date_f = new DatePicker();
		date_f.setDayCellFactory(picker -> new DateCell() {   //Disable all past dates
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		
		HBox date_box = new HBox(20);
		date_box.getChildren().addAll(date,date_f);

		 
		
		Label hour = new Label("Booking Time: ");
		hour.setFont(Font.font(font, FontWeight.BOLD, dimC));
		hour.setTextFill(Color.web(textColor));

		ChoiceBox<String> hour_f = new ChoiceBox();
		hour_f.setMinSize(70, 25);
		hour_f.setMaxSize(70, 25);
		
		
		HBox hour_box = new HBox(20);
		hour_box.getChildren().addAll(hour,hour_f);

    	
    	Button check = new Button("Check");


    	check.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
    	check.setTextFill(Color.web(backgroundColor));
    	check.setStyle("-fx-base: " + textColor );
		
		
		Label seats = new Label("Seats: ");
		seats.setFont(Font.font(font, FontWeight.BOLD, dimC));
		seats.setTextFill(Color.web(textColor));

		ChoiceBox<Integer> seats_f = new ChoiceBox();
		seats_f.setDisable(true);
		
		HBox seats_box = new HBox(20);
		seats_box.getChildren().addAll(seats,seats_f);
    
		
		
		Button book = new Button("Book");
		book.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
		book.setTextFill(Color.web(backgroundColor));
		book.setStyle("-fx-base: " + textColor );
		book.setDisable(true);
		
		Button delRes = new Button("Del. Res.");
		delRes.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
		delRes.setTextFill(Color.web(backgroundColor));
		delRes.setStyle("-fx-base: " + textColor );
		delRes.setDisable(true);
		
		
		VBox boxBook = new VBox(20);
		boxBook.getChildren().addAll(subTitle1,l1,name_box, date_box, hour_box, check, seats_box, book, delRes);
        boxBook.setStyle("-fx-padding: 7;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 3;" + 
                "-fx-border-radius: 10;" + 
                "-fx-border-color: "+ textColor +";");
		
		VBox leftInterface = new VBox(30);
		leftInterface.getChildren().addAll(boxTitle,title2, boxBook);
		leftInterface.setPrefSize(400, 600);
        leftInterface.setStyle("-fx-padding: 7;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 3;" + 
                "-fx-border-radius: 10;" );
		
//Right Interface
		
		Label subTitle2 = new Label("List of Restaurant");
		subTitle2.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
		subTitle2.setTextFill(Color.web(textColor));
		subTitle2.setStyle("-fx-underline: true;");
		
	    TableViewRestaurant restaurant = new TableViewRestaurant();
	    restaurant.listRestaurant();

	        
	    Label description = new Label("Description: ");
		TextArea description_f = new TextArea();
		description.setFont(Font.font(font, FontWeight.BOLD, dimC-2));
		description.setTextFill(Color.web(textColor));
		description_f.setEditable(false);
		description_f.setMinSize(480, 100);
		description_f.setMaxSize(480, 100);
		
		
	    restaurant.setOnMouseClicked((e) -> {
	    	try {
	    		delRes.setDisable(true);
				name_f.setText(restaurant.getSelectionModel().getSelectedItem().getName());
				description_f.setText(restaurant.getSelectionModel().getSelectedItem().getDescription());
				hour_f.getItems().clear();
				if(restaurant.getSelectionModel().getSelectedItem().getOpening() == "Lunch&Dinner") {
					hour_f.getItems().addAll("Lunch", "Dinner");
				}
				else if(restaurant.getSelectionModel().getSelectedItem().getOpening() == "Lunch") {
					hour_f.getItems().add("Lunch");
				}
				else {
					hour_f.getItems().add("Dinner");
				}
	    	}catch(NullPointerException npe) {
	    		//do nothing
	    	}	
		});
	   
		HBox description_box = new HBox(20);
		description_box.getChildren().addAll(description,description_f);
		
		Label subTitle3 = new Label("My Reservation");
		subTitle3.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
		subTitle3.setTextFill(Color.web(textColor));
		subTitle3.setStyle("-fx-underline: true;");
		
	    TableViewReservation reservation = new TableViewReservation();
	    reservation.myReservations(false);
	    reservation.myReservations(false);
	    
	    reservation.setOnMouseClicked((e) -> {
	    		delRes.setDisable(false);
	    });
	    
	    
    	check.setOnAction((ActionEvent ev) -> {
												try {
													String n = name_f.getText();
													String d = date_f.getValue().toString();
													String h = hour_f.getValue();
													int s = (int)MessageHandler.sendRequest(MessageHandler.CHECK_SEATS, n, d, h);
										
													if(s>0) {
														for(int i=1; i<=s; i++) {
															seats_f.getItems().add(i);
															seats_f.setDisable(false);
															book.setDisable(false);
														}
														
													}
												}catch(NullPointerException e) {
													e.getMessage();
													//GESTIRE MESSAGGIO ERRORE
												}
										});

		book.setOnAction((ActionEvent ev) -> {
												try {
													String n = name_f.getText();
													String d = date_f.getValue().toString();
													String h = hour_f.getValue();
													int s = seats_f.getValue();
													boolean res = (boolean)MessageHandler.sendRequest(MessageHandler.RESERVATION, n, d, h, Integer.toString(s));
													if(res) {
														reservation.myReservations(false);
													}
													else {
														//errore
														
													}
												}catch(NullPointerException e) {
													e.getMessage();
													//GESTIRE MESSAGGIO ERRORE
												}
											});
		
		delRes.setOnAction((ActionEvent ev) -> {
												try {
													int idRes = reservation.getSelectionModel().getSelectedItem().getReservation();
													boolean res = (boolean)MessageHandler.sendRequest(MessageHandler.DELETE_RESERVATION, Integer.toString(idRes));
													if(res) {
														reservation.myReservations(false);
														delRes.setDisable(true);
													}
													else {
														//errore
														
													}
												}catch(NullPointerException e) {
													e.getMessage();
													//errore
												}
											});
		
		
		date_f.setOnAction((ActionEvent ev) -> { 
												seats_f.setDisable(true);
												book.setDisable(true);
												});
		hour_f.setOnAction((ActionEvent ev) -> { 
												seats_f.setDisable(true);
												book.setDisable(true);
												});
	    
	    VBox rightInterface = new VBox(10);
	    rightInterface.getChildren().addAll(subTitle2, restaurant, description_box, subTitle3, reservation);
	    rightInterface.setPrefSize(600, 600);
	    rightInterface.setStyle("-fx-padding: 7;" + 
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 3;" + 
	                "-fx-border-radius: 10;" );
	       
	    HBox clientInterface = new HBox(10);
	    clientInterface.getChildren().addAll(leftInterface, rightInterface);
	    clientInterface.setPrefSize(1000, 600);
	    
  
	    tableInterface = new Scene(new Group(clientInterface));
	    tableInterface.setFill(Color.web(backgroundColor));
	    window.setScene(tableInterface);
        window.setOnCloseRequest((WindowEvent ev) -> {
									MessageHandler.sendRequest(MessageHandler.EXIT);
				});
    
	}
	
/////////////////////////////////RESTAURANTEUR INTERFACE ///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void restaranteurInterface(Stage window) {
//Left Interface
		//Title Box
		Label title = new Label("RistoGo");
		title.setFont(Font.font(font, FontWeight.BOLD, dimC+7));
		title.setTextFill(Color.web(textColor)); 
		
		ImageView icon = new ImageView("resources/logo.png");
		icon.setFitHeight(30);
		icon.setFitWidth(30);
		
		HBox boxTitle = new HBox(10);
		boxTitle.getChildren().addAll(title,icon);
		
		Label title2 = new Label("Welcome " + username + "!");
		title2.setFont(Font.font(font, FontWeight.NORMAL, dimC+4));
		title2.setTextFill(Color.web(textColor));
		
		//Modify Box

		Label subTitle1 = new Label("Modify your Restaurant");
		subTitle1.setStyle("-fx-underline: true;");
		subTitle1.setFont(Font.font(font, FontWeight.BOLD, dimC+3));
		subTitle1.setTextFill(Color.web(textColor));

		Label name = new Label("Name: ");
		TextField name_f = new TextField();
		name.setFont(Font.font(font, FontWeight.BOLD, dimC));
		name.setTextFill(Color.web(textColor));
		
		HBox name_box = new HBox(20);
		name_box.getChildren().addAll(name,name_f);
 
		    
		Label type = new Label("Type: ");
		type.setFont(Font.font(font, FontWeight.BOLD, dimC));
		type.setTextFill(Color.web(textColor));
		ChoiceBox<String> type_f = new ChoiceBox();
		type_f.getItems().addAll("Pizza", "Chinese", "Mexican", "Italian", "SteakHouse");

		
		HBox type_box = new HBox(20);
		type_box.getChildren().addAll(type,type_f);

		
		Label cost = new Label("Cost:");
		cost.setFont(Font.font(font, FontWeight.BOLD, dimC));
		cost.setTextFill(Color.web(textColor));

		ChoiceBox<Integer> cost_f = new ChoiceBox();
		cost_f.getItems().addAll(1, 2, 3, 4, 5);
		
		HBox cost_box = new HBox(20);
		cost_box.getChildren().addAll(cost,cost_f);
		
		Label city = new Label("City: ");
		TextField city_f = new TextField();
		city.setFont(Font.font(font, FontWeight.BOLD, dimC));
		city.setTextFill(Color.web(textColor));
		
		HBox city_box = new HBox(20);
		city_box.getChildren().addAll(city,city_f);
		
		Label address = new Label("Address: ");
		TextField address_f = new TextField();
		address.setFont(Font.font(font, FontWeight.BOLD, dimC));
		address.setTextFill(Color.web(textColor));
		
		HBox address_box = new HBox(20);
		address_box.getChildren().addAll(address,address_f);
		
		Label desc = new Label("Description: ");
		TextArea desc_f = new TextArea();
		desc.setFont(Font.font(font, FontWeight.BOLD, dimC));
		desc.setTextFill(Color.web(textColor));
		desc_f.setMinSize(480, 100);
		desc_f.setMaxSize(480, 100);
		
		//HBox desc_box = new HBox(20);
		//desc_box.getChildren().addAll(desc,desc_f);
		
		
		Label seats = new Label("Seats: ");
		TextField seats_f = new TextField();
		seats.setFont(Font.font(font, FontWeight.BOLD, dimC));
		seats.setTextFill(Color.web(textColor));
		
		HBox seats_box = new HBox(20);
		seats_box.getChildren().addAll(seats,seats_f);
		
		Label hour = new Label("Hour:");
		hour.setFont(Font.font(font, FontWeight.BOLD, dimC));
		hour.setTextFill(Color.web(textColor));

		ChoiceBox<String> hour_f = new ChoiceBox();
		hour_f.getItems().addAll("Lunch", "Dinner", "Lunch&Dinner");
		
		HBox hour_box = new HBox(20);
		hour_box.getChildren().addAll(hour,hour_f);

    	
    	Button commit = new Button("Commit");


    	commit.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
    	commit.setTextFill(Color.web(backgroundColor));
    	commit.setStyle("-fx-base: " + textColor );
		

		
		
    	commit.setOnAction((ActionEvent ev) -> {
  
										});
		
		VBox boxModify = new VBox(20);
		boxModify.getChildren().addAll(subTitle1,name_box, type_box, cost_box, city_box, address_box, desc, desc_f, seats_box, hour_box, commit);
		boxModify.setStyle("-fx-padding: 7;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 3;" + 
                "-fx-border-radius: 10;" + 
                "-fx-border-color: "+ textColor +";");
		
		VBox leftInterface = new VBox(30);
		leftInterface.getChildren().addAll(boxTitle,title2, boxModify);
		leftInterface.setPrefSize(400, 600);
        leftInterface.setStyle("-fx-padding: 7;" + 
                "-fx-border-width: 2;" +
                "-fx-border-insets: 3;" + 
                "-fx-border-radius: 10;" );
        
        
//RightInterface
		Label subTitle2 = new Label("List of Reservations at your restaurant");
		subTitle2.setFont(Font.font(font, FontWeight.BOLD, dimC+1));
		subTitle2.setTextFill(Color.web(textColor));
		subTitle2.setStyle("-fx-underline: true;");
		
		
	    TableViewReservation reservation = new TableViewReservation();
	    reservation.myReservations(true);
	    
	   	Button refresh = new Button("Refresh");


	   	refresh.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
	   	refresh.setTextFill(Color.web(backgroundColor));
	   	refresh.setStyle("-fx-base: " + textColor );
		
    	commit.setOnAction((ActionEvent ev) -> {
  
										});
	    
	    VBox rightInterface = new VBox(10);
	    rightInterface.setAlignment(Pos.CENTER);
	    rightInterface.getChildren().addAll(subTitle2, reservation, refresh);
	    rightInterface.setPrefSize(600, 600);
	    rightInterface.setStyle("-fx-padding: 7;" + 
	                "-fx-border-width: 2;" +
	                "-fx-border-insets: 3;" + 
	                "-fx-border-radius: 10;" );
			
		
        HBox restaranteurInterface = new HBox(10);
        restaranteurInterface.getChildren().addAll(leftInterface, rightInterface);
        restaranteurInterface.setPrefSize(1000, 600);
	    
  
	    tableInterface = new Scene(new Group(restaranteurInterface));
	    tableInterface.setFill(Color.web(backgroundColor));
	    window.setScene(tableInterface);
        window.setOnCloseRequest((WindowEvent ev) -> {
        										MessageHandler.sendRequest(MessageHandler.EXIT);
        											});
		
		
	}
	

}
