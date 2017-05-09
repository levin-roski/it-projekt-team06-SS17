package de.worketplace.team06.server.db;

import java.sql.Connection;
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
    /**
     * @param orgaUnit 
     * @return
     */
    public Organisation insert (Organisation o) {
        // TODO implement here
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt = con.createStatement();
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
    public Organisation findById (int id){
    	Team a = new Team();
    	return a; 
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

    /**
     * @param orgaUnit 
     * @return
     */

}
