package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.*;

public class OrganisationMapper {
	
	/**
     * Default constructor
     */
	private static OrganisationMapper organisationMapper = null;
	 /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected OrganisationMapper(){
		
	}
	
	public static OrganisationMapper organisationMapper(){
		if (organisationMapper == null){
			organisationMapper = new OrganisationMapper();
		}
		return organisationMapper; 
	}
    
	public Organisation findById (int id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id, street, zipcode, city, created FROM Organisation " + "WHERE id= " + id);
    		
    		if (rs.next()) {
    			Organisation o = new Organisation();
    			o.setID(rs.getInt("id"));
    			o.setStreet(rs.getString("street"));
    			o.setZipcode(rs.getInt("zipcode"));
    			o.setCity(rs.getString("city"));
    			o.setCreated(rs.getTimestamp("created"));
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
  
    public Vector<Organisation> findAll() {
        Connection con = DBConnection.connection();
        Vector<Organisation> result = new Vector<Organisation>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, street, zipcode, city, created "
        	+ "FROM Organisation ");
        	
        	while (rs.next()){
        		Organisation o = new Organisation();
        		o.setID(rs.getInt("id"));
        		o.setStreet(rs.getString("street"));
        		o.setZipcode(rs.getInt("zipcode"));
        		o.setCity(rs.getString("city"));
        		o.setCreated(rs.getTimestamp("created"));
        		
        		result.addElement(o);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    
    
    public Organisation insert (Organisation o) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM organisation ");
        	
        	if (rs.next()){
        	o.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("INSERT INTO project (id, street, zipcode, city) " 
        	+ "VALUES (" 
        	+ o.getID() + ", " 
        	+ o.getStreet() + "','" 
        	+ o.getZipcode() + "','"
        	+ o.getCity() + "','"
        	+ "')");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return o;
    } 
    
    public Organisation update(Organisation o) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE organisation " 
        	+ "SET street=\"" + o.getStreet() + "\", " 
        	+ "SET zipcode=\"" + o.getZipcode() + "\", "
        	+ "SET city=\"" + o.getCity() + "\", "
        	+ "WHERE id=" + o.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return o;
    }

    public void delete(Organisation o) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM organisation " + "WHERE id=" + o.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }
}
