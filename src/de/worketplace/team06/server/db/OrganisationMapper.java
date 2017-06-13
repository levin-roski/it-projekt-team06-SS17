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
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
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

    
	
    /**
     * @param orgaUnit 
     * @return
     */
	
    public Organisation insert (Organisation o) {

    	Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM orgaunit ");
			
			if (rs.next()) {
				
				o.setID(rs.getInt("maxid") + 1);
		
				con.setAutoCommit(false);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO orgaunit (id, created, google_id, description, type) " + "VALUES (" + o.getID() + ",'" + o.getCreated() + "','" + o.getGoogleID() +  "','" + o.getDescription() +  "','" + o.getType() + "')");
				stmt.executeUpdate("INSERT INTO organisation (id, created, name, street, zipcode, city) " + "VALUES (" + o.getID() + ",'" + o.getCreated() + "','" + o.getName() + "','" + o.getStreet() + "'," + o.getZipcode() + ",'" + o.getCity() + "')");
				con.commit();
			}
		}
		catch (SQLException e2) {
			try {
				System.out.println("Die SQL Transaktion konnte nicht vollständig ausgeführt werden. Es wird versucht die Transaktion rückgängig zu machen!");
				con.rollback();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  finally {
				 
				  e2.printStackTrace();
			}	
		}
		return o;
    }
    
	/**
	 * Auslesen einer Organisation mithilfe einer GoogleID.
	 */
	public Organisation findByGoogleID(String googleID) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN organisation ON orgaunit.id = organisation.id WHERE orgaunit.google_id = '" + googleID + "'");		
			
			if (rs.next()) {
				Organisation o = new Organisation();
				o.setID(rs.getInt("id"));
				o.setCreated(rs.getTimestamp("created"));
				o.setGoogleID(googleID);
				o.setDescription(rs.getString("description"));
				o.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				o.setType(rs.getString("type"));
				
				o.setName(rs.getString("name"));
				o.setStreet(rs.getString("street"));
				o.setZipcode(rs.getInt("zipcode"));
				o.setCity("city");
				return o;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}	

    /**
     * @param orgaUnit 
     * @return
     */
	public void update(Organisation o) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	//Updaten einer Person :) 
	    	stmt.executeUpdate("UPDATE orgaunit, organisation SET"
	    						+ " orgaunit.description='" + o.getDescription() +
	    						"', orgaunit.partnerprofile_id= " + o.getPartnerProfileID() +
	    						", organisation.name= '" + o.getName() +
	    						"', organisation.street= '" + o.getStreet() + 
	    						"', organisation.zipcode= " + o.getZipcode() + 
	    						", organisation.city= '" + o.getCity() + 
	    						"' WHERE orgaunit.id= " + o.getID() + 
	    						" AND organisation.id= " + o.getID()); 
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}
    
    /**
     * Löschen einer Organisation aus der Datenbank.
     * @param orga
     */
    public void delete(Organisation o) {
		Connection con = DBConnection.connection();
	    try {
	    	Statement stmt = con.createStatement();
	    	//Löschen der Organisation aus der Tabelle orgaunit und organisation
	    	stmt.executeUpdate("DELETE orgaunit, organisation FROM orgaunit INNER JOIN organisation ON orgaunit.id = organisation.id WHERE orgaunit.id= " + o.getID());	
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
	}
    
    public Organisation findByID(int ouid) {
    	Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN organisation ON orgaunit.id = organisation.id WHERE orgaunit.id = " + ouid);		
			
			if (rs.next()) {
				Organisation o = new Organisation();
				o.setID(rs.getInt("id"));
				o.setCreated(rs.getTimestamp("created"));
				o.setGoogleID(rs.getString("google_id"));
				o.setDescription(rs.getString("description"));
				o.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				o.setType(rs.getString("type"));
				
				o.setName(rs.getString("name"));
				o.setStreet(rs.getString("street"));
				o.setZipcode(rs.getInt("zipcode"));
				o.setCity("city");
				return o;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}


   /*
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
    */


   
}
