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
     * Die Klasse ApplicationMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     * @author Thies
     * @author Patrick
     * @author Theresa
     */
	
	private static ApplicationMapper applicationMapper = null;
	
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	protected ApplicationMapper() {
		
	}
	
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der ApplicationMapper-Klasse existiert. 
	 * ApplicationMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode aufgerufen werden.
	 * 
	 * @return EnrollmentMapper
	 * @author Thies
	 * @author Patrick
	 * @author Theresa
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
	 * @author Patrick
	 */
	public Application findByID(Integer id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, text, call_id, orgaunit_id, rating_id, status FROM application " + "WHERE id = " + id);
			
			if (rs.next()) {
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("orgaunit_id"));
				a.setRatingID(rs.getInt("rating_id"));
				a.setStatus(rs.getInt("status"));
				return a;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
			
	/**
	 * Methode zur Suche nach allen Bewerbungen, die in der Datenbank
	 * gespeichert sind.
	 * 
	 * @return Vector<Application> mit allen Application-Objekten.
	 * @author Patrick
	 */
	public Vector<Application> findAll() {
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id, rating_id, status FROM application " + "ORDER BY id");
			
			while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("organisation_id"));
				a.setRatingID(rs.getInt("rating_id"));
				a.setStatus(rs.getInt("status"));
				
				result.addElement(a);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	/**
	 * Suchen und auslesen der Bewerbungen anhand der übergebenen RatingID. Alle Bewerbungen, die zu 
	 * einer bestimmten Bewertung gehören, werden ausgelesen.
	 * 
	 * @param rID
	 * @return
	 * @author Patrick
	 * @author Theresa
	 */
	public Application findByRatingID(Integer rID) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, text, call_id, orgaunit_id, rating_id, status FROM application " + "WHERE rating_id = " + rID);
			
			if (rs.next()) {
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("orgaunit_id"));
				a.setRatingID(rs.getInt("rating_id"));
				a.setStatus(rs.getInt("status"));
				return a;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * Suchen und auslesen der Bewerbungen anhand der übergebenen OrgaUnitID. Alle Bewerbungen, die zu 
	 * einer bestimmten Organisationseinheit gehören, werden ausgelesen.
	 * 
	 * @param orgaUnitID
	 * @return
	 * @author Theresa
	 * @author Patrick
	 */
	public Vector<Application> findByOrgaUnitID (Integer orgaUnitID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id, rating_id, status "
					+ " FROM application WHERE orgaunit_id ='" + orgaUnitID + "'ORDER BY id");
			
				while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("organisation_id"));
				a.setRatingID(rs.getInt("rating_id"));
				a.setStatus(rs.getInt("status"));
				
				result.addElement(a);
			}
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Suchen und auslesen der Bewerbungen anhand der übergebenen CallID. Alle Bewerbungen, die zu 
	 * einer bestimmten Ausschreibung gehören, werden ausgelesen. 
	 * 
	 * @param callID
	 * @return
	 * @author Theresa
	 * @author Patrick
	 */
	public Vector<Application> findByCallID (Integer callID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id, rating_id, status"
					+ " FROM application WHERE call_id ='" + callID + "'ORDER BY id");
			
				while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("orgaunit_id"));
				a.setRatingID(rs.getInt("rating_id"));
				a.setStatus(rs.getInt("status"));
				
				result.addElement(a);
			}
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * Einfuegen eines Application-Objekts in die Datenbank. Dabei wird auch der Primaerschluessel
	 * des uebergebenen Objektes geprueft und ggf. berichtigt.
	 * 
	 * @param a
	 * @return
	 * @author Theresa
	 * @author Patrick
	 */
	public Application insert(Application a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			/*
			 *Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoeht und an an a vergeben
			*/
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM application ");
			
			if (rs.next()) {
				
				a.setID(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				// Einfuegeoption, damit das neue PartnerProfile-Tupel in die Datenbank eingefuegt werden kann.
				stmt.executeUpdate("INSERT INTO application (id, created, text, call_id, orgaunit_id, status) " + "VALUES (" + a.getID() + ",'" + a.getCreated() + "','" + a.getText() + "'," + a.getCallID() + "," + a.getOrgaUnitID()+ "," + a.getStatus() + ")");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}
	
	/**
	 * Methode ermoeglicht, dass ein Application-Objekt in der Datenbank aktualisiert werden kann.
	 * 
	 * @param a
	 * @return
	 * @author Theresa
	 * @author Patrick
	 */
	public Application update(Application a) {
		Connection con = DBConnection.connection();
		
	
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE application SET text='" + a.getText() + "', rating_id="+ a.getRatingID() + ", status="+ a.getStatus() + " WHERE id=" + a.getID());
			// alt:  stmt.executeUpdate("UPDATE application " + "SET text=\"" + a.getText() + "\" " + "WHERE id=" + a.getID());
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}
	
	/**
	 * Loeschen eines Application-Objekts in der Datenbank.
	 * 
	 * @param a
	 * @author Theresa
	 * @author Patrick
	 */
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
	
	/**
	 * Auslesen des zugehoerigen Person-Objekts zu einer gegebenen Bewerbung. 
	 * 
	 * @param a die Bewerbung, deren Person wir auslesen möchten 
	 * @return ein Objekt, das die Person zu der Bewerbung darstellt
	 * @author Thies
	 * @author Theresa
	 */
	public Person getSourcePerson(Application a) {
		//Bedienen am PersonMapper
		return PersonMapper.personMapper().findByID(a.getOrgaUnitID());
	}

	/**
	 * Auslesen des zugehoerigen Organisation-Objekts zu einer gegebenen Bewerbung.
	 * 
	 * @param a die Bewerbung, deren Organisation wir auslesen möchten
	 * @return ein Objekt, das die Organisation zu der Bewerbung darstellt
	 * @author Thies
	 * @author Theresa
	 */
	public Organisation getSourceOrganisation(Application a) {
		//Bedienen am OrganisationMapper
		return OrganisationMapper.organisationMapper().findByID(a.getOrgaUnitID());
	}
	
	/**
	 * Auslesen des zugehoerigen Team-Objekts zu einer gegebenen Bewerbung.
	 * 
	 * @param a die Bewebung, deren Team wir auslesen moechten
	 * @return ein Objekt, das das Team zu der Bewerbung darstellt
	 * @author Thies
	 * @author Theresa
	 */
	public Team getSourceTeam(Application a) {
		//Bedienen am TeamMapper
		return TeamMapper.teamMapper().findByID(a.getOrgaUnitID());
	}
	
	/**
	 * Auslesen des zugehörigen Call-Objekts zu einer gegebenen Bewerbung.
	 * 
	 * @param a die Bewerbung, deren Ausschreibung wir auslesen möchten
	 * @return ein Objekt, das die Ausschreibung zu der Bewerbung darstellt
	 * @author Thies
	 * @author Theresa
	 */
	public Call getSourceCall(Application a) {
		//Bedienen am CallMapper
		return CallMapper.callMapper().findByID(a.getCallID());
	}
	
	
}