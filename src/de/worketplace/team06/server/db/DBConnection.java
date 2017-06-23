package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

/**
 * Verwalten der Datenbank-Verbindung.
 * 
 * @author Patrick
 */
public class DBConnection {

    /**
     * Die Instatiierung der Klasse DBConnection erfolgt nur einmal.
     * Dies wird auch als Singleton bezeichnet.
     * Durch den Bezeichner static ist die Variable nur einmal für jede Instanz der Klasse vorhanden.
     * Sie speichert die einzige Instanz der Klasse.
     */
    private static Connection con = null;

    /**
     * Über folgende URL wird die Datenbank angesprochen.
     */
    
      private static String googleUrl = "jdbc:google:mysql://it-projekt-team06-ss17-v1:it-projekt-team-06/projektmarktplatz?user=jdbcg&password=Uvawevusa675";
      private static String localUrl = "jdbc:mysql://173.194.243.13:3306/projektmarktplatz?user=jdbc&password=Uvawevusa675";
      
      //String für Toby's lokale DB
      private static String localhost = "jdbc:mysql://127.0.0.1:8889/projektmarktplatz?user=root&password=root";;
    
    /**
	 * Durch DBConnection.connection() wird die folgende Methode aufgerufen.
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von DBConnection existiert.
	 * Die Instantiierung der DBConnection sollte stets durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return DBConnection-Objekt.
	 * 
	 * @see DBConnection#con
     */
    public static Connection connection() {
    	
        if (con == null) {
            String url = null;
            try {
                 if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                	 
            	     Class.forName("com.mysql.jdbc.GoogleDriver");
                      url = googleUrl;	
                 } 
                 else {
                	 
                	 Class.forName("com.mysql.jdbc.Driver");
                	 url = localUrl; 
//                	 url = localhost;
                } 
                 
                con = DriverManager.getConnection(url);
            } 
            catch (Exception e) {
                con = null;
                e.printStackTrace();
            }
        }

        return con;
    }

}
