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
				stmt.executeUpdate("INSERT INTO orgaunit (id, created, googleID, description, type) " + "VALUES (" + o.getID() + ",'" + o.getCreated() + "','" + o.getGoogleID() +  "','" + o.getDescription() +  "','" + o.getType() + "')");
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN organisation ON orgaunit.id = organisation.id WHERE orgaunit.googleID = '" + googleID + "'");		
			
			if (rs.next()) {
				Organisation o = new Organisation();
				o.setID(rs.getInt("id"));
				o.setCreated(rs.getTimestamp("created"));
				o.setGoogleID(googleID);
				o.setDescription(rs.getString("description"));
				o.setPartnerProfileID(rs.getInt("partnerprofileID"));
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
    public Organisation update(Organisation o) {
        // TODO implement here
        return null;
    }

    /**
     * @param orgaUnit
     */
    public Vector<Project> findById (int id){
    	// TODO implement here
    	return null; 
    }
    
    /**
     * Löschen einer Organisation aus der Datenbank.
     * @param orga
     */
	public void delete(Organisation orga) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	//Löschen der Organisation aus der Tabelle organisation.
	    	stmt.executeUpdate("DELETE FROM person " + "WHERE id=" + orga.getID());
	    	//Löschen der damit verbundenen OrgaUnit aus der Tabelle orgaunit.
	    	stmt.executeUpdate("DELETE FROM orgaunit " + "WHERE id=" + orga.getID());

	    }
	    catch (SQLException e2) {
	    	//TODO: @HANNES --> Passt das so mit der Überprüfung der Transaktion?
	    	try {
	    		System.out.println("Die SQL Transaktion konnte nicht vollständig ausgeführt werden. Es wird versucht die Transaktion rückgängig zu machen!");
	    		con.rollback();
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} finally {
				  e2.printStackTrace();
			}
	    }
		
	}

    /**
     * @return
     */
    public Vector<Organisation> findAll() {
        // TODO implement here
        return null;
    }


    /**
     * @param orgaUnit 
     * @return
     */

}
