package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.OrgaUnit;
	/**
	 * 
	 */
	public class OrgaUnitMapper {
	    /**
	     * Default constructor
	     */
		private static OrgaUnitMapper orgaUnitMapper = null;
		 /**
		   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
		   * neue Instanzen dieser Klasse zu erzeugen.
		   */
		protected OrgaUnitMapper(){
			
		}
		
		public static OrgaUnitMapper orgaUnitMapper(){
			if (orgaUnitMapper == null){
				orgaUnitMapper = new OrgaUnitMapper();
			}
			return orgaUnitMapper; 
		}
	    /**
	     * @param orgaUnit 
	     * @return
	     */
	    public OrgaUnit insert(OrgaUnit o) {
	        // TODO implement here
	    	Connection con = DBConnection.connection();
	    	
	    	try{
	    		Statement stmt = con.createStatement();
	    		
	    		ResultSet rs = stmt.executeQuery(" ");
	    	}
	    	
	        return null;
	    }

	    /**
	     * @param orgaUnit 
	     * @return
	     */
	    public OrgaUnit update(OrgaUnit orgaUnit) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @param orgaUnit
	     */
	    public void delete(OrgaUnit orgaUnit) {
	        // TODO implement here
	    }

	    /**
	     * @return
	     */
	    public Vector<OrgaUnit> findAll() {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @param orgaUnit 
	     * @return
	     */
	    public OrgaUnit findPersonByOrgaUnit(OrgaUnit orgaUnit) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @param orgaUnit 
	     * @return
	     */
	    public OrgaUnit findTeamByOrgaUnit(OrgaUnit orgaUnit) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @param orgaUnit 
	     * @return
	     */
	    public OrgaUnit findOrganisationByOrgaUnit(OrgaUnit orgaUnit) {
	        // TODO implement here
	        return null;
	    }

}
	
