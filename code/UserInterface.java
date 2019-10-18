package application;
	
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
	
	String font = "Arial";
	double dimC = 18;
	String textColor = "FFFFFF";
	String backgroundColor = "FF421E";
	
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

    public void start(Stage stage) {
    	
		window = stage;
 	 
  //////////LOGIN INTERFACE /////////////////////////////////////////////////////////////////////////////////////////////////
    	 
    	Label title = new Label("Welcome to RistoGo!");
    	Label description = new Label("The application that allows you to book tables\n at your favorite restaurants!");
    	
    	title.setFont(Font.font(font, FontWeight.BOLD, dimC+2));
        title.setTextFill(Color.web(textColor));
        description.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        description.setTextFill(Color.web(textColor));
        title.setAlignment(Pos.CENTER_LEFT); 
        description.setAlignment(Pos.CENTER);
            
        Label name1 = new Label("Name: ");
        TextField name_field1 = new TextField();
        name1.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        name1.setTextFill(Color.web(textColor));
        name_field1.setFont(Font.font(font, dimC));
        name_field1.setMaxWidth(200);
      
        Label password1 = new Label("Password: ");
        TextField password_field1 = new TextField();
        password1.setFont(Font.font(font, FontWeight.NORMAL, dimC));
        password1.setTextFill(Color.web(textColor));
        password_field1.setFont(Font.font(font, dimC));
        password_field1.setMaxWidth(200);
        
        VBox boxField = new VBox(10);
        boxField.getChildren().addAll(title,description,name1, name_field1, password1, password_field1);
        boxField.setAlignment(Pos.CENTER);
        
    	Button login = new Button("Login");   
    	login.setOnAction((ActionEvent ev) -> {  
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
        
        //regScene = new Scene(new Group(boxTitle));
      
        HBox boxButton = new HBox(20);
        boxButton.getChildren().addAll(login, register);
        boxButton.setAlignment(Pos.CENTER); 
       
        
        VBox loginInterface = new VBox(20);
        loginInterface.getChildren().addAll(boxField, boxButton);
        loginInterface.setPrefSize(400, 400);
        loginInterface.setAlignment(Pos.CENTER_LEFT);
        
        loginScene = new Scene(new Group(loginInterface));
        loginScene.setFill(Color.web(backgroundColor));
        
 //////////////////////////REGISTER INTERFACE /////////////////////////////////////////////////////////////////
/*        Label name2 = new Label("Name: ");
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
*/    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        
        window.setTitle("RistoGo");
        window.setResizable(false);
        window.setScene(loginScene);
        window.show();      
        
    }
    
	public static void main(String[] args) {
		launch(args);
	}

}
