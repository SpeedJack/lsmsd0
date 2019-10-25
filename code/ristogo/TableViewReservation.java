package ristogo;

import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

public class TableViewReservation extends TableView<ReservationBean> {
	
	 private final ObservableList<ReservationBean> reservationList;
	 private TableColumn nameColumn;
	 private TableColumn dateColumn;
	 private TableColumn hourColumn;
	 private TableColumn seatsColumn;
	 
	 
	  public TableViewReservation () {
		    setEditable(false);
		    setFixedCellSize(35);
		    setMaxHeight(((new Configuration()).getnumberRowsDisplayable()-2)*getFixedCellSize());
		    setMinWidth(600);
		    setMaxWidth(600);
		    String font = (new Configuration()).getFont();
		    String textColor =(new Configuration()).getTextColor();
		   
		    nameColumn = new TableColumn("Name");
		    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		    nameColumn.setStyle("-fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    
		    dateColumn = new TableColumn("Date");
		    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		    dateColumn.setStyle(" -fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    hourColumn = new TableColumn("Hour");
		    hourColumn.setCellValueFactory(new PropertyValueFactory<>("hour"));
		    hourColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    seatsColumn = new TableColumn("Seats");
		    seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
		    seatsColumn.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new Configuration()).getDimCharacter() + "; -fx-color: "+ textColor +";");
		    
		    nameColumn.setMinWidth(300); nameColumn.setMaxWidth(300);
		    dateColumn.setMinWidth(150); dateColumn.setMaxWidth(150);
		    hourColumn.setMinWidth(100); hourColumn.setMaxWidth(100);
		    seatsColumn.setMinWidth(50); seatsColumn.setMaxWidth(50);

		    
		   
		    
		    getColumns().addAll(nameColumn, dateColumn, hourColumn, seatsColumn);
		    reservationList = FXCollections.observableArrayList(); //"Costruttore"
		    setItems(reservationList); //ATTACCA ALLA TABLEVIEW I DATI LISTA RISTORANTI
		  }  
	  
	  public void myReservations (boolean isRestauranteur) {
		  reservationList.clear();
		  List<ReservationBean> res;
		  if(isRestauranteur) {
			  res = (List<ReservationBean>)MessageHandler.sendRequest(MessageHandler.LIST_RESERVATION_REST);
		  }
		  else {
			  res = (List<ReservationBean>)MessageHandler.sendRequest(MessageHandler.LIST_RESERVATION);
		  }
		  reservationList.addAll(res);
	  }
	 
	 

}
