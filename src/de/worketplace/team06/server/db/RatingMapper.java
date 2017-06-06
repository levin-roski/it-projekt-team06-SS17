package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.Rating;
<<<<<<< HEAD
import de.worketplace.team06.shared.bo.Team;
=======
>>>>>>> refs/heads/master
/**
 * 
 */
public class RatingMapper {

	private static RatingMapper ratingMapper = null;
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected RatingMapper(){
		
	}
	
	public static RatingMapper ratingMapper(){
		if (ratingMapper == null){
			ratingMapper = new RatingMapper();
		}
		return ratingMapper; 
	}


	public Rating findById (int id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery
    				("SELECT id, statement, created, rating FROM Rating " + "WHERE id= " + id);
    		
    		if (rs.next()) {
    			Rating r = new Rating();
    			r.setID(rs.getInt("id"));
    			r.setRatingStatement(rs.getString("statement"));
    			r.setCreated(rs.getTimestamp("created"));
    			r.setRating(rs.getDouble("rating"));
    		}	
    	}
    	catch (SQLExpetion e){
    		e.printStackTrace();
    	}
    	return null;
    }
  
    public Vector<Rating> findAll() {
        Connection con = DBConnection.connection();
        Vector<Rating> result = new Vector<Rating>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, statement, created, rating "+ "FROM Team ");
        	
        	while (rs.next()){
        		Rating r = new Rating();
        		r.setID(rs.getInt("id"));
        		r.setRatingStatement(rs.getString("statement"));
        		r.setCreated(rs.getTimestamp("created"));
        		r.setRating(rs.getInt("rating"));
        		
        		result.addElement(r);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
<<<<<<< HEAD
    
    
    public Rating insert (Rating r) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM rating ");
        	
        	if (rs.next()){
        	r.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("INSERT INTO rating (id, statement, created, rating) " 
        	+ "VALUES (" 
        		+ r.getID() + ", "
        		+ r.getCreated() + ", " 
        		+ r.getRating() + ", " 
        		+ r.getRatingStatement() 
        		+ "','" +"')");
        	}
        }
       catch (SQLExpetion e){
    	e.printStackTrace();
    }
    
    return r;
    } 
    
    public Rating update(Rating r) {
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

    public void delete(Rating r) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM team " + "WHERE id=" + r.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
=======
    public Rating findById(int id){
    	// TODO implement here
    	return null;
>>>>>>> refs/heads/master
    }
	
}
