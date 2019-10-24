package ristogo;


import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.*;
import javafx.util.*;



public class TableViewRestaurant extends TableView<RestaurantBean> {
	 final ObservableList<RestaurantBean> restaurantList;
	 private TableColumn nameColumn;
	 private TableColumn typeColumn;
	 private TableColumn priceColumn;
	 private TableColumn cityColumn;
	 private TableColumn addressColumn;
	 
		 
	  public TableViewRestaurant () {
		    setEditable(true);
		    setFixedCellSize(35);
		    setMaxHeight((new Configuration()).getnumberRowsDisplayable()*getFixedCellSize());
		    setMinWidth(600);
		    setMaxWidth(600);
		    String font = (new Configuration()).getFont();
		    String textColor = (new Configuration()).getTextColor();
		    String backgroundColor = (new Configuration()).getBackgroundColor();
		    
		    
		    nameColumn = new TableColumn("Name");
		    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		    nameColumn.setStyle("-fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    
		    typeColumn = new TableColumn("Type");
		    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		    typeColumn.setStyle(" -fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    priceColumn = new TableColumn("Price");
		    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		    priceColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    cityColumn = new TableColumn("City");
		    cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
		    cityColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    
		    addressColumn = new TableColumn("Address");
		    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
		    addressColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    
		    nameColumn.setMinWidth(150); nameColumn.setMaxWidth(150);
		    typeColumn.setMinWidth(100); typeColumn.setMaxWidth(100);
		    priceColumn.setMinWidth(50); priceColumn.setMaxWidth(50);
		    cityColumn.setMinWidth(100); cityColumn.setMaxWidth(100);
		    addressColumn.setMinWidth(200); addressColumn.setMaxWidth(200);

		    
		    getColumns().addAll(nameColumn, typeColumn, priceColumn, cityColumn, addressColumn);
		    restaurantList = FXCollections.observableArrayList(); //"Costruttore"
		    setItems(restaurantList); //ATTACCA ALLA TABLEVIEW I DATI LISTA RISTORANTI
		    
		    RestaurantBean res = new RestaurantBean(0000, 0000, "aaaa", "Pizza", 4, "pisa", "bbbb", "AAAAA", 10, "Lunch");
		    RestaurantBean res1 = new RestaurantBean(0000, 0000, "bbbb", "Pizza", 4, "pisa", "bbbb", "DDDDD", 10, "Dinner");
		    restaurantList.clear();
		    restaurantList.addAll(res, res1);
		  }
	  
	  
	  
/*	 public void updateTable () {
		  restaurantList.clear();
		  restaurantList.addAll(RequestHandler.listRestaurant());
	  }
*/	 
	 
}
