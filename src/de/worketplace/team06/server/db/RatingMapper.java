package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.Rating;


public class RatingMapper {
	
	/**
     * Die Klasse RatingMapper wird nur einmal instantiiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     * @author Thies
     * @author Theresa
     */

	private static RatingMapper ratingMapper = null;
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	protected RatingMapper(){
		
	}
	
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der RatingMapper-Klasse existiert. 
	 * RatingMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode.
	 * 
	 * @return TeamMapper
	 * @author Thies
	 * @author Theresa
	 */
	
	public static RatingMapper ratingMapper(){
		if (ratingMapper == null){
			ratingMapper = new RatingMapper();
		}
		return ratingMapper; 
	}
	
	/**
	 * Suchen einer Bewertung mit vorgegebener RatiingID. Duch die Eindeutigkeit der ID, 
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param ratingID
	 * @return Rating-Objekt, das der übergebenen ID entspricht
	 */

	public Rating findById (int id){
    	Connection con = DBConnection.connection();
    	/**
    	 * Verbindung zur Datenbank herstellen 
    	 */
    	
    	try{
    		/**
    		 * leeres SQL-Statement wird angelegt 
    		 */
    		
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery
    				("SELECT id, statement, created, rating, applicationID FROM Rating " + "WHERE id= " + id);
    		/**
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
    		if (rs.next()) {
    			Rating r = new Rating();
    			r.setID(rs.getInt("id"));
    			r.setRatingStatement(rs.getString("statement"));
    			r.setCreated(rs.getTimestamp("created"));
    			r.setRating(rs.getFloat("rating"));
    			//TODO: *** WICHTIG *** Setzen wir die AppID im Rating??
    			//r.setApplicationID(rs.getInt("ApplicationID"));
    			return r;
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
	
	/**
	 * Auslesen aller Bewertungen 
	 * @return
	 */
  
    public Vector<Rating> findAll() {
    	 /**
         * Verbindung zur Datenbank herstellen
         */
        Connection con = DBConnection.connection();
        Vector<Rating> result = new Vector<Rating>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, statement, created, rating, applicationID "+ "FROM Team ");
        	
        	while (rs.next()){
        		Rating r = new Rating();
        		r.setID(rs.getInt("id"));
        		r.setRatingStatement(rs.getString("statement"));
        		r.setCreated(rs.getTimestamp("created"));
        		r.setRating(rs.getFloat("rating"));
        		//TODO: *** WICHTIG *** Setzen wir die AppID im Rating??
        		//r.setApplicationID(rs.getInt("ApplicationID"));
        		
        		result.addElement(r);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    /**
     * Einfuegen eines Rating-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
     * des uebergebenen Objektes geprueft und ggf. berichtigt.
     * 
     * @param r
     * @return Rating
     * @throws  
     * @author Thies 
     * @author Theresa
     */
    
    public Rating insert (Rating r) {
    	/**
         * Verbindung zur Datenbank herstellen
         */
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	/** 
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoet und an an t vergeben
			*/
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM rating ");
        	
        	if (rs.next()){
        	r.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	/**
			 * Einfuegeoption, damit das neue Team-Tupel in die Datenbank eingefuegt werden kann
			 */
        	stmt.executeUpdate("INSERT INTO rating (id, statement, created, rating, applicationID) " 
        	+ "VALUES (" 
        		+ r.getID() + ", "
        		+ r.getCreated() + ", " 
        		+ r.getRating() + ", " 
        		//TODO: *** WICHTIG *** Setzen wir die AppID im Rating??
        		//+ r.getApplicationID() + ", "
        		+ r.getRatingStatement() 
        		+ "','" +"')");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return r;
    } 
    
    /**
     * Methode ermoeglicht, dass ein Rating-Objekt in der Datenbank aktualisiert werden kann.
     * 
     * @param r 
     * @return Rating
     */
    
    public Rating update(Rating r) {
    	/**
         * Verbindung zur Datenbank herstellen
         */
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE rating " 
        	+ "SET statement=\"" + r.getRatingStatement() + "\", "
        	+ "created=\"" +  r.getCreated() + "\" " 
        	+ "rating=\"" +  r.getRating() + "\" " 
        	+ "WHERE id=" + r.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return r;
    }
    
    /**
     * Loeschen eines Rating-Objektes aus der Datenbank
     * @param rating
     */

    public void delete(Rating r) {
    	/**
         * Verbindung zur Datenbank herstellen
         */
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM team " + "WHERE id=" + r.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }
    
    
    /**
	 * Diese Methode findet ein Rating-Objekt, anhand der übergebenen Application-ID 
	 * 
	 * @param ApplicationID
	 * @return Rating-Objekt 
	 */
    
    public Rating findRatingByApplicationID(int applicationID){
    	/**
         * Verbindung zur Datenbank herstellen
         */
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt = con.createStatement();
    		
    		ResultSet rs = stmt.executeQuery("SELECT FROM rating" + "INNER JOIN application" + 
    		"ON application.id = rating.application_id" + "WHERE application.id = " + applicationID);
    		
    		if (rs.next()){
    			Rating r = new Rating ();
    			r.setID(rs.getInt("id"));
    			r.setRatingStatement(rs.getString("RatingStatement"));
    			r.setRating(rs.getFloat("Rating"));
    			return r;
    		}
    	} 
    	catch (SQLException e2){
    		e2.printStackTrace();
    	}
    	return null;
    }
	
}
