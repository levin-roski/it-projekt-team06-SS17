package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Project;
/**
 * 
 */
public class PartnerProfileMapper {

	private static PartnerProfileMapper partnerProfileMapper = null;
	 /**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected PartnerProfileMapper(){
		
	}
	
	public static PartnerProfileMapper partnerProfileMapper(){
		if (partnerProfileMapper == null){
			partnerProfileMapper = new PartnerProfileMapper();
		}
		return partnerProfileMapper; 
	}
	
	public PartnerProfile findById (int id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id, created, last_edit, teamID, personID "
    				+ "FROM partnerprofile WHERE id= " + id);
    		
    		if (rs.next()) {
    			PartnerProfile part = new PartnerProfile();
    			part.setID(rs.getInt("id"));
    			part.setLastEdit(rs.getTimestamp("last_edit"));
    			part.setCreated(rs.getTimestamp("created"));
    	
    			return part;
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
	
    public Vector<PartnerProfile> findAll() {
        Connection con = DBConnection.connection();
        Vector<PartnerProfile> result = new Vector<PartnerProfile>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM partnerprofile ORDER BY id");
        	//noch vervollständigen 
 
        	if (rs.next()){
        		PartnerProfile part = new PartnerProfile();
        		part.setID(rs.getInt("id"));
        		part.setLastEdit(rs.getTimestamp("last_edit"));
        		part.setCreated(rs.getTimestamp("created"));
        		
        		
        		result.addElement(part);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    
    public PartnerProfile insert (PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM partnerprofile ");
        	
        	if (rs.next()){
        	part.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("INSERT INTO partnerprofile (id, last_edit, created) " 
        	+ "VALUES (" 
        	+ part.getID() + ",'" 
        	+ part.getLastEdit() + "',"
        	+ part.getCreated() + ",'"
        	+ "')");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return part;
    } 
    
    public PartnerProfile update(PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE project SET last_edit=\"" + part.getLastEdit() + "\", "
        	+ "WHERE id=" + part.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return part;
    }

    public void delete(PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE * FROM partnerprofile " + "WHERE id=" + part.getID());
        	
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }

	public PartnerProfile findPartnerProfileByID(int partnerProfileID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
