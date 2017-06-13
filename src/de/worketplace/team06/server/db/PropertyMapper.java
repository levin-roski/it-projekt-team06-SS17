package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.Rating;
import de.worketplace.team06.shared.bo.Project;
import de.worketplace.team06.shared.bo.Property;

/**
 * 
 */
public class PropertyMapper {

	private static PropertyMapper propertyMapper = null;
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected PropertyMapper(){
		
	}
	
	public static PropertyMapper propertyMapper(){
		if (propertyMapper == null){
			propertyMapper = new PropertyMapper();
		}
		return propertyMapper; 
	}

	public Property findById (int id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery
    				("SELECT id, created, name, value, FROM Property " + "WHERE id= " + id);
    		
    		if (rs.next()) {
    			Property p = new Property();
    			p.setID(rs.getInt("id"));
    			p.setCreated(rs.getTimestamp("created"));
    			p.setName(rs.getString("name"));
    			p.setValue(rs.getString("value"));
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
  
    public Vector<Property> findAll() {
        Connection con = DBConnection.connection();
        Vector<Property> result = new Vector<Property>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, created, name, value "+ "FROM Property ");
        	
        	while (rs.next()){
        		Property p = new Property();
        		p.setID(rs.getInt("id"));
        		p.setCreated(rs.getTimestamp("created"));
        		p.setName(rs.getString("name"));
        		p.setValue(rs.getString("value"));
        		
        		result.addElement(p);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
	public Vector<Property> findByPartnerProfileID(int partnerProfileID) {
		
		Connection con = DBConnection.connection();
		Vector<Property> result = new Vector<Property>();
        
        try {
        	Statement stmt = con.createStatement();		
    			ResultSet rs = stmt.executeQuery("SELECT id, created, name, value, partnerProfileID "
    					+ " FROM property WHERE partnerProfileID ='" + partnerProfileID + "'ORDER BY id");
    			
    			while (rs.next()){
    				Property p = new Property();
    				p.setID(rs.getInt("id"));
    				p.setName(rs.getString("name"));
    				p.setCreated(rs.getTimestamp("created"));
            		p.setValue(rs.getString("value"));
            		p.setPartnerProfileID(rs.getInt("partnerProfileID"));
            		
            		result.add(p);	
    			}
    		} 
        		catch (SQLException e2) {
    			e2.printStackTrace();
    		}
		
    		
			return result;
	}
    
    public Property insert (Property p) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM property ");
        	
        	if (rs.next()){
        	p.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("INSERT INTO rating (id, created, name, value) " 
        	+ "VALUES (" 
        		+ p.getID() + ", "
        		+ p.getCreated() + ", " 
        		+ p.getName() + ", " 
        		+ p.getValue() 
        		+ "','" +"')");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return p;
    } 
    
    public Property update(Property p) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE property " 
        	+ "SET value=\"" + p.getValue() + "\", "
        	+ "created=\"" +  p.getCreated() + "\" " 
        	+ "name=\"" +  p.getName() + "\" " 
        	+ "WHERE id=" + p.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return p;
    }

    public void delete(Property p) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM property " + "WHERE id=" + p.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }
}
