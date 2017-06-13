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
    		ResultSet rs = stmt.executeQuery("SELECT id, created, lastEdit, teamID, personID "
    				+ "FROM Partnerprofile WHERE id= " + id);
    		
    		if (rs.next()) {
    			PartnerProfile p = new PartnerProfile();
    			p.setID(rs.getInt("id"));
    			p.setLastedit(rs.getDate("lastEdit"));
    			p.setCreated(rs.getTimestamp("created"));
    			return p;
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
	
	/*
	 * Fraglich ob wir alle PartnerProfiles benötigen 
	 */
	/*
    public Vector<PartnerProfile> findAll() {
        Connection con = DBConnection.connection();
        Vector<PartnerProfile> result = new Vector<PartnerProfile>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM partnerProfile ORDER BY id");
        	//noch vervollst�ndigen 
 
        	if (rs.next()){
        		PartnerProfile part = new PartnerProfile();
        		part.setID(rs.getInt("id"));
        		part.setLastEdit(rs.getDate("lastEdit"));
        		part.setCreated(rs.getTimestamp("created"));
        		part.setTeamID(rs.getInt("teamID"));
        		part.setPersonID(rs.getInt("personID"));
        		
        		result.addElement(part);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    */
    
    public PartnerProfile insert (PartnerProfile p) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM partnerProfile ");
        	
        	if (rs.next()){
        	p.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
        	stmt.executeUpdate("INSERT INTO partnerProfile (id, lastEdit, created) " 
        	+ "VALUES (" 
        	+ p.getID() + ",'" 
        	+ p.getLastedit() + "','"
        	+ p.getCreated() + "','"
        	+ "')");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return p;
    } 
    
    public PartnerProfile update(PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE project SET lastEdit=\"" + part.getLastedit() + "\", "
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
        	
        	stmt.executeUpdate("DELETE * FROM partnerProfile " + "WHERE id=" + part.getID());
        	
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
