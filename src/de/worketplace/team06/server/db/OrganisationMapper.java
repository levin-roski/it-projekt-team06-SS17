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
		
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO orgaunit (id, created, googleID, description, type) " + "VALUES (" + o.getID() + ",'" + o.getCreated() + "','" + o.getGoogleID() +  "','" + o.getDescription() +  "','" + o.getType() + "')");
				stmt.executeUpdate("INSERT INTO organisation (id, created, name, street, zipcode, city) " + "VALUES (" + o.getID() + ",'" + o.getCreated() + "','" + o.getName() + "','" + o.getStreet() + "','" + o.getZipcode() + "','" + o.getCity() + "')");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return o;
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
    
    
    public void delete(Organisation o) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Vector<Organisation> findAll() {
        // TODO implement here
        return null;
    }

	public Organisation findByGoogleID(String googleId) {
		// TODO Auto-generated method stub
		return null;
	}



    /**
     * @param orgaUnit 
     * @return
     */

}
