package ristogo;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class TableViewReservation extends TableView<Reservation> {
	
	 private final ObservableList<Reservation> reservationList;
	 private TableColumn nameColumn;
	 private TableColumn dateColumn;
	 private TableColumn hourColumn;
	 private TableColumn seatsColumn;

	 
	 
	  public TableViewReservation () {
		    //setEditable(true);
		    //setFixedCellSize(35);
		    setMaxHeight((new Configuration()).getnumberRowsDisplayable()*getFixedCellSize());
		    setMinWidth(575);    
		    String font = (new Configuration()).getFont();
		    String textColor =(new Configuration()).getTextColor();
		   
		    nameColumn = new TableColumn("Name");
		    nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
		    nameColumn.setStyle("-fx-font-family: " + font + "; -fx-font-size: " + ((new Configuration()).getDimCharacter() -3) + "; -fx-color: "+ textColor +";");
		    
		    dateColumn = new TableColumn("Date");
		    dateColumn.setCellValueFactory(new PropertyValueFactory<>("genere"));
		    dateColumn.setStyle(" -fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    hourColumn = new TableColumn("Hour");
		    hourColumn.setCellValueFactory(new PropertyValueFactory<>("costo"));
		    hourColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    seatsColumn = new TableColumn("Seats");
		    seatsColumn.setCellValueFactory(new PropertyValueFactory<>("citta"));
		    seatsColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    /*colonnaNomeEsame.setMinWidth(280); colonnaNomeEsame.setMaxWidth(280);
		    colonnaCreditiEsame.setMinWidth(85); colonnaCreditiEsame.setMaxWidth(85);
		    colonnaVotoEsame.setMinWidth(85); colonnaVotoEsame.setMaxWidth(85);
		    colonnaDataConvalida.setMinWidth(150); colonnaDataConvalida.setMaxWidth(150);
		    colonnaEsameInfo.setMinWidth(80); colonnaEsameInfo.setMaxWidth(80);*/
		    
		   
		    
		    getColumns().addAll(nameColumn, dateColumn, hourColumn, seatsColumn);
		    reservationList = FXCollections.observableArrayList(); //"Costruttore"
		    setItems(reservationList); //ATTACCA ALLA TABLEVIEW I DATI LISTA RISTORANTI
		  }  
	 
	 

}
