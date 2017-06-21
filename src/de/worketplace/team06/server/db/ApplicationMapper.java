package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

/**
 * Die Mapper-Klasse ApplicationMapper bildet Application-Objekte als Datensätze
 * in einer relationalen Datenbank ab. Durch die Bereitstellung verschiedener Methoden
 * können mit deren Hilfe Objekte erzeugt, verändert, gelöscht und ausgelesen werden.
 * Das Mapping erfolgt bidirektional: Objekte können in Datensätze und Datensätze in 
 * Objekte umgewandelt werden.
 * 
 * @see CallMapper
 * @see EnrollmentMapper
 * @see MarketplaceMapper
 * @see OrganisationMapper
 * @see OrgaUnitMapper
 * @see PartnerProfileMapper
 * @see PersonMapper
 * @see ProjectMapper
 * @see PropertyMapper
 * @see RatingMapper
 * @see TeamMapper
 * 
 * @author Patrick
 */

public class ApplicationMapper {
	
    /**
     * Die Instatiierung der Klasse ApplicationMapper erfolgt nur einmal.
     * Dies wird auch als Singleton bezeichnet.
     * Durch den Bezeichner static ist die Variable nur einmal für jede Instanz der Klasse vorhanden.
     * Sie speichert die einzige Instanz der Klasse.
     * 
     * @see ApplicationMapper#applicationMapper()
     */
	
	private static ApplicationMapper applicationMapper = null;
	
	/**
	 * Der geschützte Konstruktor verhindert das Erzeugen neuer Instanzen
	 * der Klasse, sollte schon eine Instanz vorhanden sein.
	 */
	protected ApplicationMapper() {
		
	}
	
	/**
	 * Durch ApplicationMapper.applicationMapper wird die folgende Methode aufgerufen.
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von DBConnection existiert.
	 * Die Instantiierung der DBConnection sollte stets durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return ApplicationMapper-Objekt.
	 */
	public static ApplicationMapper applicationMapper() {
		if (applicationMapper == null) {
			applicationMapper = new ApplicationMapper();
		}
		
		return applicationMapper;
	}

	/**
	 * Methode zur Suche nach Bewerbungen anhand der Bewerbungs-ID.
	 * Da diese eindeutig ist wird genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 * @return Application-Objekt, das der übergebenen ID entspricht bzw. null, 
	 * wenn kein Datenbank-Tupel mit der übergebenen ID vorhanden ist.
	 */
	public Application findByID(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, text, call_id, orgaunit_id, rating_id FROM application " + "WHERE id = " + id);
			
			if (rs.next()) {
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("orgaunit_id"));
				a.setRatingID(rs.getInt("rating_id"));
				return a;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
			
	
	public Vector<Application> findAll() {
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id, rating_id FROM application " + "ORDER BY id");
			
			while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("organisation_id"));
				a.setRatingID(rs.getInt("rating_id"));
				
				result.addElement(a);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	public Application findByRatingID(int rID) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, text, call_id, orgaunit_id, rating_id FROM application " + "WHERE rating_id = " + rID);
			
			if (rs.next()) {
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("orgaunit_id"));
				a.setRatingID(rs.getInt("rating_id"));
				return a;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	public Vector<Application> findByOrgaUnitID (int orgaUnitID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id, rating_id "
					+ " FROM orgaunit WHERE orgaunit_id ='" + orgaUnitID + "'ORDER BY id");
			
				while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("organisation_id"));
				a.setRatingID(rs.getInt("rating_id"));
				
				result.addElement(a);
			}
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	public Vector<Application> findByCallID (int callID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id "
					+ " FROM call WHERE call_id ='" + callID + "'ORDER BY id");
			
				while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("organisation_id"));
				
				result.addElement(a);
			}
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public Application insert(Application a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxis " + "FROM application ");
			
			if (rs.next()) {
				
				a.setID(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO application (id, created, text, call_id, orgaunit_id, rating_id) " + "VALUES (" + a.getID() + "," + a.getCreated() + "," + a.getText() + "," + a.getCallID() + "," + a.getOrgaUnitID()+ "," + a.getRatingID()+ ")");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}
	
	
	public Application update(Application a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE application " + "SET text='" + a.getText() + "', rating_id="+ a.getRatingID() + "WHERE id=" + a.getID());
			// alt:  stmt.executeUpdate("UPDATE application " + "SET text=\"" + a.getText() + "\" " + "WHERE id=" + a.getID());
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}
	
	
	public void delete(Application a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM application " + "WHERE id=" + a.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	public Person getSourcePerson(Application a) {
		return PersonMapper.personMapper().findByID(a.getOrgaUnitID());
	}

	public Organisation getSourceOrganisation(Application a) {
		return OrganisationMapper.organisationMapper().findByID(a.getOrgaUnitID());
	}
	
	public Team getSourceTeam(Application a) {
		return TeamMapper.teamMapper().findByID(a.getOrgaUnitID());
	}

	public Call getSourceCall(Application a) {
		return CallMapper.callMapper().findByID(a.getCallID());
	}
	
	
}