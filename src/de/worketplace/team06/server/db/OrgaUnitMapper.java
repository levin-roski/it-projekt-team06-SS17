package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.worketplace.team06.shared.bo.OrgaUnit;
/**
 * Die Mapper-Klasse TeamMapper bildet Team-Objekte als Datensätze
 * in einer relationalen Datenbank ab. Durch die Bereitstellung verschiedener Methoden
 * können mit deren Hilfe Objekte erzeugt, verändert, gelöscht und ausgelesen werden.
 * Das Mapping erfolgt bidirektional: Objekte können in Datensätze und Datensätze in 
 * Objekte umgewandelt werden.
 * 
 * @see ApplicationMapper
 * @see CallMapper
 * @see EnrollmentMapper
 * @see MarketplaceMapper
 * @see OrganisationMapper
 * @see PartnerProfileMapper
 * @see PersonMapper
 * @see ProjectMapper
 * @see PropertyMapper
 * @see RatingMapper
 * @see TeamMapper
 * 
 * @author Strepp
 * @author Vocke
 * @author Thies
 */

public class OrgaUnitMapper {
	
	/**
     * Die Klasse OrgaUnitMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     */
	private static OrgaUnitMapper orgaUnitMapper = null;
	
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected OrgaUnitMapper() {
		
	}

	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der OrgaUnitMapper-Klasse existiert. 
	 * OrgaUnitMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode ausgeführt werden.
	 * 
	 * @return TeamMapper
	 */
	public static OrgaUnitMapper orgaUnitMapper() {
		if (orgaUnitMapper == null) {
			orgaUnitMapper = new OrgaUnitMapper();
		}
		
		return orgaUnitMapper;
	}
	
	/**
	 * Auslesen des Types der Organisationseinheit aus der Datenbank. Anhand der übergebenen GoogleID
	 * wird je nach Typ entweder ein Team, eine Person oder eine Organisation ausgegeben.
	 * 
	 * @param googleID
	 * @return type
	 */
	public String findTypeByGoogleID(String googleID){
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, google_id, description, partnerprofile_id, type FROM orgaunit " + "WHERE google_id = '" + googleID +"'");		
			if (rs.next()) {
			String type = rs.getString("type");
			return type;
			}
			return null;
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		
	}
	
	

	/**
	 * Auslesen des Types der Organisationseinheit aus der Datenbank. Anhand der übergebenen ID
	 * wird je nach Typ entweder ein Team, eine Person oder eine Organisation ausgegeben.
	 * 
	 * @param ouid
	 * @return
	 */
	public String findTypeByID(Integer ouid) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select type FROM orgaunit " + "WHERE id = " + ouid);
			if (rs.next()) {
			String type = rs.getString("type");
			return type;
			}
			return null;
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
	}

	/**
	 * Auslesen der ID der Organisationseinheit. 
	 * 
	 * @param googleID
	 * @return
	 */
	public Integer findID(String googleID) {
		
		Connection con = DBConnection.connection();
		Integer checkID;
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id FROM orgaunit " + "WHERE google_id = " + googleID);
			
			if (rs.next()) {
			checkID = rs.getInt("id");
			return checkID;
			}
			return null;
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Aktualisieren eines Organisationseinheit-Objektes in der Datenbank.
	 * 
	 * @param ou
	 */
	public void update(OrgaUnit ou) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	stmt.executeUpdate("UPDATE orgaunit, person SET"
	    						+ " orgaunit.description='" + ou.getDescription() +
	    						"', orgaunit.partnerprofile_id= " + ou.getPartnerProfileID() +
	    						" WHERE orgaunit.id= " + ou.getID()); 
	    						
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}	
	
}
