package ristogo;
	
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class UserInterface extends Application {
	
	Stage window;
	Scene loginScene;
	Scene regScene;
	Scene tableInterface;
	
	String font = "Arial";
	double dimC = 18;
	String textColor = "FFFFFF";
	String backgroundColor = "FF421E";
//////////////////////////////REGISTER INTERFACE ///////////////////////////////////////////////////////////////////////////	
	public void registerInterface(Stage window) {
		
		Label name2 = new Label("Fill out the form to register!!\n\nName: ");
        TextField name_field2 = new TextField();
        name2.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        name2.setTextFill(Color.web(textColor));
        name_field2.setFont(Font.font(font, dimC));
        name_field2.setMaxWidth(200);
      
        Label password2 = new Label("Password: ");
        TextField password_field2 = new TextField();
        password2.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        password2.setTextFill(Color.web(textColor));
        password_field2.setFont(Font.font(font, dimC));
        password_field2.setMaxWidth(200);
        
        Label type = new Label("Type of User: ");
        ChoiceBox<String> cb = new ChoiceBox();
        cb.getItems().addAll("Client", "Restaurateur");
        type.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        type.setTextFill(Color.web(textColor));
        
        Button register2 = new Button("Register");   
        register2.setOnAction((ActionEvent ev) -> {
												window.setScene(loginScene);
												});
   
        register2.setFont(Font.font(font, FontWeight.BOLD, dimC));
        register2.setTextFill(Color.web(backgroundColor));
        register2.setStyle("-fx-base: " + textColor);  
        
        VBox regInterface = new VBox(10);
        regInterface.getChildren().addAll(name2,name_field2, password2, password_field2, type, cb, register2);
        regInterface.setPrefSize(400, 400);
        regInterface.setAlignment(Pos.CENTER);
        
        regScene = new Scene(new Group(regInterface));
        regScene.setFill(Color.web(backgroundColor));
        window.setScene(regScene);
    		
	}
////////////////////////RESTAURANT LIST INTERFACE /////////////////////////////////////////////////////	
	public void restaurantInterface(Stage window) {	
		
    	Label title = new Label("This is the list of our restaurants!");
    	Label description = new Label("Use the table to see all the details of restaurants.\n"
    			+ "Press 'Book' to book a table in a restaurant.\n"
    			+ "Press 'MyRes' to see your reservations.");
    	
    	title.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
        title.setTextFill(Color.web(textColor));
        description.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        description.setTextFill(Color.web(textColor));
        title.setAlignment(Pos.CENTER_LEFT); 
        description.setAlignment(Pos.CENTER);
        
	    TableViewRestaurant resturant = new TableViewRestaurant();
	    
	    
	    Button book = new Button("Book");
    	book.setOnAction((ActionEvent ev) -> { 
			//inserire azioni bottone book
							});
    	book.setFont(Font.font(font, FontWeight.BOLD, dimC));
    	book.setTextFill(Color.web(backgroundColor));
    	book.setStyle("-fx-base: " + textColor );
	    
	    Button myres = new Button("MyRes");
    	myres.setOnAction((ActionEvent ev) -> { 
			//inserire azioni bottone myres
							});
    	myres.setFont(Font.font(font, FontWeight.BOLD, dimC));
    	myres.setTextFill(Color.web(backgroundColor));
    	myres.setStyle("-fx-base: " + textColor );
    	
    	HBox boxButton = new HBox(20);
    	boxButton.getChildren().addAll(book,myres);
    	boxButton.setAlignment(Pos.CENTER); 
	    
	    
	    
	    VBox res = new VBox(10);
	    res.getChildren().addAll(title, description, resturant, boxButton);
	    res.setPrefSize(900, 500);
	    res.setAlignment(Pos.CENTER);
	    
	    tableInterface = new Scene(new Group(res));
	    tableInterface.setFill(Color.web(backgroundColor));
	    window.setScene(tableInterface);
    
	}
	
///////////////////////////BOOK INTERFACE/////////////////////////////////////////////////////////////////////////////////
	
	public void bookInterface(Stage window) {
		
		Label title = new Label("Book your table!");
    	Label description = new Label("Fill the following form to have your reservation\n");
    	title.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
        title.setTextFill(Color.web(textColor));
        description.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        description.setTextFill(Color.web(textColor));
        title.setAlignment(Pos.CENTER_LEFT); 
        description.setAlignment(Pos.CENTER);
        
        
        Label name = new Label("Name of Restaurant: ");
        TextField name_field = new TextField();
        
        Label date = new Label("Date of Reservation: ");
        DatePicker date_field = new DatePicker();
        
        /*Prelevare valore da DP
         * LocalDate data = date_field.getValue();
         */
        
        Label hour = new Label("Booking Time: Lunch or Dinner?: ");
        ChoiceBox<String> cb = new ChoiceBox();
        cb.getItems().addAll("Lunch", "Dinner");
        
        Label seats = new Label("Seats: ");
        ChoiceBox<String> s = new ChoiceBox();
        s.getItems().addAll("Lunch", "Dinner");
        
		
		
	}
    
//////////START INTERFACE /////////////////////////////////////////////////////////////////////////////////////////////////
    public void start(Stage stage) {
    	
		window = stage;
 	 
    	 
    	Label title = new Label("Welcome to RistoGo!");
    	Label description = new Label("The application that allows you to book tables\n at your favorite restaurants!");
    	
    	title.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
        title.setTextFill(Color.web(textColor));
        description.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        description.setTextFill(Color.web(textColor));
        title.setAlignment(Pos.CENTER_LEFT); 
        description.setAlignment(Pos.CENTER);
            
        Label name = new Label("Name: ");
        TextField name_field = new TextField();
        name.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        name.setTextFill(Color.web(textColor));
        name_field.setFont(Font.font(font, dimC));
        name_field.setMaxWidth(200);
      
        Label password = new Label("Password: ");
        TextField password_field = new TextField();
        password.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        password.setTextFill(Color.web(textColor));
        password_field.setFont(Font.font(font, dimC));
        password_field.setMaxWidth(200);
        
   //////////////////error/////////////////////////////////////////////////////     
        Label error = new Label("Error");
        error.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        error.setTextFill(Color.web(textColor));
        error.setStyle("-fx-background-color:   green;");
        error.setVisible(false);
  ///////////////////////////////////////////////////////////////////////////////
        
        VBox boxField = new VBox(10);
        boxField.getChildren().addAll(title,description,name, name_field, password, password_field, error);
        boxField.setAlignment(Pos.CENTER);
        
    	Button login = new Button("Login");   
    	login.setOnAction((ActionEvent ev) -> { 
    						restaurantInterface(window);
    						error.setVisible(true);
    						
        					//inserire azioni bottone login
        									});
    	
        login.setFont(Font.font(font, FontWeight.BOLD, dimC));
        login.setTextFill(Color.web(backgroundColor));
        login.setStyle("-fx-base: " + textColor );  
        
        Button register = new Button("Register");   
        register.setOnAction((ActionEvent ev) -> {
        										registerInterface(window);
												//window.setScene(regScene);
												});
   
        register.setFont(Font.font(font, FontWeight.BOLD, dimC));
        register.setTextFill(Color.web(backgroundColor));
        register.setStyle("-fx-base: " + textColor);
        

      
        HBox boxButton = new HBox(20);
        boxButton.getChildren().addAll(login, register);
        boxButton.setAlignment(Pos.CENTER); 
       
        
        VBox loginInterface = new VBox(20);
        loginInterface.getChildren().addAll(boxField, boxButton);
        loginInterface.setPrefSize(400, 400);
        loginInterface.setAlignment(Pos.CENTER_LEFT);
        
        loginScene = new Scene(new Group(loginInterface));
        loginScene.setFill(Color.web(backgroundColor));
        
        
        window.setTitle("RistoGo");
        window.setResizable(false);
        window.setScene(loginScene);
        window.show();      
        
    }
    
	public static void main(String[] args) {
		launch(args);
	}

}
