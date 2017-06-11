package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.Rating;
<<<<<<< HEAD
import de.worketplace.team06.shared.bo.Team;


public class RatingMapper {

	private static RatingMapper ratingMapper = null;
	 /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected RatingMapper(){
		
	}
	
	/**
	 * Sicherstellen der Singleton-Eigenschaft
	 * @return
	 */
	
	public static RatingMapper ratingMapper(){
		if (ratingMapper == null){
			ratingMapper = new RatingMapper();
		}
		return ratingMapper; 
	}
	
	/**
	 * Auslesen eines bestimmten Rating-Objektes in der Datenbank 
	 * @param id
	 * @return
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
    		
    		if (rs.next()) {
    			Rating r = new Rating();
    			r.setID(rs.getInt("id"));
    			r.setRatingStatement(rs.getString("statement"));
    			r.setCreated(rs.getTimestamp("created"));
    			r.setRating(rs.getDouble("rating"));
    			r.setApplicationID(rs.getInt("ApplicationID"));
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return r;
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
        		r.setRating(rs.getDouble("rating"));
        		r.setApplicationID(rs.getInt("ApplicationID"));
        		
        		result.addElement(r);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    /**
     * Hinzufügen eines Rating-Objektes in der Datenbank
     * @param r
     * @return
     */
    
    public Rating insert (Rating r) {
    	/**
         * Verbindung zur Datenbank herstellen
         */
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM rating ");
        	
        	if (rs.next()){
        	r.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("INSERT INTO rating (id, statement, created, rating, applicationID) " 
        	+ "VALUES (" 
        		+ r.getID() + ", "
        		+ r.getCreated() + ", " 
        		+ r.getRating() + ", " 
        		+ r.getApplicationID() + ", "
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
     * Aktualisieren eines Rating-Objektes in der Datenbank 
     * @param r
     * @return
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
     * Löschen eines Rating-Objektes in der Datenbank 
     * @param r
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
    
    public findRatingByApplicationID(int applicationID){
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
