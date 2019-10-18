package application;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;



public class TableViewRestaurant extends TableView<Restaurant> {
	 private final ObservableList<Restaurant> resturantList;
	 private TableColumn nameColumn;
	 private TableColumn typeColumn;
	 private TableColumn priceColumn;
	 private TableColumn cityColumn;
	 private TableColumn addressColumn;
	 private TableColumn descriptionColumn;
	 
		 
	  public TableViewRestaurant () {
		    //setEditable(true);
		    setFixedCellSize(35);
		    setMaxHeight((new Configuration()).getnumberRowsDisplayable()*getFixedCellSize());
		    setMinWidth(850);
		    setMaxWidth(850);
		    String font = (new Configuration()).getFont();
		    String textColor = (new Configuration()).getTextColor();
		    
		    
		    nameColumn = new TableColumn("Name");
		    nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		    nameColumn.setStyle("-fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    
		    typeColumn = new TableColumn("Type");
		    typeColumn.setCellValueFactory(new PropertyValueFactory<>("genere"));
		    typeColumn.setStyle(" -fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    priceColumn = new TableColumn("Price");
		    priceColumn.setCellValueFactory(new PropertyValueFactory<>("costo"));
		    priceColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    cityColumn = new TableColumn("City");
		    cityColumn.setCellValueFactory(new PropertyValueFactory<>("citta"));
		    cityColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    
		    addressColumn = new TableColumn("Address");
		    addressColumn.setCellValueFactory(new PropertyValueFactory<>("via"));
		    addressColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    descriptionColumn = new TableColumn("Description");
		    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
		    descriptionColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    
		    nameColumn.setMinWidth(150); nameColumn.setMaxWidth(150);
		    typeColumn.setMinWidth(150); typeColumn.setMaxWidth(100);
		    priceColumn.setMinWidth(100); priceColumn.setMaxWidth(100);
		    cityColumn.setMinWidth(150); cityColumn.setMaxWidth(150);
		    addressColumn.setMinWidth(150); addressColumn.setMaxWidth(150);
		    descriptionColumn.setMinWidth(150); descriptionColumn.setMaxWidth(150); 

		    
		    getColumns().addAll(nameColumn, typeColumn, priceColumn, cityColumn, addressColumn, descriptionColumn);
		    resturantList = FXCollections.observableArrayList(); //"Costruttore"
		    setItems(resturantList); //ATTACCA ALLA TABLEVIEW I DATI LISTA RISTORANTI
		  }  
	 
	 
}
