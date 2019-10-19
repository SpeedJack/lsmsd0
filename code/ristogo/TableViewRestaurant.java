package ristogo;


import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.input.*;
import javafx.util.*;



public class TableViewRestaurant extends TableView<Restaurant> {
	 private final ObservableList<Restaurant> restaurantList;
	 private TableColumn nameColumn;
	 private TableColumn typeColumn;
	 private TableColumn priceColumn;
	 private TableColumn cityColumn;
	 private TableColumn addressColumn;
	 
/*	    class MyStringTableCell extends TableCell<Restaurant, String> {
	    	 
	        public void updateItem(String item, boolean empty) {
	            super.updateItem(item, empty);
	            setText(empty ? null : getString());
	            setGraphic(null);
	        }
	 
	        private String getString() {
	            return getItem() == null ? "" : getItem().toString();
	        }
	    }
	 
	    class MyEventHandler implements EventHandler<MouseEvent> {
	 
	        @Override
	        public void handle(MouseEvent t) {
	            TableCell c = (TableCell) t.getSource();
	            int index = c.getIndex();
	            System.out.println("id = " + restaurantList.get(index).getName());
	            
	        }
	    }
	 
     Callback<TableColumn, TableCell> stringCellFactory =
             new Callback<TableColumn, TableCell>() {
    	 
		         public TableCell call(TableColumn p) {
		             MyStringTableCell cell = new MyStringTableCell();
		             cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
		             return cell;
		         }
     	};
     	*/
		 
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
		    //nameColumn.setCellFactory(stringCellFactory);
		    
		    
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
		    
		    Restaurant res = new Restaurant(0000, 0000, "aaaa", "Pizza", 4, "pisa", "bbbb", "AAAAA", 10, "Lunch");
		    restaurantList.clear();
		    restaurantList.addAll(res, res);
		  }
	  
	  
	  
/*	 public void updateTable () {
		  restaurantList.clear();
		  restaurantList.addAll(RequestHandler.listRestaurant());
	  }
*/	 
	 
}
