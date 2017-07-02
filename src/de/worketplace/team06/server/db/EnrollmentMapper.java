package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.worketplace.team06.shared.bo.Enrollment;
import de.worketplace.team06.shared.bo.Organisation;
import de.worketplace.team06.shared.bo.Person;
import de.worketplace.team06.shared.bo.Project;
import de.worketplace.team06.shared.bo.Team;
/**
 * Die Mapper-Klasse MarketplaceMapper bildet Marketplace-Objekte als Datensätze
 * in einer relationalen Datenbank ab. Durch die Bereitstellung verschiedener Methoden
 * können mit deren Hilfe Objekte erzeugt, verändert, gelöscht und ausgelesen werden.
 * Das Mapping erfolgt bidirektional: Objekte können in Datensätze und Datensätze in 
 * Objekte umgewandelt werden.
 * 
 * @see ApplicationMapper
 * @see CallMapper
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
 * @author Strepp
 * @author Vocke
 * @author Thies
 */
public class EnrollmentMapper {
	/**
     * Die Klasse EnrollmentMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     */
	private static EnrollmentMapper enrollmentMapper = null;
	
	/**
	 * Darstellung von Datum wird durch diese Methode vereinfacht und vereinheitlicht. 
	 * wird aufgerufen, wenn Startdatum und Enddatum neu angelegt oder verändert werden.
	 */
	
	
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected EnrollmentMapper() {
		
	}
	
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der EnrollmentMapper-Klasse existiert. 
	 * EnrollmentMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode aufgerufen werden.
	 * 
	 * @return EnrollmentMappe
	 */
	public static EnrollmentMapper enrollmentMapper() {
		if (enrollmentMapper == null) {
			enrollmentMapper = new EnrollmentMapper();
		}
		
		return enrollmentMapper;
	}

	/**
	 * Suchen einer Beteiligung mit vorgegebener EnrollmentID. Durch die Eindeutigkeit der ID 
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param e
	 * @return Enrollment-Objekt, das der übergebenen ID entspricht
	 */
	public Enrollment findByID(Integer id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id, jobdescription FROM enrollment " + "WHERE id = " + id);
			/*
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
			if (rs.next()) {
				Enrollment e = new Enrollment();
				e.setID(rs.getInt("id"));
				e.setCreated(rs.getTimestamp("created"));
				System.out.println(e.getCreated());
				try{
				e.setStartDate(sdf.parse(rs.getString("start_date")));
				}
				catch(NullPointerException e4){
				e.setStartDate(null);
				}
				try{
				e.setWorkload(rs.getInt("period"));
				}
				catch(NullPointerException e5){
				e.setWorkload(null);
				}
				try{
				e.setEndDate(sdf.parse(rs.getString("start_date")));
				}
				catch(NullPointerException e6){
				e.setEndDate(null);
				}
				e.setOrgaUnitID(rs.getInt("orgaunit_id"));
				e.setProjectID(rs.getInt("project_id"));
				try{
				e.setRatingID(rs.getInt("rating_id"));
				}
				catch(NullPointerException e7){
				e.setRatingID(null);
				}
				try{
					e.setJobdescription(rs.getString("jobdescription"));
					}
				catch(NullPointerException e8){
					e.setJobdescription(null);
				}
				
				return e;
			}
		}
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * Auslesen aller Enrollment-Objekte aus der Datenbank.
	 * 
	 * @return Vektor <Enrollment>
	 */
	public Vector<Enrollment> findAll() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		Vector<Enrollment> result = new Vector<Enrollment>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "ORDER BY id");
			
			while (rs.next()) {
				
				Enrollment e = new Enrollment();
				e.setID(rs.getInt("id"));
				e.setCreated(rs.getTimestamp("created"));
				System.out.println(e.getCreated());
				try{
				e.setStartDate(sdf.parse(rs.getString("start_date")));
				}
				catch(NullPointerException e4){
				e.setStartDate(null);
				}
				try{
				e.setWorkload(rs.getInt("period"));
				}
				catch(NullPointerException e5){
				e.setWorkload(null);
				}
				try{
				e.setEndDate(sdf.parse(rs.getString("start_date")));
				}
				catch(NullPointerException e6){
				e.setEndDate(null);
				}
				e.setOrgaUnitID(rs.getInt("orgaunit_id"));
				e.setProjectID(rs.getInt("project_id"));
				try{
				e.setRatingID(rs.getInt("rating_id"));
				}
				catch(NullPointerException e7){
				e.setRatingID(null);
				}
				try{
				e.setJobdescription(rs.getString("jobdescription"));
				}
				catch(NullPointerException e8){
				e.setJobdescription(null);
				}
				
				result.addElement(e);
			}
		}
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Auslesen der Beteiligungen anhand der übergebenen OrgaUnitID. Alle Beteiligungen, die zu 
	 * einer bestimmten Organisationseinheit werden ausgelesen.
	 * 
	 * @param orgaUnitID
	 * @return Vektor<Enrollment>
	 */
	public Vector<Enrollment> findByOrgaUnitID (Integer orgaUnitID) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		Vector<Enrollment> result = new Vector<Enrollment>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "WHERE orgaunit_id ='" + orgaUnitID + "'ORDER BY id");
			
				while (rs.next()) {
				
					Enrollment e = new Enrollment();
					e.setID(rs.getInt("id"));
					e.setCreated(rs.getTimestamp("created"));
					System.out.println(e.getCreated());
					try{
					e.setStartDate(sdf.parse(rs.getString("start_date")));
					}
					catch(NullPointerException e4){
					e.setStartDate(null);
					}
					try{
					e.setWorkload(rs.getInt("period"));
					}
					catch(NullPointerException e5){
					e.setWorkload(null);
					}
					try{
					e.setEndDate(sdf.parse(rs.getString("start_date")));
					}
					catch(NullPointerException e6){
					e.setEndDate(null);
					}
					e.setOrgaUnitID(rs.getInt("orgaunit_id"));
					e.setProjectID(rs.getInt("project_id"));
					try{
					e.setRatingID(rs.getInt("rating_id"));
					}
					catch(NullPointerException e7){
					e.setRatingID(null);
					}
					try{
					e.setJobdescription(rs.getString("jobdescription"));
					}
					catch(NullPointerException e8){
					e.setJobdescription(null);
					}
				
				result.addElement(e);
			}
		}
		
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Auslesen der Beteiligungen anhand der übergebenen ProjectID. Die Beteiligungen, die zu einem
	 * bestimmten Projekt gehören werden hier aus der Datenbank ausgelesen.
	 * 
	 * @param projectID
	 * @return Vektor<Enrollment>
	 */
	public Vector<Enrollment> findByProjectID (Integer projectID) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		Vector<Enrollment> result = new Vector<Enrollment>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "WHERE project_id ='" + projectID + "'ORDER BY id");
			
				while (rs.next()) {
				
					Enrollment e = new Enrollment();
					e.setID(rs.getInt("id"));
					e.setCreated(rs.getTimestamp("created"));
					System.out.println(e.getCreated());
					try{
					e.setStartDate(sdf.parse(rs.getString("start_date")));
					}
					catch(NullPointerException e4){
					e.setStartDate(null);
					}
					try{
					e.setWorkload(rs.getInt("period"));
					}
					catch(NullPointerException e5){
					e.setWorkload(null);
					}
					try{
					e.setEndDate(sdf.parse(rs.getString("end_date")));
					}
					catch(NullPointerException e6){
					e.setEndDate(null);
					}
					e.setOrgaUnitID(rs.getInt("orgaunit_id"));
					e.setProjectID(rs.getInt("project_id"));
					try{
					e.setRatingID(rs.getInt("rating_id"));
					}
					catch(NullPointerException e7){
					e.setRatingID(null);
					}
					try{
					e.setJobdescription(rs.getString("jobdescription"));
					}
					catch(NullPointerException e8){
					e.setJobdescription(null);
					}
				
				result.addElement(e);
			}
		}
		
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Auslesen aller Beteiligung anhand der übergebenen RatintingID. Alle Beteiligungen, 
	 * die einer bestimmten Bewertungen entsprechen, werden hier aus der Datenbank ausgelesen.
	 * 
	 * @param rID
	 * @return
	 */
	public Enrollment findByRatingID (Integer rID) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "WHERE rating_id ='" + rID + "'ORDER BY id");
			
				if (rs.next()) {
				
					Enrollment e = new Enrollment();
					e.setID(rs.getInt("id"));
					e.setCreated(rs.getTimestamp("created"));
					System.out.println(e.getCreated());
					try{
					e.setStartDate(sdf.parse(rs.getString("start_date")));
					}
					catch(NullPointerException e4){
					e.setStartDate(null);
					}
					try{
					e.setWorkload(rs.getInt("period"));
					}
					catch(NullPointerException e5){
					e.setWorkload(null);
					}
					try{
					e.setEndDate(sdf.parse(rs.getString("start_date")));
					}
					catch(NullPointerException e6){
					e.setEndDate(null);
					}
					e.setOrgaUnitID(rs.getInt("orgaunit_id"));
					e.setProjectID(rs.getInt("project_id"));
					try{
					e.setRatingID(rs.getInt("rating_id"));
					}
					catch(NullPointerException e7){
					e.setRatingID(null);
					}
					try{
						e.setJobdescription(rs.getString("jobdescription"));
					}
					catch(NullPointerException e8){
					e.setJobdescription(null);
					}
				
				return e;
			}
		}
		
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Einfuegen eines Enrollment-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
	 * des uebergebenen Objektes geprueft und ggf. berichtigt.
	 * 
	 * @param e
	 * @return Enrollment
	 */
	public Enrollment insert(Enrollment e) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		try {
			
			/*
			 *  Start und Enddatum werden mit Hochkommas verknüpft, da im Falle einer Automatischen
			 *  Beteiligung das Start und Enddatum nicht gesetzt sind. "Null" darf nicht als String
			 *  in der Datenbank gespeichert werden, deshalb werden die Hochkommas direkt im startdatum und 
			 *  enddatum gesetzt. 
			 */
			
			String invertedcomma = "'";
			String startdate = null;
			String enddate = null;
			if (e.getStartDate() != null){
			startdate = sdf.format(e.getStartDate());
			startdate = invertedcomma.concat(startdate).concat(invertedcomma);
			}
			if (e.getEndDate() != null){
			enddate = sdf.format(e.getEndDate());
			enddate = invertedcomma.concat(enddate).concat(invertedcomma);
			}
        	
			Statement stmt = con.createStatement();
			/*
			 *Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoeht und an an part vergeben
			*/
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM enrollment ");
			
			if (rs.next()) {
				
				e.setID(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				// Einfuegeoption, damit das neue PartnerProfile-Tupel in die Datenbank eingefuegt werden kann.
				stmt.executeUpdate("INSERT INTO enrollment (id, created, start_date, end_date, period,"
						+ " orgaunit_id, project_id, rating_id, jobdescription) " + "VALUES (" + e.getID() + ",'"
						+ e.getCreated() + "'," + startdate + "," + enddate + ","
				        + e.getWorkload() + "," + e.getOrgaUnitID() + "," + e.getProjectID() + ","
						+ e.getRatingID()+ ",'" + e.getJobdescription() + "')");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return e;
	}
	
	/**
	 * Methode ermoeglicht, dass ein Enrollment-Objekt in der Datenbank aktualisiert werden kann.
	 * 
	 * @param e
	 * @return
	 */
	public Enrollment update(Enrollment e) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = DBConnection.connection();
		
		try {
			String startdate = sdf.format(e.getStartDate());
        	String enddate = sdf.format(e.getEndDate());
        	
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE enrollment SET start_date='" + startdate +
					"', end_date='" + enddate + "', orgaunit_id=" + e.getOrgaUnitID() + 
					", project_id=" + e.getProjectID() + ", rating_id=" + e.getRatingID() +
					", period=" + e.getWorkload() + ", jobdescription='" + e.getJobdescription() +
					"' WHERE id=" + e.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return e;
	}
	
	/**
	 * Loeschen eines Enrollment-Objektes aus der Datenbank.
	 * 
	 * @param e
	 */
	public void delete(Enrollment e) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM enrollment " + "WHERE id=" + e.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Auslesen des zugehoerigen Person-Objekts zu einer gegebenen Beteiligung. 
	 * 
	 * @param e die Beteiligung, deren Person wir auslesen möchten 
	 * @return ein Objekt, das die Person zu der Beteiligung darstellt
	 */
	public Person getSourcePerson(Enrollment e) {
		 //Bedienen am PersonMapper
		return PersonMapper.personMapper().findByID(e.getOrgaUnitID());
	}

	/**
	 * Auslesen des zugehoerigen Organisation-Objekts zu einer gegebenen Beteiligung.
	 * 
	 * @param e die Beteiligung, deren Organisation wir auslesen möchten
	 * @return ein Objekt, das die Organisation zu der Beteiligung darstellt
	 */
	public Organisation getSourceOrganisation(Enrollment e) {
		//Bedienen am OrganisationMapper
		return OrganisationMapper.organisationMapper().findByID(e.getOrgaUnitID());
	}
	
	/**
	 * Auslesen des zugehoerigen Team-Objekts zu einer gegebenen Beteiligung.
	 * 
	 * @param e die Beteiligung, deren Team wir auslesen moechten
	 * @return ein Objekt, das das Team zu der Beteiligung darstellt
	 */
	public Team getSourceTeam(Enrollment e) {
		//Bedienen am TeamMapper
		return TeamMapper.teamMapper().findByID(e.getOrgaUnitID());
	}
	
	/**
	 * Auslesen des zugehoerigen Project-Objekts zu einer gegebenen Beteiligung.
	 * 
	 * @param e die Beteiligung, deren Projekt wir auslesen moechten
	 * @return ein Objekt, das das Projekt zu der Beteiligung darstellt
	 */
	public Project getSourceProject(Enrollment e) {
		//Bedienen am ProjectMapper
		return ProjectMapper.projectMapper().findByID(e.getProjectID());
	}
	
	
}