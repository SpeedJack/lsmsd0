package application;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;



public class TableViewResturant extends TableView<Restaurant> {
	 private final ObservableList<Restaurant> resturantList;
	 private TableColumn nameColumn;
	 private TableColumn typeColumn;
	 private TableColumn priceColumn;
	 private TableColumn cityColumn;
	 private TableColumn addressColumn;
	 private TableColumn descriptionColumn;
	 
	 int numberRowsDisplayable = 7;
	 String font = "Arial";
	 
	 
	  public TableViewResturant () {
		    //setEditable(true);
		    //setFixedCellSize(35);
		    setMaxHeight(numberRowsDisplayable*getFixedCellSize());
		    setMinWidth(575);    
		    String font = (new Configuration()).getFont();
		    
		    
		    nameColumn = new TableColumn("Name");
		    nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		    nameColumn.setStyle("-fx-font-family: " + font + "; -fx-font-size: " + ((new Configuration()).getDimCharacter() -3) + "; -fx-color: #FF421E;");
		    
		    typeColumn = new TableColumn("Type");
		    typeColumn.setCellValueFactory(new PropertyValueFactory<>("genere"));
		    typeColumn.setStyle(" -fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: #FF421E;");
		    
		    priceColumn = new TableColumn("Price");
		    priceColumn.setCellValueFactory(new PropertyValueFactory<>("costo"));
		    priceColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: #FF421E;");
		    
		    cityColumn = new TableColumn("City");
		    cityColumn.setCellValueFactory(new PropertyValueFactory<>("citta"));
		    cityColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: #FF421E;");
		    
		    
		    addressColumn = new TableColumn("Address");
		    addressColumn.setCellValueFactory(new PropertyValueFactory<>("via"));
		    addressColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: #FF421E;");
		    
		    descriptionColumn = new TableColumn("Description");
		    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
		    descriptionColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: #FF421E;");

		    
		    getColumns().addAll(nameColumn, typeColumn, priceColumn, cityColumn, addressColumn, descriptionColumn);
		    resturantList = FXCollections.observableArrayList(); //"Costruttore"
		    setItems(resturantList); //ATTACCA ALLA TABLEVIEW I DATI LISTA RISTORANTI
		  }  
	 
	 
}
