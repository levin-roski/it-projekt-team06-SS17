package de.worketplace.team06.server.db;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

/**
 * Die Mapper-Klasse CallMapper bildet Call-Objekte als Datensätze
 * in einer relationalen Datenbank ab. Durch die Bereitstellung verschiedener Methoden
 * können mit deren Hilfe Objekte erzeugt, verändert, gelöscht und ausgelesen werden.
 * Das Mapping erfolgt bidirektional: Objekte können in Datensätze und Datensätze in 
 * Objekte umgewandelt werden.
 * 
 * @see ApplicationMapper
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

public class CallMapper {
	
	/**
     * Die Klasse CallMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     * @author Thies
     * @author Theresa
     */
	
	private static CallMapper callMapper = null;
	
	/**
	 * Darstellung von Datum wird durch diese Methode vereinfacht und vereinheitlicht. 
	 * wird aufgerufen, wenn Startdatum und Enddatum neu angelegt oder verändert werden.
	 * 
	 * @author Theresa
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	protected CallMapper() {
		
	}
	
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der CallMapper-Klasse existiert. 
	 * CallMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode aufgerufen werden.
	 * 
	 * @return CallMapper
	 * @author Thies
	 * @author Theresa
	 * @author Patrick
	 */
	public static CallMapper callMapper() {
		if (callMapper == null) {
			callMapper = new CallMapper();
		}
		
		return callMapper;
	}


	/**
	 * Methode ermoeglicht, dass ein Enrollment-Objekt in der Datenbank aktualisiert werden kann.
	 * 
	 * @param c
	 * @author Theresa
	 * @author Patrick
	 */
	
	public void update(Call c) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		String deadline = sdf.format(c.getDeadline());
    		
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE projektmarktplatz.`call` SET"
					+ " projektmarktplatz.`call`.title='" + c.getTitle() +
					"', projektmarktplatz.`call`.description= '" + c.getDescription() +
					"', projektmarktplatz.`call`.deadline= '" + deadline +
					"', projektmarktplatz.`call`.project_id= " + c.getProjectID() + 
					", projektmarktplatz.`call`.orgaunit_id= " + c.getCallerID() +
					", projektmarktplatz.`call`.partnerProfile_id= " + c.getPartnerProfileID() +
					", projektmarktplatz.`call`.status= " + c.getStatus() + 
					" WHERE projektmarktplatz.`call`.id= " + c.getID());
    	}
    	catch (SQLException e2) {
    		e2.printStackTrace();	
    	}
	}

	/**
	 * Einfuegen eines Enrollment-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
	 * des uebergebenen Objektes geprueft und ggf. berichtigt.
	 * 
	 * @param c
	 * @return
	 * @author Theresa
	 * @author Patrick
	 */
	
	public Call insert(Call c) {
		Connection con = DBConnection.connection();
		
		try {
			String deadline = sdf.format(c.getDeadline());
			
			Statement stmt = con.createStatement();
			/*
			 *Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoeht und an an c vergeben
			*/
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM projektmarktplatz.`call`");			
			if (rs.next()) {
				
				c.setID(rs.getInt("maxid") + 1);
		
				stmt = con.createStatement();
				// Einfuegeoption, damit das neue Call-Tupel in die Datenbank eingefuegt werden kann.
				stmt.executeUpdate("INSERT INTO projektmarktplatz.`call` (id, created, title, description, deadline, orgaunit_id, project_id, status) " 
				+ "VALUES (" + c.getID() + ",'" + c.getCreated() + "','" 
				+ c.getTitle() + "','" + c.getDescription() +  "','" 
				+ deadline + "'," + c.getCallerID() +  "," 
				+ c.getProjectID() + "," + c.getStatus() + ")");
			}
		}
		
		catch (SQLException e) {
			
				e.printStackTrace();	
		}
		return c;
	}

	/**
	 * Auslesen aller Call-Objekten aus der Datenbank.
	 * 
	 * @return Vector<Call> mit allen Call-Objekten.
	 * @author Patrick
	 * @author Theresa
	 */
	
	public Vector<Call> findAll() {
		
		Connection con = DBConnection.connection();
        Vector<Call> result = new Vector<Call>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, created, title, description,"
        			+ "deadline, project_id, orgaunit_id, partnerprofile_id, status  "
        	+ "FROM projektmarktplatz.`call` ");
        	
        	while (rs.next()){
        		Call c = new Call();
        		c.setID(rs.getInt("id"));
        		c.setCreated(rs.getTimestamp("created"));
        		c.setTitle(rs.getString("title"));
        		c.setDescription(rs.getString("description"));
        		c.setDeadline(sdf.parse(rs.getString("deadline")));
        		c.setProjectID(rs.getInt("project_id"));
        		c.setCallerID(rs.getInt("orgaunit_id"));
        		c.setPartnerProfileID(rs.getInt("partnerprofile_id"));
        		c.setStatus(rs.getInt("status"));
        		
        		result.addElement(c);
        	}
        	
        }
        catch (SQLException | ParseException e){
        	e.printStackTrace();
        	return null;
        }
    return result;
	}

	/**
	 * Methode zur Suche nach allen Ausschreibungen, deren Eigenschaften des PartnerProfils
	 * Gemeinsamkeiten haben mit den Eigenschaften eines PartnerProfils einer OrgaUnit.
	 * 
	 * @param callID
	 * @return vector<Call> mit allen Call-Objekten, die ähnliche Partnerprofile haben.
	 */	
/*	public Vector<Call> findCallForSimilarPartnerProfile(Integer callID) {
		Connection con = DBConnection.connection();
		Vector<Call> result = new Vector<Call>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM projektmarktplatz.'call' "
					+ "INNER JOIN projektmarktplatz.'partnerprofile' "
					+ "WHERE projektmarktplatz.`call`.partnerprofile_id = partnerprofile.id "
					+ "INNER JOIN projektmarktplatz.'orgaunit' "
					+ "INNER JOIN projektmarktplatz.'property' "
					+ "AND projektmarktplatz.'orgaunit'.partnerprofile.property.name = call.partnerprofile.property.name "
					+ "ORDER BY call.id");
			// Erste Überlegung für SQL Statement, TODO: Besprechen wie Anforderung 3) anders gelöst werden kann bzw. Statement miteinander erarbeiten
			while (rs.next()) {
				Call c = new Call();
			}
		}
		
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
			return null;
		}
		return result;
	}
*/	
	/**
	 * Auslesen von Ausschreibungen anhand einer übergebenen projectID.
	 * Da diese eindeutig ist wird genau ein Objekt zurückgegeben.
	 * 
	 * @param projectID
	 * @return Call-Objekt, das der übergebenen projectID entspricht bzw. null, 
	 * wenn kein Datenbank-Tupel mit der übergebenen ID vorhanden ist.
	 * @author Patrick 
	 */
	
	public Vector<Call> findByProjectID(Integer projectID) {
		Connection con = DBConnection.connection();
		Vector<Call> result = new Vector<Call>();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM projektmarktplatz.`call` WHERE projektmarktplatz.`call`.project_id = '" + projectID + "'");		
			
			while (rs.next()) {
				Call c = new Call();
				c.setID(rs.getInt("id"));
				c.setCreated(rs.getTimestamp("created"));
				c.setTitle(rs.getString("title"));
				c.setDescription(rs.getString("description"));
				c.setDeadline(sdf.parse(rs.getString("deadline")));
				c.setProjectID(rs.getInt("project_id"));
				c.setCallerID(rs.getInt("orgaunit_id"));
				c.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				c.setStatus(rs.getInt("status"));
				
				result.addElement(c);
			}
		}
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
			return null;
		}
		return result;
	}
	
	/**
	 * Methode zur Suche nach Ausschreibungen anhand einer callID.
	 * Da diese eindeutig ist wird genau ein Objekt zurückgegeben.
	 * 
	 * @param callID
	 * @return Call-Objekt, das der übergebenen ID entspricht bzw. null, 
	 * wenn kein Datenbank-Tupel mit der übergebenen ID vorhanden ist.
	 * @author Patrick
	 */
	
	public Call findByID(Integer callID) {
		
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, created, title, description, deadline, "
					+ "project_id, orgaunit_id, partnerprofile_id, status "
					+ "FROM projektmarktplatz.`call` WHERE projektmarktplatz.`call`.id = " + callID);		
			
			if (rs.next()) {
				Call c = new Call();
				c.setID(rs.getInt("id"));
				c.setCreated(rs.getTimestamp("created"));
				c.setTitle(rs.getString("title"));
				c.setDescription(rs.getString("description"));
				c.setDeadline(sdf.parse(rs.getString("deadline")));
				c.setProjectID(rs.getInt("project_id"));
				c.setCallerID(rs.getInt("orgaunit_id"));
				c.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				c.setStatus(rs.getInt("status"));
				return c;
			}			
		}
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Loeschen eines Call-Objektes aus der Datenbank mit der entsprechenden übergebenen ID.
	 * @param c
	 * @author Patrick
	 */
	
	public void delete(Call c) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	stmt.executeUpdate("DELETE projektmarktplatz.`call` FROM projektmarktplatz.`call` WHERE projektmarktplatz.`call`.id= " + c.getID());
	    	
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}

}
