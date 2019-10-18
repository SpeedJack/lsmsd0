package application;

public class Configuration {
	private String font;
	private double dimCharacter;
	private String backgroundColor;
	private String textColor;
	private int numberRowsDisplayable;
	private String serverIP;
	private int serverPort;
	private String clientIP;
	
	
	
	public Configuration() {
		font = "Arial";
		dimCharacter = 18;
		backgroundColor = "FF421E";
		textColor = "FFFFFF";
		numberRowsDisplayable = 7;
		serverIP = "127.0.0.1";
		serverPort= 9999;
		clientIP = "127.0.0.1";
	}
	
	 public String getFont () {
		    return font;
	 }
	 
	 public double getDimCharacter () {
		    return dimCharacter;
	 }
	 public String getBackgroundColor () {
		    return backgroundColor;
	 }
	 public int getnumberRowsDisplayable () {
		    return numberRowsDisplayable;
	 }
	 public String getTextColor () {
		    return textColor;
	 }
	 public String getServerIP () {
		    return serverIP;
	 }
	 public int getServerPort () {
		    return serverPort;
	 }
	 public String getClientIP () {
		    return clientIP;
	 }
	
}
	