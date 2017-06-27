package it.univr.cd.store;

public class Control {
	// ATTRIBUTI
	private static boolean logged = false;
	private static int userId = 0;
	
	// MAIN
	public static void main(String[] args) {
		//INIZIO ESECUZIONE
		System.out.println("Inizio Esecuzione");
		
		View view = new View();
		view.setVisible(true);
		
		System.out.println("Fine Esecuzione");
		//FINE ESECUZIONE
	}
	
	// METODI
	public static void setLogged(boolean loggedd){
		logged = loggedd;
	}
	
	public static boolean getLogged(){
		return logged;
	}
	
	public static void setUserId(int userIdd){
		userId = userIdd;
	}
	
	public static int getUserId(){
		return userId;
	}
}