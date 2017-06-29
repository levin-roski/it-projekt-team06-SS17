package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;
import de.worketplace.team06.shared.bo.*;
/**
 * Die Mapper-Klasse PersonMapper bildet Person-Objekte als Datensätze
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
 * @see OrgaUnitMapper
 * @see PartnerProfileMapper
 * @see ProjectMapper
 * @see PropertyMapper
 * @see RatingMapper
 * @see TeamMapper
 * 
 * @author Patrick
 * @author Theresa
 */

public class PersonMapper {
	
	/**
     * Die Klasse PersonMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     * @author Thies
     * @author Theresa
     */
	
	
	private static PersonMapper personMapper = null;
	
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	
	protected PersonMapper() {
		
	}

	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der PersonMapper-Klasse existiert. 
	 * PersonMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode genutzt werden.
	 * 
	 * @return PersonMapper
	 * @author Thies
	 * @author Theresa
	 */
	
	public static PersonMapper personMapper() {
		if (personMapper == null) {
			personMapper = new PersonMapper();
		}
		
		return personMapper;
	}		
	
	/**
	 * Einfuegen eines Team-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
	 * des uebergebenen Objektes geprueft und ggf. berichtigt.
	 * 
	 * @param p
	 * @return person
	 * @author Thies
	 * @author Theresa
	 */
	
	public Person insert(Person p) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			/*
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 * wird dann um 1 erhoeht und an an p vergeben.
			*/
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM orgaunit ");
			
			if (rs.next()) {
				
				p.setID(rs.getInt("maxid") + 1);
				
				con.setAutoCommit(false);
				stmt = con.createStatement();
				/*
				 * Einfuegeoption, damit das neue Person-Tupel in die Datenbank eingefuegt werden kann.
				 * Das Person-Tupel wird zunächst in Organisationseinheit eingefügt, um dann in Person 
				 * hinzugefügt werden zu können, da Person eine Organisationseinheit ist.
				 */
				stmt.executeUpdate("INSERT INTO orgaunit (id, created, google_id, description, type) "
									+ "VALUES (" + p.getID() + ",'" + p.getCreated() + "','" 
									+ p.getGoogleID() +  "','" + p.getDescription() +  "','"
									+ p.getType() + "')");
				stmt.executeUpdate("INSERT INTO person (id, created, firstname, lastname, street, zipcode, city) "
									+ "VALUES (" + p.getID() + ",'" + p.getCreated() + "','"
									+ p.getFirstName() + "','" + p.getLastName() + "','" + p.getStreet() + "'," 
									+ p.getZipcode() + ",'" + p.getCity() + "')");
				con.commit();
			}
		}
		catch (SQLException e2) {
			try {
				System.out.println("Die SQL Transaktion konnte nicht vollständig ausgeführt werden. "
						+ "Es wird versucht die Transaktion rückgängig zu machen!");
				con.rollback();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			  finally {
				 
				  e2.printStackTrace();
			}	
		}
		finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return p;
	}
	
	/**
	 * Auslesen eines Person-Objekts aus der Datenbank anhand der übergebenen Google-ID. 
	 * 
	 * @param googleID
	 * @return Person-Objekt 
	 * @author Theresa
	 */
	public Person findByGoogleID(String googleID) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			
			/* Tabellen orgaunit und person werden miteinander verbunden, da Person 
			 * immer zu einer Organisationseinheit gehört.
			*/ 
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN person "
											+ "ON orgaunit.id = person.id "
											+ "WHERE orgaunit.google_id='" + googleID + "'");		
			
			if (rs.next()) {
				Person p = new Person();
				p.setID(rs.getInt("id"));
				p.setCreated(rs.getTimestamp("created"));
				p.setGoogleID(googleID);
				p.setDescription(rs.getString("description"));
				p.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				p.setType(rs.getString("type"));
				
				p.setFirstName(rs.getString("firstname"));
				p.setLastName(rs.getString("lastname"));
				p.setStreet(rs.getString("street"));
				p.setZipcode(rs.getInt("zipcode"));
				p.setCity(rs.getString("city"));
				return p;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}	
	
	/**
     * Methode ermoeglicht, dass ein Person-Objekt in der Datenbank aktualisiert werden kann.
     * 
     * @param orgaUnit 
     * @return Person
     * @author Theresa
     */
	public void update(Person p) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	// Tabelle orgaunit und person, da Person eine Organisationseinheit ist.
	    	stmt.executeUpdate("UPDATE orgaunit, person SET"
	    						+ " orgaunit.description='" + p.getDescription() +
	    						"', orgaunit.partnerprofile_id= " + p.getPartnerProfileID() +
	    						", person.firstname= '" + p.getFirstName() +
	    						"', person.lastname= '" + p.getLastName() + 
	    						"', person.street= '" + p.getStreet() + 
	    						"', person.city= '" + p.getCity() + 
	    						"', person.zipcode= " + p.getZipcode() + 
	    						" WHERE orgaunit.id= " + p.getID() + 
	    						" AND person.id= " + p.getID()); 
	    			
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}	
	
	/**
     * Loeschen eines Person-Objektes aus der Datenbank
     * 
     * @param person
     * @author Theresa
     */
	
	public void delete(Person p) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	//Löschen der Person aus der Tabelle orgaunit und person.
	    	stmt.executeUpdate("DELETE orgaunit, person FROM orgaunit INNER JOIN person "
	    			+ "ON orgaunit.id = person.id WHERE orgaunit.id= " + p.getID());
	    	
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}

	/**
	 * Auslesen eines Person-Objekts anhand der übergebenen ID. Durch die Eindeutigkeit der ID, 
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param ouid
	 * @return Person-Objekt, das der übergebenen ID entspricht
	 * @author Theresa
	 */
	
	public Person findByID(Integer ouid) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN person "
											+ "ON orgaunit.id = person.id "
											+ "WHERE orgaunit.id = " + ouid);	
			/*
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der Datenbank vorhanden ist
			 */
			
			if (rs.next()) {
				Person p = new Person();
				p.setID(rs.getInt("id"));
				p.setCreated(rs.getTimestamp("created"));
				p.setGoogleID(rs.getString("google_id"));
				p.setDescription(rs.getString("description"));
				p.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				p.setType(rs.getString("type"));
				
				p.setFirstName(rs.getString("firstname"));
				p.setLastName(rs.getString("lastname"));
				p.setStreet(rs.getString("street"));
				p.setZipcode(rs.getInt("zipcode"));
				p.setCity(rs.getString("city"));
				return p;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	public Vector<Person> findAll() {
		Connection con = DBConnection.connection();
		Vector<Person> result = new Vector<Person>();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN person "
											+ "ON orgaunit.id = person.id");	
			/*
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der Datenbank vorhanden ist
			 */
			
			while (rs.next()) {
				Person p = new Person();
				p.setID(rs.getInt("id"));
				p.setCreated(rs.getTimestamp("created"));
				p.setGoogleID(rs.getString("google_id"));
				p.setDescription(rs.getString("description"));
				p.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				p.setType(rs.getString("type"));
				
				p.setFirstName(rs.getString("firstname"));
				p.setLastName(rs.getString("lastname"));
				p.setStreet(rs.getString("street"));
				p.setZipcode(rs.getInt("zipcode"));
				p.setCity(rs.getString("city"));
				result.addElement(p);
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return result;
	}	

}
