package application;

import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;



public class TableViewResturant extends TableView<Resturants> {
	 private final ObservableList<Resturant> resturantList;
	 private TableColumn nameColumn;
	 private TableColumn typeColumn;
	 private TableColumn priceColumn;
	 private TableColumn addressColumn;
	 private TableColumn descriptionColumn;
	 private TableColumn dateColumn;
	 
	 int numberRowsDisplayable = 7;
	 String font = "Arial";
	 
	 
	  public TableViewResturant () {
		    //setEditable(true);
		    //setFixedCellSize(35);
		    setMaxHeight(numberRowsDisplayable*getFixedCellSize());
		    setMinWidth(575);    
		    String font = (new ValoriConfigurazioneXML()).getFont();
		    
		    
		    nameColumn = new TableColumn("Name");
		    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		    nameColumn.setStyle("-fx-font-family: " + font + "; -fx-font-size: " + ((new ValoriConfigurazioneXML()).getDimCarattere() -3) + "; -fx-color: #FF421E;");
		    
		    colonnaCreditiEsame = new TableColumn("Crediti");
		    colonnaCreditiEsame.setCellValueFactory(new PropertyValueFactory<>("crediti"));
		    colonnaCreditiEsame.setCellFactory(cellFactory);
		    colonnaCreditiEsame.setStyle(" -fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new ValoriConfigurazioneXML()).getDimCarattere() + "; -fx-color: #FF421E;");
		    
		    colonnaVotoEsame = new TableColumn("Voto");
		    colonnaVotoEsame.setCellValueFactory(new PropertyValueFactory<>("voto"));
		    colonnaVotoEsame.setCellFactory(cellFactory);
		    colonnaVotoEsame.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new ValoriConfigurazioneXML()).getDimCarattere() + "; -fx-color: #FF421E;");
		    
		    colonnaDataConvalida = new TableColumn("Data Convalida");
		    colonnaDataConvalida.setCellValueFactory(new PropertyValueFactory<>("dataConvalida"));
		    colonnaDataConvalida.setCellFactory(cellFactory);
		    colonnaDataConvalida.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new ValoriConfigurazioneXML()).getDimCarattere() + "; -fx-color: #FF421E;");
		    
		    colonnaEsameInfo = new TableColumn("Es. Info.");
		    colonnaEsameInfo.setCellValueFactory(new PropertyValueFactory<>("esameInfo"));
		    colonnaEsameInfo.setCellFactory(cellFactory);
		    colonnaEsameInfo.setStyle("-fx-alignment: CENTER; -fx-font-family: " + font + "; -fx-font-size: " + (new ValoriConfigurazioneXML()).getDimCarattere() + "; -fx-color: #FF421E;");

		    
		    
		    colonnaNomeEsame.setMinWidth(280); colonnaNomeEsame.setMaxWidth(280);
		    colonnaCreditiEsame.setMinWidth(85); colonnaCreditiEsame.setMaxWidth(85);
		    colonnaVotoEsame.setMinWidth(85); colonnaVotoEsame.setMaxWidth(85);
		    colonnaDataConvalida.setMinWidth(150); colonnaDataConvalida.setMaxWidth(150);
		    colonnaEsameInfo.setMinWidth(80); colonnaEsameInfo.setMaxWidth(80);
		    
		    getColumns().addAll(colonnaNomeEsame, colonnaCreditiEsame, colonnaVotoEsame, colonnaDataConvalida, colonnaEsameInfo);
		    listaEsami = FXCollections.observableArrayList(); //"Costruttore"
		    setItems(listaEsami); //ATTACCA ALLA TABLEVIEW I DATI LISTA ESAMI
		  }  
	 
	 
}
